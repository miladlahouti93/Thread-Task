import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static String filePathPay = "src/main/resources/pay.txt";
    private final static String filePathBalance = "src/main/resources/balance.txt";
    private final static String filePathTransaction = "src/main/resources/transaction.txt";

    private static BigDecimal sumPay=new BigDecimal(0);
    private static BigDecimal bankBalance;
    private static String bankAccountNumber = "1.10.100.1";
    private static List<PaymentDTO> paymentDTOList = new ArrayList<>();
    private static List<BalanceDTO> balanceDTOList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        CreateFiles.createFile("pay", bankAccountNumber, filePathPay);
        CreateFiles.createFile("balance", bankAccountNumber, filePathBalance);
        paymentDTOList=ReadFile.readFile("pay",filePathPay);
        balanceDTOList = ReadFile.readFile("balance",filePathBalance);
        bankBalance = paymentDTOList.get(0).getBalance();
        for (int i = 1; i < 1001; i++) {
            BigDecimal x=paymentDTOList.get(i).getBalance();
            sumPay=sumPay.add(x);
        }
        if (bankBalance.compareTo(sumPay)==1) {
            try {
                UpdateBalance.updateBalanceFile(paymentDTOList, balanceDTOList, sumPay, filePathBalance,filePathTransaction);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Bank account balance is not enough!!!!");
        }
    }


}
