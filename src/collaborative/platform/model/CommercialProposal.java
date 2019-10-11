package collaborative.platform.model;

import jade.core.AID;
import jade.core.Agent;

import java.io.Serializable;

public class CommercialProposal implements Serializable {

    private Product product;

    private long price;

    private AID fromAID;

    public CommercialProposal(Product product, long price, AID fromAID) {
        this.product = product;
        this.price = price;
        this.fromAID = fromAID;
    }

    public CommercialProposal(Product product, long price, Agent agent) {
        this(product, price, agent.getAID());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public AID getFromAID() {
        return fromAID;
    }

    public void setFromAID(Agent agent) {
        fromAID = agent.getAID();
    }

    public void setAid(AID aid) {
        this.fromAID = aid;
    }
}
