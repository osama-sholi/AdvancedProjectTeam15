package edu.najah.cap.delete;

import edu.najah.cap.iam.UserProfile;

public class HardDelete implements IDelete{
    public void delete(UserProfile user){
        System.out.println("Hard Delete");
    }
}
