package model.services;


public interface OnlinePaymetnService {
    Double paymentFee(Double ammount);
    Double interest(Double ammount, Integer months);
}
