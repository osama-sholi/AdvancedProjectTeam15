package edu.najah.cap.servicesfactories;

import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.PaymentService;

public class PaymentServiceFactory {
    public static IPayment getPaymentService(PaymentServiceTypes type) throws IllegalArgumentException{
        if (PaymentServiceTypes.PAYMENT_SERVICE.equals(type)) {
            return new PaymentService();
        }
        else {
            throw new IllegalArgumentException("Invalid payment service");
        }
    }
}
