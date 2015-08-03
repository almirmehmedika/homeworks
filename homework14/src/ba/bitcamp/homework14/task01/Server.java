package ba.bitcamp.homework14.task01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(2468);
			System.out.println("Server activated!");
			Socket client = server.accept();
			System.out.println("Client connected!");
			
			File file = new File("src/download.jpg");
			FileOutputStream stream = new FileOutputStream(file);
			InputStream is = client.getInputStream();
			
			byte[] data = new byte[1024];
			int sent;
			while ((sent = is.read(data, 0, data.length)) > 0) {
				stream.write(data, 0, sent);
				stream.flush();
			}
			System.out.println("File received!");
			is.close();
			
			File file2 = new File("src/index.html");
			String html = "";
			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(file2));
				while (reader.ready()) {
					html += reader.readLine() + "\n";
				}
				//Borisu sam takodjer vec preporocio pa mozes i ti razmisliti da ovakve stvari radis sa StringBuilder-om
				html += "<p>Client IP address: " + client.getInetAddress().getHostAddress() + "</p>\n";
				html += "<p>Time of download: " + Calendar.getInstance().getTime() + "</p>\n";
				
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file2));
			writer.write(html);
			writer.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
