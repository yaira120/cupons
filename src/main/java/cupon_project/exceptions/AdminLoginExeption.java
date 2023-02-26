package cupon_project.exceptions;

public class AdminLoginExeption extends Exception{
    public AdminLoginExeption() {
        super("Error login");
    }

    public AdminLoginExeption(String message) {
        super(message);
    }


}
