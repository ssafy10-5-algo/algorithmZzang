// Java에서 우선순위 큐의 우선순위를 재정의하는 방법을 처음 써봐서 난항을 겪었네요ㅠㅠ
// 그래서 구글링 살짝 해서 Java에서 우선순위 재정의하는 법을 배우고.. Comparable implement해서 사용하는 방법 배웠습니다..
// C++에선 Priority Queue를 생성할 때 조건이랑 부합하는 Pair를 만들어 Queue에 넣는 방식을 사용했었는데,
// Java는 역시 객체지향이니까 클래스를 만들고 Comparable implement 사용해서 쓰더라구요 몰랐습니다 흑흑
// 그런데 알고나니까 뭔가 Java가 더 재미있어진 것 같아요. 클래스 내부를 원하는대로 구성할 수 있어서 확장성도 더 높아보였기 떄문에 좋았습니다..
// 이번 문제를 골라주신 희승님께 감사하다는 말씀 드리겠습니다.. 다른 우선순위 큐 문제도 몇 개 더 풀어보겠습니다ㅎㅎ

import java.util.*;

// 클래스명이 Pair인데 처음에 순서쌍으로 구상해서 풀었던 흔적입니다..ㅎㅎ 앞으론 더 열심히 C++스러움을 버리도록 노력하겠습니다 후후..
class Pair implements Comparable<Pair> {
	int num;
	
	public Pair(int num) {
		this.num = num;
	}
	
	public int getNum() {
		return num;
	}
	
	@Override
	public int compareTo(Pair o) {
      // 절댓값이 같은 경우 더 작은 값을 먼저 출력합니다.
		if (Math.abs(getNum()) == Math.abs(o.getNum())) return getNum() - o.getNum();
      // 절댓값이 다른 경우 절댓값이 더 작은 값을 먼저 출력합니다.
		return Math.abs(getNum()) - Math.abs(o.getNum());
	}

}

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		Vector<Integer> vector = new Vector<>();
		
		// 입력을 받아 우선순위 큐에 삽입함과 동시에 벡터에 정답을 저장합니다.
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int x = sc.nextInt();
			if (x == 0) {
				Pair pair = pq.poll();
				vector.add(pair == null ? 0 : pair.num);
			} else pq.add(new Pair(x));
		}
		
		// 입력이 끝나면 벡터에 있는 값들을 출력하면 끝!
		while (!vector.isEmpty()) {
			System.out.println(vector.get(0));
			vector.remove(0);
		}
		
		sc.close();
		
	}
	
}
