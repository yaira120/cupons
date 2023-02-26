package cupon_project.exceptions;

public class CompanyExistsException  extends Exception{
    public CompanyExistsException() {
        super("The company is not exists");
    }
}
