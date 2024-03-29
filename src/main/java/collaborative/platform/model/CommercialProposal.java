package collaborative.platform.model;

import java.io.Serializable;

public class CommercialProposal implements Serializable {

    private Product product;

    private long price;

    public CommercialProposal(Product product, long price) {
        this.product = product;
        this.price = price;
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
}
