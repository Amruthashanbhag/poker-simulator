package com.icm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineInputHandler
{
    public void run() throws IOException
    {
        CommandExecutor executor = new CommandExecutor();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String input = console.readLine();
            if (input != null)
            {
                System.out.println(executor.execute(input));
            }
        }
    }


}
