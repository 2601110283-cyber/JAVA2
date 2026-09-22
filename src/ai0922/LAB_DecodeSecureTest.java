package ai0922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LAB_DecodeSecureTest {
    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            String line = "";
            String decure = "";
            BufferedReader br = new BufferedReader(new FileReader("seure.txt"));
            FileWriter fw = new FileWriter("decodeSecure.txt");


            while (true) {
                decure = "";
                line = br.readLine();

                if (line == null)
                    break;


                for (int i = 0; i < line.length(); i++) {
                    int num = (int) line.charAt(i);
                    num -= 100;
                    decure += (char) num;
                }
                fw.write(decure + "\n");
            }


            fw.close();
            br.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    }

