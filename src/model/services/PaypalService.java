package model.services;

public class PaypalService implements OnlinePaymetnService {
    
    
    public Double interest(Double ammount, Integer month){
        return ammount * 0.01 * month;
    }

    public Double paymentFee(Double ammount){
       return ammount * 0.02;
    }
    
}
