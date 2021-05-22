package model;

import java.math.BigDecimal;

public class TransactionDTO {
    private String sourceDepositNumber;
    private String destinationDepositNumber;
    private BigDecimal deposit;

    public String getSourceDepositNumber() {
        return sourceDepositNumber;
    }

    public void setSourceDepositNumber(String sourceDepositNumber) {
        this.sourceDepositNumber = sourceDepositNumber;
    }

    public String getDestinationDepositNumber() {
        return destinationDepositNumber;
    }

    public void setDestinationDepositNumber(String destinationDepositNumber) {
        this.destinationDepositNumber = destinationDepositNumber;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public TransactionDTO() {
    }


    @Override
    public String toString() {
        return this.sourceDepositNumber+" "+this.destinationDepositNumber+" "+this.deposit+"\n";
    }
}

