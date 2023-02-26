package cupon_project.exceptions;

public class CompanyLoginExeption extends Exception{
    public CompanyLoginExeption() {
        super("Company is not exists");

    }

    public CompanyLoginExeption(String message) {
        super(message);
    }
}
