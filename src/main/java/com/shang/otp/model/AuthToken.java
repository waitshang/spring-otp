package com.shang.otp.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by shangwei2009@hotmail.com on 2023/2/7 16:47
 */
@Data
public class AuthToken {
    @NotBlank
    private String name;
    @NotBlank
    private String uri;
}
