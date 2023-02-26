package cupon_project.exceptions;

public class CustomerEmailException extends Exception{
    public CustomerEmailException() {
        super("Email already exists Please select another email");
    }
}
