package me.braden.FileReceiverServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.apache.http.HttpStatus;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

import io.opentelemetry.instrumentation.annotations.WithSpan;

public class Server {
	
	private final static int SERVER_SOCKET_PORT = 7777;
	
	private Path output = Path.of("output.txt");
	
	public Server() {
		startServer();
	}
	
	public void startServer() {
		HttpServer server = ServerBootstrap.bootstrap()
                .setListenerPort(SERVER_SOCKET_PORT)
                .registerHandler("*", (request, response, context) -> {
                	if(request.getRequestLine().getMethod().equalsIgnoreCase("post")) {
    					System.out.println("Received data.");
    					BasicHttpEntityEnclosingRequest postReq = (BasicHttpEntityEnclosingRequest) request;
    					byte[] receivedBytes = postReq.getEntity().getContent().readAllBytes();
    					new Thread(() -> writeToFile(receivedBytes)).start();
                		response.setStatusCode(HttpStatus.SC_OK);
                	} else {
                		response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
                	}
                	
                }).create();
		try {
			server.start();
			System.out.println("Server started on port " + SERVER_SOCKET_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.shutdown(0, null);
        }));
	}
	
	@WithSpan
	public void writeToFile(byte[] bytes) {
		try {
			synchronized(this) {
				Files.write(output, bytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String...args) {
		new Server();
	}

}
