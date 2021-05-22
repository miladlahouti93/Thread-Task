package controller;

import model.BalanceDTO;
import model.PaymentDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public static <T> List<T> readFile(String typeDto, String filePathPay) {
        Path pathPay = Paths.get(filePathPay);
        List<String> payLine = null;
        try {
            payLine = Files.readAllLines(pathPay, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (typeDto.equals("pay")) {
            List<PaymentDTO> paymentDTOList = new ArrayList<>();
            for (String line : payLine) {
                PaymentDTO paymentDTO = new PaymentDTO();
                if (!line.isEmpty()) {
                    String[] tokens = line.split("\\s");
                    if (!tokens.equals(null)) {
                        paymentDTO.setActionType(PaymentDTO.actionType.valueOf(tokens[0]));
                        paymentDTO.setDepositNumber(tokens[1]);
                        paymentDTO.setBalance(new BigDecimal(tokens[2]));
                    }
                }
                paymentDTOList.add(paymentDTO);
            }
            return (List<T>) paymentDTOList;
        } else {
            List<BalanceDTO> balanceDTOS = new ArrayList<>();
            for (String line : payLine) {
                BalanceDTO balanceDTO = new BalanceDTO();
                if (!line.isEmpty()) {
                    String[] tokens = line.split("\\s");
                    if (!tokens.equals(null)) {
                        balanceDTO.setDepositNumber(tokens[0]);
                        balanceDTO.setBalance(new BigDecimal(tokens[1]));
                    }
                }
                balanceDTOS.add(balanceDTO);
            }
            return (List<T>) balanceDTOS;
        }

    }
}

