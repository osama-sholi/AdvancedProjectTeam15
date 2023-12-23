package edu.najah.cap.delete;

import edu.najah.cap.iam.UserProfile;

public class SoftDelete implements IDelete{
    public void delete(UserProfile user){
        System.out.println("Soft Delete");
    }
}
