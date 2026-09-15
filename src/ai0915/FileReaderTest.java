 package ai0915;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

    public class FileReaderTest {
        public static void main(String[] args) {
            try {
//            file을 읽어오기 위한 입력 스트링
                BufferedReader br = new BufferedReader(new FileReader("D:\\fileIO\\myDataTest1.txt"));

                String line = "";

//            파일에서 한 줄 읽어오기
                line = br.readLine();
                System.out.println(line);

                line = br.readLine();
                System.out.println(line);

                line = br.readLine();
                System.out.println(line);

//            파일인풋스트림닫기
                br.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


