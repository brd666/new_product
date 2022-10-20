package com.tensql.requests;

import java.util.List;

public class HelpVariant implements CommandVariant {

    String name = "help";
    private final List<StupidStoreRequest> sqlList;

    public HelpVariant(List<StupidStoreRequest> sqlList) {
        this.sqlList = sqlList;
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sqlList.size(); i++) {
            sb.append(i + 1).append(". ").append(sqlList.get(i).getDescription()).append("\n");
        }
        return sb.toString();
    }
}
