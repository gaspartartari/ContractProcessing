package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {
    public static void main(String[] args) throws Exception {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        int menu = 0;
        while(menu == 0){
                try{    
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
                    menu =1;
                        
                    }
                    catch (InputMismatchException e ){
                        System.out.println("Incorrect format. Please enter a valid value");
                        sc.nextLine();
                        menu =0;
                        
                    }
                    catch (DateTimeParseException e){
                        System.out.println("Incorrect format. Please enter date format (dd/MM/yyyy)");
                        sc.nextLine();
                        menu=0;
                        
                }
        }    
        sc.close();
    }
}
