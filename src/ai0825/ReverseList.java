package ai0825;

import java.util.Arrays;
import java.util.Collections;

public class ReverseList {
    static void main(String[] args) {
        String[] newjeans = {"다니엘", "하니", "민지", "혜인", "혜린"};
        System.out.println("원본:" + Arrays.toString(newjeans));
        Collections.reverse(Arrays.asList(newjeans));
        System.out.println("반전(역순):" + Arrays.toString(newjeans));
    }
}
