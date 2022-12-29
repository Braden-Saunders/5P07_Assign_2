package me.braden.FileReaderClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import io.opentelemetry.instrumentation.annotations.WithSpan;

public class Client {
	
	private final static int NUMBER_OF_FILES = 10;
	private final static int SERVER_SOCKET_PORT = 7777;
	
	public Client() {
		for(int i = 1; i <= NUMBER_OF_FILES; i++) {
			String fileName = "file" + i + ".txt";
			new Thread(() -> readAndSend(fileName)).start();
		}
	}
	
	@WithSpan
	private void readAndSend(String fileName) {
        HttpPost post = new HttpPost("http://localhost:" + SERVER_SOCKET_PORT);

        try {
			post.setEntity(new ByteArrayEntity(Files.readAllBytes(Path.of(fileName))));
			System.out.println("Sending contents of " + fileName + " to server on listening on port " + SERVER_SOCKET_PORT);
			try(CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {
				String result = EntityUtils.toString(response.getEntity()); //We don't care about the result
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String...args) {
		new Client();
	}

}
