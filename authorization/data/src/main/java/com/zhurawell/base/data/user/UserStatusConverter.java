package com.zhurawell.base.data.user;

import com.zhurawell.base.data.model.user.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserStatusConverter implements AttributeConverter<Status, Integer> {


    public Integer convertToDatabaseColumn(Status status) {
        return status == null ? null : status.getId();
    }

    public Status convertToEntityAttribute(Integer statusId) {
        return Status.getStatusById(statusId);
    }
}
