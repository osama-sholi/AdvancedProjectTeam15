package edu.najah.cap.proxy;

import edu.najah.cap.exceptions.*;
import edu.najah.cap.delete.DeletedUsersArchive;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;

import java.util.logging.Logger;
public class UserServiceProxy implements IUserService {

        private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(UserServiceProxy.class.getName());

        public UserServiceProxy() {
            if (userService == null) {
                userService = new UserService();
            }
        }

        @Override
        public void addUser(UserProfile user) {
            try {
                if (user.getUserName().trim().isBlank()) {
                    throw new BlankUsernameException("User name cannot be empty");
                }
                if (userService.getUser(user.getUserName()) != null) {
                    throw new UserAlreadyExistsException("User already exists");
                }
                if (DeletedUsersArchive.isUserDeleted(user.getUserName())) {
                    throw new UserDeletedException("User is deleted");
                }
                userService.addUser(user);
            } catch (UserAlreadyExistsException | UserDeletedException | BlankUsernameException e) {
                LOGGER.warning(e.getMessage());
            } catch (NotFoundException | NullPointerException e) {
                LOGGER.warning(e.getMessage());
            } catch (Exception e) {
                LOGGER.warning(e.getMessage());
            }
        }

        @Override
        public void updateUser(UserProfile user) {
            try {
                if (user.getUserName().trim().isBlank()) {
                    throw new BlankUsernameException("User name cannot be empty");
                }
                if (userService.getUser(user.getUserName()) == null) {
                    throw new UserAlreadyExistsException("User does not exist");
                }
                if (DeletedUsersArchive.isUserDeleted(user.getUserName())) {
                    throw new UserDeletedException("User is deleted");
                }
                userService.updateUser(user);
            } catch (UserAlreadyExistsException | UserDeletedException | BlankUsernameException e) {
                LOGGER.warning(e.getMessage());
            } catch (NotFoundException | NullPointerException e) {
                LOGGER.warning(e.getMessage());
            } catch (Exception e) {
                LOGGER.warning(e.getMessage());
            }
        }

        @Override
        public void deleteUser(String userName) {
            try {
                DeletedUsersArchive.addDeletedUser(userName);
                userService.deleteUser(userName);
            } catch (NotFoundException | BadRequestException | SystemBusyException e) {
                LOGGER.warning(e.getMessage());
            } catch (Exception e) {
                LOGGER.warning(e.getMessage());
            }

        }

        @Override
        public UserProfile getUser(String userName) {
            try {
                return userService.getUser(userName);
            } catch (NotFoundException | BadRequestException | SystemBusyException e) {
                LOGGER.warning(e.getMessage());
            } catch (Exception e) {
                LOGGER.warning(e.getMessage());
            }
            return null;
        }
}
