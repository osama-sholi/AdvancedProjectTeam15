package edu.najah.cap.export;

import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.Transaction;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ExportTransaction {
    public static void exportTransaction(String user, IPayment paymentService) {
        String oldUser = user;
        String newUser = oldUser.substring(4);
        int number = Integer.parseInt(newUser);
        if (number >= 8) {
            try (PrintWriter transactionWriter = new PrintWriter(new FileWriter(user + " Transaction.txt"))) {
                transactionWriter.println("Balance: " + paymentService.getBalance(user));
                transactionWriter.println();
                for (Transaction transaction : paymentService.getTransactions(user)) {
                    transactionWriter.println("User Name: " + transaction.getUserName());
                    transactionWriter.println("Amount: " + transaction.getAmount());
                    transactionWriter.println("Description: " + transaction.getDescription());
                    transactionWriter.println();
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
