package cupon_project.exceptions;

public class CouponException extends Exception{
    public CouponException() {
        super("Coupon error");
    }

    public CouponException(String message) {
        super(message);
    }
}

