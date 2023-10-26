package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

    private OnlinePaymetnService onlinePaymetnService;

    public ContractService(OnlinePaymetnService onlinePaymetnService){
        this.onlinePaymetnService = onlinePaymetnService;
    }
    public void processContract (Contract contract,Integer months){

        Double basicQuota = contract.getTotalValue() / months;

        for (int i = 1; i <= months ; i++){
            Double interest = onlinePaymetnService.interest(basicQuota, i);
            Double fee = onlinePaymetnService.paymentFee( basicQuota + interest);
            Double quota = basicQuota + interest + fee;
            LocalDate dueDate = contract.getDate().plusMonths(i);
            contract.getInstallments().add(new Installment(dueDate, quota));
        }
    }
}
