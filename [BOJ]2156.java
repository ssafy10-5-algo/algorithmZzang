import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int n = sc.nextInt();

        // wine : 포도주 잔에 들어있는 포도주의 양
        int[] wine = new int[n];
        // dp[i] : i번째 잔까지 최대로 마실 수 있는 포도주의 양
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            wine[i] = sc.nextInt();
            if (i == 0) {
                dp[i] = wine[i];
                continue;
            }
            // i번째 포도주 잔을 고른 경우
            int first = 0, second = 0;
            // // 직전의 포도주 잔을 고르지 않은 경우
            if (i >= 2) second = wine[i] + dp[i-2];
            // // 직전의 포도주 잔을 고른 경우
            if (i >= 1) first = wine[i] + wine[i-1];
            if (i >= 3) first += dp[i-3]; // i < 3인 경우 dp[i-3]을 호출하면 java.lang.ArrayIndexOutOfBoundsException 발생
            dp[i] = Math.max(first, second);

            // i번째 포도주 잔을 고르지 않은 경우
            // 예시: 1000 1000 1에서는 3번째 잔을 먹지 않는 게 더 이득이다.
            // 이 때는, dp[i-1]이 dp[i]보다 클 것이다.
            dp[i] = Math.max(dp[i-1], dp[i]);
        }

        System.out.println(dp[n-1]);

        sc.close();

    }
}
