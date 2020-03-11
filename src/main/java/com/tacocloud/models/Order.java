package com.tacocloud.models;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class Order {
    private Long id;
    private Date createdAt;

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
}
