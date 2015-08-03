package ba.bitcamp.homework14.task01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
		        //Savjet: Obicno ovakve stvari npr, Port idu u konstante
			Socket client = new Socket("localhost", 2468);

			File file = new File("src/slika.jpg");
			if (file.exists()) {
				FileInputStream stream = new FileInputStream(file);
				OutputStream os = client.getOutputStream();

				byte[] data = new byte[1024];
				int sent;

				while ((sent = stream.read(data, 0, data.length)) > 0) {
					os.write(data, 0, sent);
					os.flush();
				}
				os.close();
				System.out.println("File Sent!");
				
			} else {
				System.out.println("File not found!");
			}
			client.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
