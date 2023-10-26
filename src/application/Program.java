package application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Enter contract data: ");
        System.out.print("Number: ");
        Integer numberC = sc.nextInt();
        sc.nextLine();
        System.out.print("Date (dd/mm/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(), sdf);
        System.out.print("Contract value: ");
        Double value = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter number of installments: ");
        Integer numberI = sc.nextInt();
        sc.nextLine();
        Contract contract = new Contract(numberC, date, value);
        ContractService contractService = new ContractService(new PaypalService());
        
        contractService.processContract(contract, numberI);
        System.out.println("Installments:");
        for (Installment i : contract.getInstallments()){
            System.out.println(i);
        }



        





        sc.close();
    }
}
