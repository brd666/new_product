package com.tensql.requests;

public class ExitVariant implements CommandVariant  {

    String name = "exit";

    @Override
    public void execute() throws Exception  {
        throw new Exception();
    }
}
