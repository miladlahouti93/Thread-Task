import controller.CreateFiles;
import controller.ReadFile;
import controller.UpdateBalance;
import model.BalanceDTO;
import model.PaymentDTO;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private final static String filePathPay = "src/main/resources/pay.txt";
    private final static String filePathBalance = "src/main/resources/balance.txt";

    public static void main(String[] args) throws IOException {
        BigDecimal sumPay = new BigDecimal(0);
        BigDecimal bankBalance = new BigDecimal(0);
        BalanceDTO bankBalanceDTO = new BalanceDTO();

        String bankAccountNumber = "1.10.100.1";
        List<PaymentDTO> paymentDTOList;
        List<BalanceDTO> balanceDTOList;

        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter number of account : ");
        int numberOfAccount = scanner.nextInt();

        clearFile(filePathBalance);
        clearFile(filePathPay);

        CreateFiles.createFile("pay", bankAccountNumber, filePathPay, numberOfAccount);
        CreateFiles.createFile("balance", bankAccountNumber, filePathBalance, numberOfAccount);
        paymentDTOList = ReadFile.readFile("pay", filePathPay);
        balanceDTOList = ReadFile.readFile("balance", filePathBalance);

        for (PaymentDTO paymentList : paymentDTOList) {
            sumPay = paymentList.getBalance().add(sumPay);
        }

        for (BalanceDTO balanceList : balanceDTOList) {
            if (balanceList.getDepositNumber().equals(bankAccountNumber)) {
                bankBalance = balanceList.getBalance();
                bankBalanceDTO.setDepositNumber(bankAccountNumber);
                bankBalanceDTO.setBalance(bankBalance);

            }
        }


        if (bankBalance.compareTo(sumPay) == 1) {
            clearFile(filePathBalance);
            ExecutorService executorService = Executors.newFixedThreadPool(10);

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    for (PaymentDTO paymentlist : paymentDTOList) {
                        try {
                            UpdateBalance.updateBalanceFile(paymentlist, balanceDTOList, bankBalanceDTO, filePathBalance);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

            };
            executorService.execute(runnable);
            executorService.shutdown();
        } else {
            System.out.println("Bank account balance is not enough!!!!");
        }
    }

    private static void clearFile(String filePath) {
        try {
            if (Files.size(Paths.get(filePath)) != 0) {
                Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
