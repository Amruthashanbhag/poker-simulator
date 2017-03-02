package com.icm;

import java.io.IOException;

public class PokerSimulator
{
    public static void main(String[] args) throws IOException
    {
        if (args.length > 0)
        {
            FileInputHandler handler = new FileInputHandler(args[0]);
            handler.run();
        }
        else
        {
            CommandLineInputHandler handler = new CommandLineInputHandler();
            handler.run();
        }
    }
}
