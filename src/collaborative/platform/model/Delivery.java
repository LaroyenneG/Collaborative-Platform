package collaborative.platform.model;

import jade.core.AID;
import jade.core.Agent;

import java.io.Serializable;

public class Delivery implements Serializable {

    private long time;
    private long price;

    public Delivery(long time, long price, AID from) {
        this.time = time;
        this.price = price;
    }

    public Delivery(long time, long price, Agent agent) {
        this(time, price, agent.getAID());
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
