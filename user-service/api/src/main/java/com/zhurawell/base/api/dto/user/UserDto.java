package com.zhurawell.base.api.dto.user;

import com.zhurawell.base.data.model.user.Status;
import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.api.dto.BaseDto;
import com.zhurawell.base.api.dto.grants.RoleDto;

import java.math.BigInteger;
import java.util.Date;

public class UserDto extends BaseDto<User> {

    public UserDto(User user) {
        super(user);
    }

    public UserDto() {
        super(new User());
    }


    public BigInteger getId() {
        return getPojo().getId();
    }

    public void setId(BigInteger id) {
        getPojo().setId(id);
    }

    public String getFirstName() {
        return getPojo().getFirstName();
    }

    public void setFirstName(String firstName) {
        getPojo().setFirstName(firstName);
    }

    public String getLastName() {
        return getPojo().getLastName();
    }

    public void setLastName(String lastName) {
        getPojo().setLastName(lastName);
    }

    public String getLogin() {
        return getPojo().getLogin();
    }

    public void setLogin(String login) {
        getPojo().setLogin(login);
    }

    public String getPassword() {
        return getPojo().getPassword();
    }

    public void setPassword(String password) {
        getPojo().setPassword(password);
    }

    public String getEmail() {
        return getPojo().getEmail();
    }

    public void setEmail(String email) {
        getPojo().setEmail(email);
    }

    public String getImage() {
        return getPojo().getImage();
    }

    public void setImage(String image) {
        getPojo().setImage(image);
    }

    public Date getRegistrationDate() {
        return getPojo().getRegistrationDate();
    }

    public void setRegistrationDate(Date registrationDate) {
        getPojo().setRegistrationDate(registrationDate);
    }

    public Status getStatus() {
        return getPojo().getStatus();
    }

    public void setStatus(Status status) {
        getPojo().setStatus(status);
    }

    public RoleDto getRole() {
        return new RoleDto(getPojo().getRole());
    }

    public void setRole(RoleDto role) {
        getPojo().setRole(role.getPojo());
    }
}
