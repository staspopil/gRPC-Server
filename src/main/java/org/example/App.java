package org.example;

import Services.SubscribeService;
import Services.ReceiveMessageSerivice;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(8080)
                .addService(new ReceiveMessageSerivice())
                .addService( new SubscribeService())
                .build();

        server.start();
        System.out.println("server started");

        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            String cmd = scanner.nextLine();
            if (cmd.equals("exit"))
            {
                server.awaitTermination();
            }
        }
    }
}
