package com.tensql.requests;

public class ExitVariant implements CommandVariant  {

    String name = "exit";

    @Override
    public String execute() throws Exception  {
         throw new Exception();
    }
}
