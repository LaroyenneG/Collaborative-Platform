package collaborative.platform.model;

import jade.core.AID;

import java.io.Serializable;

public class BankTransaction implements Serializable {

    private AID receiver;

    private long value;

    public BankTransaction(AID receiver, long value) {
        this.value = value;
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "$" + value + " --> " + receiver.toString();
    }

    public AID getReceiver() {
        return receiver;
    }

    public void setReceiver(AID receiver) {
        this.receiver = receiver;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
