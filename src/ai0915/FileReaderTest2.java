package ai0915;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest2 {
    static void main(String[] args) {
        try{
        BufferedReader br = new BufferedReader((new FileReader("myDataTest2.txt")));

            String line = "";
       while (true){
           line = br.readLine();
           if (line == null)
               break;
                System.out.println(line);
            }

        br.close();
    } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
