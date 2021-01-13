import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

//백준 1949
//우수마을
//dp
//참고: https://navy-apple.com/algorithms/BOJ/1949

public class p2 {

	 	static int n, u, v, a[] = new int[10001], dp[][] = new int[10001][2];
	    static String people[];
	    static String num[];
	    static ArrayList<Integer>[] g = new ArrayList[10001]; //ArrayList로 트리 표현
	    static boolean visited[] = new boolean[10001];
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	        
	        n = Integer.parseInt(br.readLine()); //마을 수 
	        people = br.readLine().split(" "); //각 마을의 주민 수
	        
	        long ans = 0;
	        for (int i = 1; i <= n; i++) {
	            ans += a[i] = Integer.parseInt(people[i - 1]);
	            //a[1]=Integer.parseInt(people[0]) 식으로 바꾼다.
	            //ans는 n개의 마을의 주민 합
	            g[i] = new ArrayList<>();
	        }

	        //dp배열 초기화
	        for (int i = 1; i <= n; i++)
	            for (int j = 0; j < 2; j++)
	                dp[i][j] = -1;

	        //마을 두 개씩 입력하는 것 받고 ArrayList에 추가(트리 구현)
	        for (int i = 1; i < n; i++) {
	        	num = br.readLine().split(" ");
	            u = Integer.parseInt(num[0]);
	            v = Integer.parseInt(num[1]);
	            g[u].add(v);
	            g[v].add(u);
	        }
	        
	        
	        int p = dp(1, 1, 1);
	        Arrays.fill(visited, false);
	        int q = dp(1, 0, 1);
	        
	        bw.write(Math.max(p, q) + "\n");
	        bw.flush();bw.close();
	    }

	    //최대 주민 수 구하는 함수
	    public static int dp(int v, int b, int prev) {
	        //b마을이 이미 방문되었을 경우
	    	if (dp[v][b] != -1) 
	        	return dp[v][b];
	        
	    	//b마을이 아직 방문되지 않았을 경우
	        visited[v] = true; //방문표시하고
	        dp[v][b] = (b == 1) ? a[v] : 0; //루트노드를 방문하는 경우-루트노드의 주민 수
	        
	        for (int i = 0; i < g[v].size(); i++) {
	            int next = g[v].get(i);
	            if (next != prev)
	                dp[v][b] += (b == 1) ? dp(next, 0, v) : Math.max(dp(next, 0, v), dp(next, 1, v));
	        }
	        return dp[v][b];
	    }
}
