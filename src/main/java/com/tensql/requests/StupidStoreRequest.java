package com.tensql.requests;

public class StupidStoreRequest {
    private final String sqlRequests;
    private final String description;
    private final int number;

    StupidStoreRequest(String sqlRequests, String description, int number) {
        this.sqlRequests = sqlRequests;
        this.description = description;
        this.number = number;
    }

    public String getSqlRequests() {
        return sqlRequests;
    }
    public String getDescription() {
        return description;
    }
    public int getNumber() {
        return number;
    }
}
