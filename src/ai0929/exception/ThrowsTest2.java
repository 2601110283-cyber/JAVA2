package ai0929.exception;

import java.io.*;

public class ThrowsTest2 {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("mydata1.txt"));

            while(true){
                String line = br.readLine();
                if(line==null){
                    break;
                }

                System.out.println(line + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수가 없습니다");
        } catch (IOException e) {
            System.out.println("한줄 읽어올 때 문제가 발생했습니다.");
        }
    }
}
