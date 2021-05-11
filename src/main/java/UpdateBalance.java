import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class UpdateBalance extends WriteFile {
    private static StringBuffer updateBalanceBuffer = new StringBuffer();
    private static StringBuffer transactionBuffer = new StringBuffer();
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);
    private static List<Callable<String>> callables = new ArrayList<>();

    public static void updateBalanceFile(List<PaymentDTO> paymentDTOList, List<BalanceDTO> balanceDTOList, BigDecimal sumPay, String pathBalanceFile, String pathTransactionFile) throws InterruptedException {
        BalanceDTO balanceDTOBank = new BalanceDTO();
        balanceDTOBank.setDepositNumber(paymentDTOList.get(0).getDepositNumber());
        balanceDTOBank.setBalance(paymentDTOList.get(0).getBalance().subtract(sumPay));
        updateBalanceBuffer.append(balanceDTOBank.toString());
        for (int i = 1; i < 1001; i++) {
            final int j = i;
            Callable<String> task1 = () -> {
                BalanceDTO balanceDTO = new BalanceDTO();
                balanceDTO.setDepositNumber(balanceDTOList.get(j).getDepositNumber());
                BigDecimal x = paymentDTOList.get(j).getBalance();
                balanceDTO.setBalance(balanceDTOList.get(j).getBalance().add(x));
                updateBalanceBuffer.append(balanceDTO.toString());
                return Thread.currentThread().getName() + "**UpdateBalance**";
            };
            callables.add(task1);

            Callable<String> task2 = () -> {
                TransactionDTO transactionDTO = new TransactionDTO();
                transactionDTO.setSourceAccount(paymentDTOList.get(0).getDepositNumber());
                transactionDTO.setDestinationAccount(paymentDTOList.get(j).getDepositNumber());
                transactionDTO.setDeposit(paymentDTOList.get(j).getBalance());
                transactionBuffer.append(transactionDTO.toString());
                return Thread.currentThread().getName() + "**Transaction**";
            };
            callables.add(task2);
        }
        try {
            List<Future<String>> futures = executorService.invokeAll(callables);
            executorService.shutdown();
            for (Future future : futures
            ) {
                System.out.println(future.get().toString());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        writeFileChannel(ByteBuffer.wrap(updateBalanceBuffer.toString().getBytes()), pathBalanceFile);
        writeFileChannel(ByteBuffer.wrap(transactionBuffer.toString().getBytes()), pathTransactionFile);

    }
}
