import java.io.*;
import java.util.*;

class Location {
	int x;
	int y;
	public Location (int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static char[][] yard;
	static boolean[][] visited;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int row;
	static int column;
	static int sheepTotal;
	static int wolfTotal;
	
	// 영역마다 돌릴 bfs 함수
	public static void bfs(Location start, char[][] yard, boolean[][] visited) {
		Queue<Location> queue = new LinkedList<>();
		queue.add(start);
		visited[start.x][start.y] = true;
		// 해당 영역에 존재하는 양, 늑대의 수 (나중에 양, 늑대 수를 비교해서 제거할 때 사용)
		int sheep = 0;
		int wolf = 0;
		// 같은 영역에 있는 경우를 찾아서 양과 늑대의 수를 더해나감
		while(!queue.isEmpty()) {
			Location top = queue.poll();
			switch (yard[top.x][top.y]) {
			case 'o': sheep++; break;
			case 'v': wolf++; break;
			}
//			System.out.printf("%d %d\n", top.x, top.y);
			for (int[] movingDirection : move) {
				int newX = top.x + movingDirection[0];
				int newY = top.y + movingDirection[1];
				if (newX >= 0 && newY >= 0 && newX < row && newY < column && !visited[newX][newY] && yard[newX][newY] != '#') {
					Location newLocation = new Location(newX, newY);
					queue.add(newLocation);
					visited[newX][newY] = true;
				}
				
			}
		}
		// 양과 늑대의 수 중 더 많은 것만 반영 (적은 동물은 전부 사망.. 같을 때는 늑대가 이김)
		if (sheep > wolf) {
			sheepTotal += sheep;
		} else {
			wolfTotal += wolf;
		}
//		System.out.println("finished bfs");
		
	}
	
	public static void main(String[] args) throws Exception {
		// 입력 받을 거 받고 마당 정보 채워넣기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		column = Integer.parseInt(st.nextToken());
		yard = new char[row][column];
		visited = new boolean[row][column];
		for (int i = 0; i < row; i++) {
			yard[i] = br.readLine().toCharArray();
		}
		
		// bfs 돌리기
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (!visited[i][j] && yard[i][j] != '#') bfs(new Location(i, j), yard, visited);
			}
		}
		
		System.out.printf("%d %d", sheepTotal, wolfTotal);
		
	}
	
}
