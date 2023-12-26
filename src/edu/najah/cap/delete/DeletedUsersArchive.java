package edu.najah.cap.delete;

import java.util.ArrayList;
import java.util.List;

public class DeletedUsersArchive {
    private static List<String> deletedUsers = new ArrayList<>();

    public static void addDeletedUser(String username) {
        deletedUsers.add(username);
    }

    public static boolean isUserDeleted(String username) {
        return deletedUsers.contains(username);
    }
}