package cupon_project.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Company {
    private int id;
    private String name;
    private String email;
    private String password;
    List<Coupon> coupons;

    public Company(String name,String email,String password){
        this.name=name;
        this.email=email;
        this.password=password;
    }


    //  private String getPassword() {
      //  return password;
   // }
}
