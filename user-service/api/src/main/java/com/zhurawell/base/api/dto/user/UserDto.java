package com.zhurawell.base.api.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.zhurawell.base.data.model.user.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto  {

    private BigInteger id;

    private String firstName;

    private String lastName;

    private String login;

    private String email;

    private String image;

    private LocalDate registrationDate;

    private Integer status;

}
