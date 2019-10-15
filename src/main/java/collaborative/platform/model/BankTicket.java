package collaborative.platform.model;

import jade.core.AID;

import java.io.Serializable;

public class BankTicket implements Serializable {

    private AID credit;
    private AID debtor;

    private long value;

    private boolean state;

    public BankTicket(AID credit, AID debtor, long value, boolean state) {
        this.value = value;
        this.credit = credit;
        this.debtor = debtor;
        this.state = state;
    }

    @Override
    public String toString() {
        return debtor.getLocalName() + "<- " + value + " ->" + credit.getLocalName() + " state : " + state;
    }

    public AID getCredit() {
        return credit;
    }

    public void setCredit(AID credit) {
        this.credit = credit;
    }

    public AID getDebtor() {
        return debtor;
    }

    public void setDebtor(AID debtor) {
        this.debtor = debtor;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
