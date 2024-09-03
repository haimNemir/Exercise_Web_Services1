package Exercise1.Repositories.Exceptions;

public class AlreadyExistException extends Exception {
    public AlreadyExistException(String massage) {
        super(massage);
    }
}
