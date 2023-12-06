package com.zhurawell.base.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BaseDto<T> {

    private T pojo;

    protected BaseDto(T pojo) {
        this.pojo = pojo;
    }

    @JsonIgnore
    public T getPojo() {
        return this.pojo;
    }

    @JsonIgnore
    public void setPojo(T pojo) {
        this.pojo = pojo;
    }

    public static <R, B> List<R> fromPojoCollection(Collection<B> coll, Class<? extends BaseDto> clazz) {
        List<R> dtoCol = new LinkedList<>();
        try {
            for(B element : coll) {
                Object obj = clazz.newInstance();
                ((BaseDto) obj).setPojo(element);
                dtoCol.add((R) obj);
            }
        } catch (Exception ex) {
            //TODO add logger
        }
        return dtoCol;
    }

    public static  <R, B extends BaseDto> List<R> toPojoCollection(Collection<B> coll) {
        List<R> dtoCol = new LinkedList<>();
        for(B element : coll) {
            dtoCol.add((R) element.getPojo());
        }
        return dtoCol;
    }
}
