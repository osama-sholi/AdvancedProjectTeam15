package edu.najah.cap.servicesfactories;

import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserService;
import edu.najah.cap.proxy.UserServiceProxy;

public class UserServiceFactory {
    public static IUserService getUserService(UserServiceTypes type) throws IllegalArgumentException{
        if (UserServiceTypes.USER_SERVICE.equals(type)) {
            return new UserService();
        }
        if (UserServiceTypes.USER_SERVICE_PROXY.equals(type)) {
            return new UserServiceProxy();
        }
        else {
            throw new IllegalArgumentException("Invalid user service");
        }
    }
}
