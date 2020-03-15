package com.tacocloud.models;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private int id;
    private Date placedAt;

    @NotBlank(message = "name is mandatory")
    private String customer_name;

    @NotBlank(message = "street is mandatory")
    private String street;

    @NotBlank(message = "city is mandatory")
    private String city;

    @NotBlank(message = "zip code is mandatory")
    private String zip;

    @CreditCardNumber(message = "this is not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "expiration date must have format MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "invalid CVV code")
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }
}
