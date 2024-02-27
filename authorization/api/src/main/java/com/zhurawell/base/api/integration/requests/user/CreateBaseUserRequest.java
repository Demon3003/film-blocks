package com.zhurawell.base.api.integration.requests.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateBaseUserRequest  {

    private String login;

    private String email;

}
