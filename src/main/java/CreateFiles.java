
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Random;

//write in files (pay and balance)
public class CreateFiles extends WriteFile {

    private static final Random random = new Random();
    private static StringBuffer payBuffer = new StringBuffer();
    private static StringBuffer balanceBuffer = new StringBuffer();
    private static BigDecimal bankBalance;

    public static void createFile(String typeDto, String bankAccountNumber, String filePath) {
        if (typeDto.equals("pay")) {
            PaymentDTO bankAccount = new PaymentDTO();
            bankAccount.setActionType(PaymentDTO.actionType.debtor);
            bankAccount.setDepositNumber(bankAccountNumber);
            bankBalance = BigDecimal.valueOf(random.nextInt(10000000));
            bankAccount.setBalance(bankBalance);
            payBuffer.append(bankAccount.toString());
            for (int i = 1; i < 1001; i++) {
                PaymentDTO paymentDTO = new PaymentDTO();
                paymentDTO.setActionType(PaymentDTO.actionType.creditor);
                paymentDTO.setDepositNumber("1.20.100." + i);
                paymentDTO.setBalance(BigDecimal.valueOf(random.nextInt(5000)));
                payBuffer.append(paymentDTO.toString());

            }
            writeFileChannel(ByteBuffer.wrap(payBuffer.toString().getBytes()), filePath);

        } else {
            BalanceDTO bankAccount = new BalanceDTO();
            bankAccount.setDepositNumber(bankAccountNumber);
            bankAccount.setBalance(bankBalance);
            balanceBuffer.append(bankAccount.toString());
            for (int j = 1; j < 1001; j++) {
                BalanceDTO balanceDTO = new BalanceDTO();
                balanceDTO.setDepositNumber("1.20.100." + j);
                balanceDTO.setBalance(BigDecimal.valueOf(random.nextInt(6000)));
                balanceBuffer.append(balanceDTO.toString());

            }
            writeFileChannel(ByteBuffer.wrap(balanceBuffer.toString().getBytes()), filePath);

        }

    }
}
