package cupon_project.threads;

import cupon_project.dbdao.CouponDbdao;
import lombok.SneakyThrows;

public class DailyCouponJob implements Runnable{
    private boolean start=false;
    CouponDbdao couponDbdao=new CouponDbdao();
    @SneakyThrows
    @Override
    public void run() {
        while (!start){
            couponDbdao.jobDeleteCoupons();
            try {
                Thread.sleep(24*60*60);
            } catch (InterruptedException e) {
                e.printStackTrace();
              //  stopTheThread();
            }

        }


    }

    public void stopTheThread(){
        this.start=true;
    }
}
