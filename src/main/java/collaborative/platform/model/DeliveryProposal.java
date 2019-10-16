package collaborative.platform.model;

import java.io.Serializable;

public class DeliveryProposal implements Serializable {

    private long time;
    private long price;

    public DeliveryProposal(long time, long price) {
        this.time = time;
        this.price = price;
    }

    @Override
    public String toString() {
        return time + " s" + price;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
