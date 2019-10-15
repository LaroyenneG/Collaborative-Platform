package collaborative.platform.model;

import jade.core.AID;

import java.io.Serializable;

public class OrderProposal implements Serializable {

    private Product product;
    private AID from;

    private long price;

    public OrderProposal(Product product, long price, AID from) {
        this.product = product;
        this.price = price;
        this.from = from;
    }

    public OrderProposal(Product product, long price) {
        this.product = product;
        this.price = price;
        this.from = null;
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

    public AID getFrom() {
        return from;
    }

    public void setFrom(AID from) {
        this.from = from;
    }
}
