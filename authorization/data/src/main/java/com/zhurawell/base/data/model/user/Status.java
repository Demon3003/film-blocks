package com.zhurawell.base.data.model.user;


import java.util.Map;

public enum Status {

    NEW("new", 1),
    ACTIVE("active", 2),
    INACTIVE("inactive", 3);

    Status(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    private final String name;

    private final Integer id;

    private static final Map<Integer, Status> ID_TO_STATUS =  Map.of(
            1, NEW,
            2, ACTIVE,
            3, INACTIVE
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