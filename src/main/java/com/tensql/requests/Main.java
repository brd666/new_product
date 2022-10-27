package com.tensql.requests;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
//    private static final String URL = "jdbc:mysql://localhost:3306/new_product";
//    private static final String USERNAME = "brd";
//    private static final String PASSWORD = "root";

    private static HashMap<String, CommandVariant> initCv(Statement stmt) {
        HashMap<String, CommandVariant> result = new HashMap<>();
        List<StupidStoreRequest> temp = Initializer.initialize();

        //help
        HelpVariant helpVariant = new HelpVariant(temp);
        result.put(helpVariant.name, helpVariant);


        //exit
        ExitVariant exitVariant = new ExitVariant();
        result.put(exitVariant.name, exitVariant);

        // 1-10
//        for (int i = 1; i < 11; i++) {
//            NumberVariant cv = new NumberVariant(String.valueOf(i), stmt);// Передавать объект stupdiStoreReq
//            result.put(cv.name, cv);
//        }
        for (int i = 0; i < temp.size() ; i++) {
            NumberVariant cv = new NumberVariant(String.valueOf(i), stmt, temp);// Передавать объект stupdiStoreReq
            result.put(String.valueOf(i+1), cv);
        }

        return result;
    }

    public static void main(String[] args) {
        ConnectBase connectBase = new ConnectBase();
        connectBase.toConnect();

            HashMap<String, CommandVariant> cv = initCv(connectBase.stmt);

            Scanner reader = new Scanner(System.in);
            try {
                while (true) {
                    String input = reader.nextLine();
                    CommandVariant inputcv = cv.get(input);
                    if (inputcv == null) {
                        System.out.println("Wrong command, use on of these commands.");
                    } else {
                        System.out.print(inputcv.execute());//Исправил!
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            try {
                connectBase.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
