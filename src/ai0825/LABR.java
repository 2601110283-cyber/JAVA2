package ai0825;

import java.util.Random;

public class LABR {
    static void main(String[] args) {
        String[] wisesay = {
                "성공은 최종적인 것이 아니며, 실패는 치명적인 것이 아니다. 중요한 것은 계속하려는 용기다. – 윈스턴 처칠",

        "당신이 할 수 있다고 믿든, 할 수 없다고 믿든, 당신이 언제나 옳다. – 헨리 포드",

        "삶이 있는 한 희망은 있다. – 마르쿠스 툴리우스 키케로",

                "상상력은 지식보다 중요하다. 지식은 한계가 있지만 상상력은 세상을 둘러싼다. – 알베르트 아인슈타인",

        "인생은 곱셈이다. 어떤 기회가 와도 내가 제로(0)면 아무런 의미가 없다. – 나카무라 미츠루",

        "가장 어두운 밤도 끝날 것이고, 태양은 결국 떠오를 것이다. – 빅토르 위고",

        "행복은 습관이다. 그것을 몸에 지니라. – 엘버트 허버드",

        "아무것도 하지 않으면 아무 일도 일어나지 않는다. – 기시미 이치로",

        "모든 성취의 시작점은 갈망이다. – 나폴레온 힐",

        "내일은 내일의 태양이 뜬다. – 마거릿 미첼"


        };

        Random random =new Random();
        int randomIndex = random.nextInt(wisesay.length);
        System.out.print("오늘의 명언 :" + wisesay[randomIndex]);
    }
}
