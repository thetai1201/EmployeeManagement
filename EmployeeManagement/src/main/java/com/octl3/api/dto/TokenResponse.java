package com.octl3.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static com.octl3.api.constants.SecurityConst.TOKEN_TYPE;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenResponse {

     String accessToken;
     String tokenType;

    public TokenResponse(String accessToken) {
        this.accessToken = accessToken;
        this.tokenType = TOKEN_TYPE;
    }
}
