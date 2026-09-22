package ai0922;

import java.io.*;

public class LAB_FileCopy {
    static void main(String[] args) throws FileNotFoundException {
        //File Inputstream 생성
        try{
            //File Inputstream생성
        BufferedReader br = new BufferedReader(new FileReader("myData1.txt"));
        //File outputStream생성
            FileWriter fw = new FileWriter("newFile.txt");
            String line = "";

            while (true){
                line = br.readLine();
                if (line == null)
                    break;


                fw.write(line + "\n");
            }
            System.out.println("myData1.txt File이 newFile.txt 파일로 복사 됨");
            fw.close();
        br.close();
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
