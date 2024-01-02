package edu.najah.cap.delete;

import edu.najah.cap.exceptions.BadRequestException;

public interface IDelete {
    public abstract void delete(String username) throws BadRequestException;
}

