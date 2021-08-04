package com.pablojvm;

import com.pablojvm.application.ValidationService;
import com.pablojvm.infrastructure.LoginHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

// TODO: 8/4/21 create endpoint post user
// TODO: 8/4/21 create endpoint login user

public class Main
{
    public static void main(String[] args) throws IOException
    {
        HttpServer server = HttpServer
                .create(new InetSocketAddress("localhost", 8081), 0);
        ThreadPoolExecutor threadPoolExecutor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        server.createContext("/login", new LoginHandler(new ValidationService()));
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("server on in port 8081");
    }
}
