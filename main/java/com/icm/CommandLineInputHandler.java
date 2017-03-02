package com.icm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineInputHandler
{
    public void run() throws IOException
    {
        int player1Hands = 0;
        int player2Hands = 0;
        CommandExecutor executor = new CommandExecutor();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String input = console.readLine();
            if (input != null)
            {
                if(input.equals("X"))
                {
                    System.out.println("Player 1: " + player1Hands);
                    System.out.println("Player 2: " + player2Hands);
                    System.exit(0);
                }
                switch (executor.execute(input))
                {
                    case 1: {
                        player1Hands++;
                        break;
                    }
                    case 2: {
                        player2Hands++;
                        break;
                    }
                    default: break;
                }
            }
        }
    }
}
