package controller;

import model.BalanceDTO;
import model.PaymentDTO;
import model.TransactionDTO;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import java.nio.ByteBuffer;
import java.util.List;


public class UpdateBalance extends WriteFile {
     private static int countPayment=0;
     public static synchronized void updateBalanceFile(PaymentDTO paymentDTO, List<BalanceDTO> balanceDTOList, BalanceDTO bankBalanceDTO, String pathBalanceFile) throws InterruptedException {
        init();
        Logger logger = Logger.getLogger(TransactionDTO.class);
        TransactionDTO transactionDTO = new TransactionDTO();
        for (BalanceDTO balanceList : balanceDTOList) {
            if (balanceList.getDepositNumber().equals(paymentDTO.getDepositNumber())) {
                if (balanceList.getDepositNumber().equals(bankBalanceDTO.getDepositNumber())){
                    bankBalanceDTO.setBalance(balanceList.getBalance().subtract(paymentDTO.getBalance()));
                    countPayment++;
                    break;
                }
                    else{
                    balanceList.setBalance(balanceList.getBalance().add(paymentDTO.getBalance()));
                    bankBalanceDTO.setBalance(bankBalanceDTO.getBalance().subtract(paymentDTO.getBalance()));
                    writeFileChannel(ByteBuffer.wrap(balanceList.toString().getBytes()), pathBalanceFile);
                    countPayment++;
                    if(balanceDTOList.size()==countPayment){
                        writeFileChannel(ByteBuffer.wrap(bankBalanceDTO.toString().getBytes()),pathBalanceFile);
                    }

                    transactionDTO.setSourceDepositNumber(bankBalanceDTO.getDepositNumber());
                    transactionDTO.setDestinationDepositNumber(balanceList.getDepositNumber());
                    transactionDTO.setDeposit(paymentDTO.getBalance());
                    logger.info(transactionDTO.toString());
                    break;
                }
            }

        }

    }

    private static void init() {
        DOMConfigurator.configure("log4j.xml");
    }


}

