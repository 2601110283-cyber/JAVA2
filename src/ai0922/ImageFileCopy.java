package ai0922;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageFileCopy {
    public static void main(String[] args) {
        try {
//            파일 input스트림 생성
            FileInputStream fin = new FileInputStream("fire.jpg");
            FileOutputStream fout = new FileOutputStream("firecopy.jpg");

            int data;
            while ((data = fin.read()) != -1) {
                fout.write((byte) data);
            }

            fin.close();
            fout.close();

            System.out.println("파일 복사 완료");

        } catch (IOException e) {
            System.out.println("파일을 읽는 중 오류가 발생했습니다.");
        }
    }
    
}
