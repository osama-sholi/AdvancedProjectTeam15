package edu.najah.cap.delete;

public class DeleteFactory {
    public static IDelete getDelete(DeleteTypes type) throws IllegalArgumentException{
        if (DeleteTypes.HARD_DELETE.equals(type)){
            return new HardDelete();
        }
        else if (DeleteTypes.SOFT_DELETE.equals(type)){
            return new SoftDelete();
        }
        else{
            throw new IllegalArgumentException("Invalid delete type");
        }
    }
}
