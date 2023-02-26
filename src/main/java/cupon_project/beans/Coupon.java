package cupon_project.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Coupon {
    private int id;
    private int companyID;
    private Category category;
    private String title;
    private String description;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private int amount;
    private double price;
    private String image;

    public Coupon( Category category, String title, String description, Date startDate, Date endDate, int amount, double price, String image) {


        this.category =category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }
}

