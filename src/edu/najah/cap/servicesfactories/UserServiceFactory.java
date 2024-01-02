package edu.najah.cap.servicesfactories;

import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserService;
import edu.najah.cap.proxy.UserServiceProxy;

public class UserServiceFactory {
    public static IUserService getUserService(String userService) throws IllegalArgumentException{
        if ("UserService".equals(userService)) {
            return new UserService();
        }
        if ("UserServiceProxy".equals(userService)) {
            return new UserServiceProxy();
        }
        else {
            throw new IllegalArgumentException("Invalid user service");
        }
    }
}
