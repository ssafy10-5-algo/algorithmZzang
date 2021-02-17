import java.io.*;
import java.util.*;

public class Main {
	static int row;
	static int column;
	static int curCheese = 0;
	static int curCount = 0;
  // 치즈가 남아있는 위치에 -1을 저장해두는 배열
	static int[][] cheese;
  // 치즈가 있던 자리에 치즈가 녹은 시간을 저장해두는 배열
	static int[][] cheeseCount;
  // 단위시간마다 해당 시간에 녹은 치즈의 개수를 저장해두는 정수
  static int answer = 0;
  // DFS를 돌 때 방문 여부를 판단하는 배열
	static boolean[][] visited;
	
  // 가장자리에서 시작, DFS를 돌면서 마주치는 치즈를 녹임
  // x와 y는 좌표, count는 단위시간 (한번 DFS를 돌면서 치즈를 녹이는 과정이 끝날 때마다 +1)
	static void dfs(int x, int y, int count) {
    // 이미 방문한 곳이면 return
		if (visited[x][y]) return ;
    // 치즈가 있는 자리에 저장된 단위시간이 현재 단위시간보다 크면 return
		if (cheeseCount[x][y] > count) {
			if (cheese[x][y] != -1)
				return;
		}
    // 치즈가 남아있으면,
		if (cheese[x][y] == -1) {
      // 저장된 단위시간이 0이면, (치즈가 녹아야 하는데 녹지 않았다는 뜻)
			if (cheeseCount[x][y] == 0) {
        // 단위시간을 갱신
				cheeseCount[x][y] = count+1;
        // 치즈를 녹임 (-1이어야 치즈가 남아있다는 뜻)
				cheese[x][y] = 0;
        // 치즈를 녹였으므로 녹은 치즈의 개수를 1 늘림
				answer++;
			}
			return ;
		}
    // DFS 돌리기~
		visited[x][y] = true;
		if (y >= 1) dfs(x, y-1, count);
		if (y < column) dfs(x, y+1, count);
		if (x < row) dfs(x+1, y, count);
		if (x >= 1) dfs(x-1, y, count);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		column = Integer.parseInt(st.nextToken());
    // 일부러 모든 배열을 넉넉히 가로 세로 1칸씩 더 넓게 선언함
    // 혹시라도 가장자리에 치즈가 있을수도 있으니까.. (문제에선 아니라했지만)
		cheese = new int[row+2][column+2];
		cheese[0] = new int[column+2];
		for (int i = 1; i <= row; i++) {
			int[] eachRow = new int[column+2];
			eachRow[0] = 0;
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= column; j++) {
				int point = Integer.parseInt(st.nextToken());
				if (point == 1) eachRow[j] = -1;
				else eachRow[j] = point;
			}
			eachRow[column+1] = 0;
			cheese[i] = eachRow;
		}
		cheese[row+1] = new int[column+2];
		cheeseCount = new int[row+2][column+2];
    // 만약 치즈가 하나도 없이 시작했다면, 예외처리
		boolean startZero = true;
		for (int i = 0; i < row+2; i++) {
			for (int j = 0; j < column+2; j++) {
				if (cheese[i][j] != 0) startZero = false;
			}
		}
    // 치즈가 다 녹을 때까지 DFS를 빙빙 돈다!
		boolean flag = false;
		while (!flag) {
			flag = true;
			answer = 0;
			visited = new boolean[row+2][column+2];
			dfs(0, 0, curCount);
			for (int i = 0; i < row+2; i++) {
				for (int j = 0; j < column+2; j++) {
					if (cheese[i][j] != 0) flag = false;
				}
			}
			curCount++;
		}
    // 위에서 언급한 예외처리
		if (startZero) curCount--;
		System.out.println(curCount + "\n" + answer);
	}

}
