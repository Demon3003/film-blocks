package com.zhurawell.base.data.model.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DateAndImage {
    private Date dtm;

    private String img;

    public DateAndImage(Date dtm, String img) {
        this.dtm = dtm;
        this.img = img;
    }

    @Override
    public String toString() {
        return "DateAndImage{" +
                "dtm=" + dtm +
                ", img='" + img + '\'' +
                '}';
    }
}
