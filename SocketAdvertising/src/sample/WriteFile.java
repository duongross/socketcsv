package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteFile {

    public static void main(String[] args) {
        List<Advertising> list = ReadFile.readData("advertising.csv");
        ReadFile.writeData("new_advertising.csv", list);
    }
}
