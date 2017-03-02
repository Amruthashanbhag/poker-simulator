package com.icm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInputHandler
{
    String fileName;
    public FileInputHandler(String fileName)
    {
        this.fileName = fileName;
    }

    public void run() throws IOException
    {
        int player1Hands = 0;
        int player2Hands = 0;
        CommandExecutor executor = new CommandExecutor();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try
        {
            String input = br.readLine();
            while (input != null)
            {
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
                input = br.readLine();
            }
            System.out.println("Player 1: " + player1Hands);
            System.out.println("Player 2: " + player2Hands);
        }
        finally
        {
            br.close();
        }
    }
}
