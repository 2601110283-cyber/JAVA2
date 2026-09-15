package ai0915;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest3 {
    // 파일의 내용 전체를 거꾸로 출력
    static void main(String[] args) {
        try{
            StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader((new FileReader("myDataTest2.txt")));

            String line = "";
       while (true){
           line = br.readLine();
           if (line == null)
               break;
           sb.append(line).append("\n");
            }

        br.close();

       sb.reverse();
            System.out.println(sb);

    } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
