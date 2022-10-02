package com.tensql.requests;

public class ExitVariant extends CommandVariant  {

    public ExitVariant() {
        super.name = "exit";
    }

    @Override
    public void execute() throws Exception  {
        throw new Exception();
    }
}
