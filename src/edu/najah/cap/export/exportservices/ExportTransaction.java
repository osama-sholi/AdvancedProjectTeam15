package edu.najah.cap.export.exportservices;

import edu.najah.cap.compress.PDFConvertor;
import edu.najah.cap.exceptions.FileDeletionException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.exceptions.UnqualifiedUserException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserType;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.servicesfactories.PaymentServiceFactory;
import edu.najah.cap.servicesfactories.PaymentServiceTypes;
import edu.najah.cap.servicesfactories.UserServiceFactory;
import edu.najah.cap.servicesfactories.UserServiceTypes;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;

import static edu.najah.cap.logs.MyLogging.log;

public class ExportTransaction implements IExportService {
    @Override
    public String exportData(String user, String path) throws Exception {
        log(Level.INFO, "Exporting transactions to local storage...", "ExportTransaction", "exportTransaction");
        String outputPath = path + user + "-Transactions.txt";
        try (PrintWriter transactionWriter = new PrintWriter(new FileWriter(outputPath))) {
            IPayment paymentService = PaymentServiceFactory.getPaymentService(PaymentServiceTypes.PAYMENT_SERVICE);
            IUserService userService = UserServiceFactory.getUserService(UserServiceTypes.USER_SERVICE);
            transactionWriter.println("Balance: " + paymentService.getBalance(user));
            transactionWriter.println();
            List<Transaction> transactions = null;
            UserType userType = null;
            while (true) {
                try {
                    userType = userService.getUser(user).getUserType();
                    transactions = paymentService.getTransactions(user);
                    break;
                } catch (SystemBusyException e) {
                    log(Level.WARNING, "System Busy, Trying Again...", "ExportTransaction", "exportTransaction");
                } catch (NotFoundException e) {
                    if(userType == null){
                        throw new NotFoundException(e.getMessage());
                    }
                    throw new UnqualifiedUserException("User is not qualified for exporting transactions");
                }
            }
            if (userType == UserType.NEW_USER) {
                throw new UnqualifiedUserException("User is not qualified to export transactions");
            }
            for (Transaction transaction : transactions) {
                transactionWriter.println("User Name: " + transaction.getUserName());
                transactionWriter.println("Amount: " + transaction.getAmount());
                transactionWriter.println("Description: " + transaction.getDescription());
                transactionWriter.println();
            }
            transactionWriter.close();
            return PDFConvertor.ConvertTOPdf(outputPath);
        } catch (UnqualifiedUserException e) {
            log(Level.WARNING, e.getMessage(), "ExportTransaction", "exportTransaction");
            try {
                File file = new File(outputPath);
                if (!file.delete()) {
                    throw new FileDeletionException("File Deletion Failed");
                }
            } catch (FileDeletionException x) {
                log(Level.SEVERE, x.getMessage(), "PDFConvertor", "ConvertTOPdf");
            }
            return null;
        }

    }
}
