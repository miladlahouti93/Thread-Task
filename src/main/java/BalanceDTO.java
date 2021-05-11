import java.math.BigDecimal;

public class BalanceDTO {
    private String depositNumber;
    private BigDecimal balance;

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

    public BalanceDTO() {
    }


    @Override
    public String toString() {
        return new StringBuffer().append(this.depositNumber)
                .append(" ").append(this.balance).append("\n").toString();
    }
}
