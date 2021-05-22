package model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PaymentDTO implements Serializable {
    public enum actionType {
        debtor,creditor
    }
    private actionType actionType;
    private String depositNumber;
    private BigDecimal balance;

    public void setActionType(actionType actionType) {
        this.actionType = actionType;
    }
    public PaymentDTO.actionType getActionType() {
        return actionType;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public PaymentDTO() {
    }

    public String toString() {
        return actionType+" "+this.depositNumber+" "+this.balance+"\n";
    }
}
