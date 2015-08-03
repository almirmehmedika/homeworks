package ba.bitcamp.homework16.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;

import org.codehaus.jackson.map.ObjectMapper;

public class ServerJSON {

	public static void main(String[] args) {
		
		File file = new File("src/README.txt");
		//Nije greska, ali Java ce staviti warning pa stavi ovo da ti se ne zali.
		//ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while(reader.ready()) {
				list.add(reader.readLine());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Pitanje sta si sa ovim zelio postici posto Math.random() vraca broj izmedju 0 i 1 i kada to pomozis prakticno ces dobiti uvijek index = 0?
		//Ovo bas nije lijep nacin citanja iz liste posto mala greska i dobit ces od liste index of bound i ne preporucujem ti ovo da radis
		//Ako si vec napunio listu uradi for each nad listom i uzimaj od pocetka do kraja vrijednosti
		String message = list.get((int)(Math.random()*list.size()));
		String timestamp = Calendar.getInstance().getTime().toString();		
		BitResponse br = new BitResponse(message, timestamp);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			ServerSocket server = new ServerSocket(9999);
			System.out.println("Server activated...");
			Socket client = server.accept();
			System.out.println("Client connected");
			OutputStream os = client.getOutputStream();
			mapper.writeValue(os, br);
			System.out.println("Message sent");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
