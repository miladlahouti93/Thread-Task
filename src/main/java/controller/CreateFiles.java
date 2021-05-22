package controller;

import model.BalanceDTO;
import model.PaymentDTO;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Random;

//write in files (pay and balance)
public class CreateFiles extends WriteFile {

    public static void createFile(String typeDto, String accountNumber, String filePath,int numberOfAccount) {
        StringBuffer payBuffer = new StringBuffer();
        StringBuffer balanceBuffer = new StringBuffer();
        Random random = new Random();
        if (typeDto.equals("pay")) {
            PaymentDTO bankAccount = new PaymentDTO();
            bankAccount.setActionType(PaymentDTO.actionType.debtor);
            bankAccount.setDepositNumber(accountNumber);
            bankAccount.setBalance(BigDecimal.valueOf(random.nextInt(1000000)));
            payBuffer.append(bankAccount.toString());
            for (int i = 1; i <= numberOfAccount; i++) {
                PaymentDTO paymentDTO = new PaymentDTO();
                paymentDTO.setActionType(PaymentDTO.actionType.creditor);
                paymentDTO.setDepositNumber("1.20.100." + i);
                paymentDTO.setBalance(BigDecimal.valueOf(random.nextInt(5000)));
                payBuffer.append(paymentDTO.toString());

            }
            WriteFile.writeFileChannel(ByteBuffer.wrap(payBuffer.toString().getBytes()), filePath);

        } else {
            BalanceDTO bankAccount = new BalanceDTO();
            bankAccount.setDepositNumber(accountNumber);
            bankAccount.setBalance(BigDecimal.valueOf(random.nextInt(1000000000)));
            balanceBuffer.append(bankAccount.toString());
            for (int j = 1; j <= numberOfAccount; j++) {
                BalanceDTO balanceDTO = new BalanceDTO();
                balanceDTO.setDepositNumber("1.20.100." + j);
                balanceDTO.setBalance(BigDecimal.valueOf(random.nextInt(6000)));
                balanceBuffer.append(balanceDTO.toString());

            }
            WriteFile.writeFileChannel(ByteBuffer.wrap(balanceBuffer.toString().getBytes()), filePath);

        }

    }
}
