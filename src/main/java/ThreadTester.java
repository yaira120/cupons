import cupon_project.threads.DailyCouponJob;

public class ThreadTester {
    public static void main(String[] args) {
        Runnable r1 = new DailyCouponJob();
        Thread thread=new Thread(r1);
        thread.start();

    }
}
