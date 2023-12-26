package edu.najah.cap.delete;

public abstract class AbstractDelete {
    public abstract void delete(String username);

    protected <T> void deleteData(DeleteIterator<T> iterator, String dataType) {

        if (!iterator.isDataFound()) {
            System.out.println("No " + dataType + " found for the given username");
        } else {
            while (iterator.hasNext()) {
                iterator.deleteCurrent();
            }

            System.out.println(dataType + " Deleted");
        }
    }
}

