package com.tensql.requests;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class NumberVariant implements CommandVariant {
    private final Statement stmt;
    public String name;
    private final List<StupidStoreRequest> sqlList;


    public NumberVariant(String name, Statement stmt, List<StupidStoreRequest> sqlList) {
        this.name = name;
        this.stmt = stmt;
        this.sqlList = sqlList;
    }


    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();

        try {
//            ResultSet result = stmt.executeQuery(Requests.query(name));
            ResultSet result = stmt.executeQuery(sqlList.get(Integer.parseInt(name)).getSqlRequests());
            ResultSetMetaData rsmd = result.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                sb.append(String.format("%-30s", rsmd.getColumnName(i+1)));
            }
            sb.append("\n");
            while (result.next()) {
                for (int i = 0; i < columnCount; i++) {
                    sb.append(String.format("%-30s", result.getString(i+1)));
                }
                sb.append("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
