package edu.najah.cap.delete;

public class DeleteFactory {
    public static AbstractDelete getDelete(DeleteTypes type){
        if (DeleteTypes.HARD_DELETE.equals(type)){
            return new HardDelete();
        }
        else if (DeleteTypes.SOFT_DELETE.equals(type)){
            return new SoftDelete();
        }
        else{
            return null;
        }
    }
}
