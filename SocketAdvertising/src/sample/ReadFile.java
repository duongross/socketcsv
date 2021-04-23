package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class ReadFile {

    public static List<Advertising> readData(String fileName) {
        List<Advertising> advs = new ArrayList<Advertising>();

        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                StringTokenizer tokens = new StringTokenizer(data, ",");

              
                String tv = tokens.nextToken();
                String radio = tokens.nextToken();
                String news = tokens.nextToken();
                String sales = tokens.nextToken();

               
                advs.add(new Advertising(tv,radio,news,sales));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return advs;
    }

    public static void writeData(String fileName, List<Advertising> advs) {
        try {
            FileWriter writer = new FileWriter(fileName);

            for (int i=0; i<advs.size()-1;i++) {     
                String tv = advs.get(i).getTV();
                String radio = advs.get(i).getRadio();
                String news = advs.get(i).getNews();
                String sales = advs.get(i).getNews();
                writer.write(tv + "," + radio + "," + news + "," + sales+"\n");
            }
            writer.write(advs.get(advs.size()-1).getTV() + "," + advs.get(advs.size()-1).getRadio() + "," +
            		advs.get(advs.size()-1).getNews() + "," + advs.get(advs.size()-1).getNews());
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

