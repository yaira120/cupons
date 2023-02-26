package cupon_project.exceptions;

public class CustomerLoginExeption extends Exception{
    public CustomerLoginExeption() {
        super("Customer is not exists");
    }

    public CustomerLoginExeption(String message) {
        super(message);
    }
}
