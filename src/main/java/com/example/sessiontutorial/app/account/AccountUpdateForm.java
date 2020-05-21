package com.example.sessiontutorial.app.account;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountUpdateForm implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;

    // (2)
    @NotNull(groups = { Wizard1.class })
    @Size(min = 1, max = 255, groups = { Wizard1.class })
    private String name;

    @NotNull(groups = { Wizard1.class })
    @Size(min = 1, max = 255, groups = { Wizard1.class })
    @Email(groups = { Wizard1.class })
    private String email;

    @NotNull(groups = { Wizard1.class })
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthday;

    @NotNull(groups = { Wizard1.class })
    @Size(min = 7, max = 7, groups = { Wizard1.class })
    private String zip;

    @NotNull(groups = { Wizard1.class })
    @Size(min = 1, max = 255, groups = { Wizard1.class })
    private String address;

    @Size(min = 16, max = 16, groups = { Wizard2.class })
    private String cardNumber;

    @DateTimeFormat(pattern = "yyyy-MM")
    private Date cardExpirationDate;

    @Size(min = 1, max = 255, groups = { Wizard2.class })
    private String cardSecurityCode;

    @NotNull(groups = { Wizard2.class })
    @Size(min = 1, max = 255, groups = { Wizard2.class })
    private String creditLimit;

    private String creditCharge;

    public String getLastFourOfCardNumber() {
        if (cardNumber == null) {
            return "";
        }
        return cardNumber.substring(cardNumber.length() - 4);
    }

    public static interface Wizard1 {

    }

    public static interface Wizard2 {

    }

}
