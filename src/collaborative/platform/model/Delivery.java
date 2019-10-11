package collaborative.platform.model;

import jade.core.AID;
import jade.core.Agent;

import java.io.Serializable;

public class Delivery implements Serializable {

    private long time;
    private long price;

    private AID from;

    public Delivery(long time, long price, AID from) {
        this.time = time;
        this.price = price;
        this.from = from;
    }

    public Delivery(long time, long price, Agent agent) {
        this(time, price, agent.getAID());
    }

    @Override
    public String toString() {
        return time + "s " + price + "$ from : " + from.toString();
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

    public AID getFrom() {
        return from;
    }

    public void setFrom(AID from) {
        this.from = from;
    }
}
