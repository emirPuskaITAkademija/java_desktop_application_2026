package com.academy.four.dao.connection;

public enum ConnectionProperty {
    URL("jdbc:mysql://localhost:3306/sports"),
    USERNAME("root"),
    PASSWORD("root"),
    ;

    private final String value;

    ConnectionProperty(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
