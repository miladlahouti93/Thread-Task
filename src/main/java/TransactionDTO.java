import java.math.BigDecimal;

public class TransactionDTO {
    private String sourceAccount;
    private String destinationAccount;
    private BigDecimal deposit;

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
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
        return new StringBuffer().append(this.sourceAccount)
                .append(" ").append(this.destinationAccount).append(" ").append(this.deposit).append("\n").toString();
    }
}

