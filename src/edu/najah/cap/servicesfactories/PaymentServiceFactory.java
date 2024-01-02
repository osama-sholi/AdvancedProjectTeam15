package edu.najah.cap.servicesfactories;

import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.PaymentService;

public class PaymentServiceFactory {
    public static IPayment getPaymentService(String paymentService) throws IllegalArgumentException{
        if ("PaymentService".equals(paymentService)) {
            return new PaymentService();
        }
        else {
            throw new IllegalArgumentException("Invalid payment service");
        }
    }
}
