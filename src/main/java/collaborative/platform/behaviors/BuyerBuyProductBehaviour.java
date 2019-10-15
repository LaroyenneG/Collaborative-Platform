package collaborative.platform.behaviors;

import collaborative.platform.agents.Protocol;
import collaborative.platform.model.CommercialProposal;
import collaborative.platform.model.DeliveryProposal;
import collaborative.platform.model.OrderProposal;
import collaborative.platform.model.Product;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.util.*;

public class BuyerBuyProductBehaviour extends CyclicBehaviour {

    private Map<Integer, List<CommercialProposal>> commercialProposal;
    private Map<Integer, List<DeliveryProposal>> deliveryProposal;
    private Map<Integer, AID> customers;

    public BuyerBuyProductBehaviour(Agent a) {
        super(a);

        commercialProposal = new HashMap<>();
        deliveryProposal = new HashMap<>();
        customers = new HashMap<>();
    }

    @Override
    public void action() {
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) {
            messageProcessing(aclMessage);
            chooseOffer();
        } else {
            block();
        }
    }

    private void messageProcessing(ACLMessage aclMessage) {
        if(!aclMessage.getOntology().equals(Protocol.ONTOLOGY)){
            return;
        }

        switch (aclMessage.getProtocol()){
            case Protocol.BUYER_BUY:
                processBuy(aclMessage);
                break;

            case Protocol.BUYER_OFFER_FROM_SELLER:
                processOfferFromSeller(aclMessage);
                break;

            case Protocol.BUYER_OFFER_FROM_DELIVERY:
                processOfferFromDelivery(aclMessage);
                break;
        }
    }

    private void processBuy(ACLMessage buyMessage){

        // Initialiser les listes pour enregistrer les offres
        commercialProposal.put(buyMessage.getSender().hashCode(), new ArrayList<>());
        deliveryProposal.put(buyMessage.getSender().hashCode(), new ArrayList<>());
        customers.put(buyMessage.getSender().hashCode(), buyMessage.getSender());

        // Demander les offres
        sendRequestPriceSeller(buyMessage);
        sendRequestPriceDelivery(buyMessage);
    }

    private void processOfferFromSeller(ACLMessage aclMessage){
        try {
            commercialProposal.get(Integer.parseInt(aclMessage.getConversationId())).add((CommercialProposal) aclMessage.getContentObject());
        } catch (UnreadableException e) {
            e.printStackTrace();
        }
    }

    private void processOfferFromDelivery(ACLMessage aclMessage){
        try {
            deliveryProposal.get(Integer.parseInt(aclMessage.getConversationId())).add((DeliveryProposal) aclMessage.getContentObject());
        } catch (UnreadableException e) {
            e.printStackTrace();
        }
    }

    private void sendRequestPriceSeller(ACLMessage buyMessage){
        try {
            Product product = (Product) buyMessage.getContentObject();

            ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
            message.setOntology(Protocol.ONTOLOGY);
            message.setConversationId(String.valueOf(buyMessage.getSender().hashCode()));
            message.setContentObject(product);
            message.setProtocol(Protocol.SELLER_REQUEST_PRICE);

            // Send the message to all sellers
            DFAgentDescription[] result = getAllService(Protocol.SERVICE_SELLER);
            for(DFAgentDescription agent : result){
                message.addReceiver(agent.getName());
            }

            myAgent.send(message);
        } catch (UnreadableException | IOException | FIPAException e) {
            e.printStackTrace();
        }
    }

    private void sendRequestPriceDelivery(ACLMessage buyMessage){
        try {
            ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
            message.setOntology(Protocol.ONTOLOGY);
            message.setConversationId(String.valueOf(buyMessage.getSender().hashCode()));
            message.setProtocol(Protocol.DELIVERY_REQUEST_PRICE);

            // Send the message to all delivery
            DFAgentDescription[] result = getAllService(Protocol.SERVICE_DELIVERY);
            for(DFAgentDescription agent : result){
                message.addReceiver(agent.getName());
            }

            myAgent.send(message);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

    private void sendOfferToCustomer(OrderProposal op, AID customer) throws IOException {
        ACLMessage message = new ACLMessage(ACLMessage.PROPOSE);
        message.setOntology(Protocol.ONTOLOGY);
        message.setProtocol(Protocol.CUSTOMER_OFFER);
        message.setContentObject(op);
        message.addReceiver(customer);

        myAgent.send(message);
    }

    private void chooseOffer() {
        Iterator<Map.Entry<Integer, List<CommercialProposal>>> cpIt = commercialProposal.entrySet().iterator();

        while(cpIt.hasNext()){
            Map.Entry<Integer, List<CommercialProposal>> cp = cpIt.next();
            List<DeliveryProposal> deliveryProposals = deliveryProposal.get(cp.getKey());

            // Si tous les Sellers et tous les Delivery ont répondu
            if(cp.getValue().size() == 3 && deliveryProposals.size() == 3){
                CommercialProposal bestCP = Collections.min(cp.getValue(), (cp1, cp2) -> (int)(cp1.getPrice() - cp2.getPrice()));
                DeliveryProposal bestDP = Collections.min(deliveryProposals, (dp1, dp2) -> (int)(dp1.getPrice() - dp2.getPrice()));

                try {
                    // Envoyer la meilleure proposition au client
                    OrderProposal op = new OrderProposal(bestCP.getProduct(), bestCP.getPrice() + bestDP.getPrice());
                    sendOfferToCustomer(op, customers.get(cp.getKey()));

                    // Supprimer les réponses intermédiaires
                    deliveryProposal.remove(cp.getKey());
                    customers.remove(cp.getKey());
                    cpIt.remove();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private DFAgentDescription[] getAllService(String service) throws FIPAException {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(service);

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);

        return DFService.search(myAgent, dfAgentDescription);
    }
}
