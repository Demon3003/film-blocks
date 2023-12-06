package com.zhurawell.base.data.model.user;


import java.util.Map;

public enum Status {

    ACTIVE("active", 1),
    INACTIVE("inactive", 2);

    Status(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    private final String name;

    private final Integer id;

    private static final Map<Integer, Status> ID_TO_STATUS =  Map.of(
            1, ACTIVE,
            2, INACTIVE
    );

    public String getStatusName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public static Status getStatusById(Integer id) {
        return ID_TO_STATUS.get(id);
    }

}