package collaborative.platform.model;

import jade.core.AID;

import java.io.Serializable;

public class BankTransaction implements Serializable {

    private AID from;
    private AID to;

    private long value;

    public BankTransaction(AID from, AID to, long value) {
        this.value = value;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return from.toString() + "- " + value + " ->" + to.toString();
    }

    public void setFrom(AID from) {
        this.from = from;
    }

    public AID getTo() {
        return to;
    }

    public void setTo(AID to) {
        this.to = to;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
