package sample;

import java.net.*;
import java.util.List;
import java.io.*;

public class ChatClient {
	public static void main(String args[]) {
		try {
			String serverName = "localhost";
			int serverPort = 1999;
			
			System.out.println("Establishing connection. Please wait ...");
			Socket socket = new Socket(serverName, serverPort);
			System.out.println("Connected: " + socket);

			BufferedReader console = 
					new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream streamOut = 
					new DataOutputStream(socket.getOutputStream());
			/**
			 * Copy csv to Server
			 */
			System.out.println("Copy file Advertising");
	        List<Advertising> list_client = ReadFile.readData("advertising.csv");
	        try {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(list_client);        
            }
            	catch (IOException e)
            {
                e.printStackTrace();
            } 			
			/**
			 * Ask Server tv, radio, news
			 */
			String line = "";
			while (!line.equals(".bye")) {
				try {	       
					line = console.readLine();
					streamOut.writeUTF(line);
					streamOut.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		            String message;
					try {
						message = (String) ois.readObject();
						System.out.println(message);
					} catch (ClassNotFoundException e) {

						e.printStackTrace();
					}
					ois.close();
				} catch (IOException ioe) {
					System.out.println("Sending error: " + ioe.getMessage());
				}
			}
			console.close();
			streamOut.close();
			socket.close();	
		} catch (UnknownHostException uhe) {
			System.out.println("Host unknown: " + uhe.getMessage());
		} catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
	}
}
