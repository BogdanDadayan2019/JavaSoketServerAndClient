package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", 8080);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String inputLine;
            out.println(1);
            System.out.println("Сообщение для сервера: 1");
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Сервер ответил: " + inputLine);
                int number = Integer.valueOf(inputLine);

                if (number >= 10) {
                    break;
                }
                number++;

                out.println(number);
                System.out.println("Сервер ответил: " + number);
                Thread.sleep(2000);
            }
            System.out.println("отключение...");
        } catch (Throwable cause) {
            System.out.println("connection error: " + cause.getMessage());
        }
    }
}
