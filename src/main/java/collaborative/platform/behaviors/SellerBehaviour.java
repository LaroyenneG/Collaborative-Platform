package collaborative.platform.behaviors;

import collaborative.platform.agents.Protocol;
import collaborative.platform.model.CommercialProposal;
import collaborative.platform.model.DeliveryProposal;
import collaborative.platform.model.Product;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SellerBehaviour extends CyclicBehaviour {
    private HashMap<Product, Long> pricesTable = new HashMap<>();

    public SellerBehaviour(Agent a) {
        super(a);

        for (Product product : Product.productList()) {
            long leftLimit = 1L;
            long rightLimit = 10L;
            long randomPrice = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
            pricesTable.put(product, randomPrice); //prix aléatoire entre 1 et 10€
        }
    }

    @Override
    public void action() {
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) {
            if (!aclMessage.getOntology().equals(Protocol.ONTOLOGY)) return;

            switch (aclMessage.getProtocol()) {
                case Protocol.SELLER_REQUEST_PRICE:
                    try {
                        Product asked = (Product) aclMessage.getContentObject();
                        if (asked == null) {
                            System.out.println("product null");
                            return;
                        }
                        CommercialProposal offer;
                        if (pricesTable.containsKey(asked)) {
                            offer = new CommercialProposal(asked, pricesTable.get(asked));
                        }
                        else {
                            offer = new CommercialProposal(asked, -1);
                        }
                        ACLMessage answer = aclMessage.createReply();
                        answer.setContentObject(offer);
                        answer.setProtocol(Protocol.BUYER_OFFER_FROM_SELLER);
                        System.out.println("[" + this.myAgent.getLocalName() + "] Price sent to buyer : " + pricesTable.get(asked).longValue() + "€");
                        myAgent.send(answer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    System.out.println("message non reconnu");
                    break;
            }
        } else {
            block();
        }
    }
}
