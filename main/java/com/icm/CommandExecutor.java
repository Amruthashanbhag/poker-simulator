package com.icm;

import java.util.Arrays;

public class CommandExecutor
{

    public Integer execute(String input)
    {
        String[] parameters = input.split(" ");
        if (parameters.length != 10)
        {
            return -1;
        }
        Poker poker = new Poker();
        return poker.execute(Arrays.asList(parameters));
    }
}
