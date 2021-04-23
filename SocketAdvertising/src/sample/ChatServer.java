package sample;

import java.net.*;
import java.util.*;
import java.util.List;
import java.io.*;

public class ChatServer {
	public static void main(String args[]) {
		try {
			int port = 1999;
			ObjectOutputStream out = null;
			ObjectInputStream in = null;
			
			ServerSocket server = new ServerSocket(port);
			System.out.println("Server started: " + server);
			System.out.println("Waiting for a client ...");

			Socket socket = server.accept();
			System.out.println("Client accepted: " + socket);

			// open stream
			DataInputStream streamIn = new DataInputStream(socket.getInputStream());
			out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

			boolean done = false;
			while (!done) {
				/***
				 * Write advertising.csv from Client.
				 */
				try {
					List<Advertising> list_server = new ArrayList<Advertising>();
					Object object;
					try {
						object = in.readObject();
						list_server =  (ArrayList<Advertising>) object;
	                    ReadFile.writeData("new_advertising.csv", list_server);
	                    System.out.println("Copy file successful");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} catch (IOException ioe) {
					done = true;
				}
				//******************************************************************************
				try {
					String line = streamIn.readUTF();
					List<Advertising> list_server = ReadFile.readData("new_advertising.csv");
					/***
					 * Get total tv, radio, news
					 */
					double total=0;
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					while(!done) {
						if (line.equals("tv")) {
							System.out.println("Client ask Server about the total of tv\n");
							for (Advertising adv : list_server) {
								total=total+Double.parseDouble(adv.getTV());
							}
							oos.writeObject("Hi Client, The total $ of TV is: "+total);
							done=true;
						}
						if (line.equals("radio")) {
							System.out.println("Client ask Server about the total of radio\n");
							for (Advertising adv : list_server) {
								total=total+Double.parseDouble(adv.getRadio());
							}
							oos.writeObject("Hi Client, The total $ of Radio is: "+total);
							done=true;
						}
						if (line.equals("news")) {
							System.out.println("Client ask Server about the total of news\n");
							for (Advertising adv : list_server) {
								total=total+Double.parseDouble(adv.getNews());
							}
							oos.writeObject("Hi Client, The total $ of News is: "+total);
							done=true;
						}
						done = line.equals(".bye");
					}
					//******************************************************************************
					done = line.equals(".bye");
				} catch (IOException ioe) {
					done = true;
				}
			}
			out.close();
			in.close();
			streamIn.close();
			socket.close();
			server.close();
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
}
