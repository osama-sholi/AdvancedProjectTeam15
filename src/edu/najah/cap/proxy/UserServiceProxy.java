package edu.najah.cap.proxy;

import edu.najah.cap.delete.DeletedUsersArchive;
import edu.najah.cap.exceptions.*;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;

import java.util.logging.Level;

import static edu.najah.cap.logs.MyLogging.log;


public class UserServiceProxy implements IUserService {
    private UserService userService;

    public UserServiceProxy() {
        if (userService == null) {
            userService = new UserService();
        }
    }

    @Override
    public void addUser(UserProfile user) {
        try {
            validateUsername(user.getUserName());
        } catch (UserDeletedException | BlankUsernameException | UserAlreadyExistsException | BadRequestException e) {
            log(Level.SEVERE, e.getMessage(), "UserServiceProxy", "addUser");
        } catch (NotFoundException e) {
            userService.addUser(user);
        }
    }

    @Override
    public void updateUser(UserProfile user) throws NotFoundException, SystemBusyException, BadRequestException {
        userService.updateUser(user);
    }

    @Override
    public void deleteUser(String userName) throws NotFoundException, SystemBusyException, BadRequestException {
        DeletedUsersArchive.addDeletedUser(userName);
        userService.deleteUser(userName);
    }

    @Override
    public UserProfile getUser(String userName) throws NotFoundException, SystemBusyException, BadRequestException {
        return userService.getUser(userName);
    }

    private void validateUsername(String username) throws BlankUsernameException, UserDeletedException, UserAlreadyExistsException, NotFoundException, BadRequestException {

            if (username.trim().isBlank()) {
                throw new BlankUsernameException("User name cannot be empty");
            }
            if (DeletedUsersArchive.isUserDeleted(username)) {
                throw new UserDeletedException("Username unavailable as it is deleted");
            }
            while (true) {
                try {
                    userService.getUser(username);
                    throw new UserAlreadyExistsException("User already exists");
                } catch (SystemBusyException e) {
                    log(Level.WARNING, "System Busy, Trying Again...", "UserServiceProxy", "validateUsername");
                }
            }
    }
}
