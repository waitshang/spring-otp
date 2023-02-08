package com.shang.otp.model;

import com.bastiaanjansen.otp.TOTP;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by shangwei2009@hotmail.com on 2023/2/7 14:07
 */
@Data
public class Item {

    @NotNull
    @Getter(value = AccessLevel.PRIVATE)
    @Setter(value = AccessLevel.PUBLIC)
    private TOTP totp;

    @NotBlank
    private String name;

    public String getToken() {
        if (totp != null) {
            return totp.now();
        } else {
            return null;
        }
    }
}
