import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

//���� 1949
//�������
//dp
//����: https://navy-apple.com/algorithms/BOJ/1949

public class p2 {

	 	static int n, u, v, a[] = new int[10001], dp[][] = new int[10001][2];
	    static String people[];
	    static String num[];
	    static ArrayList<Integer>[] g = new ArrayList[10001]; //ArrayList�� Ʈ�� ǥ��
	    static boolean visited[] = new boolean[10001];
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	        
	        n = Integer.parseInt(br.readLine()); //���� �� 
	        people = br.readLine().split(" "); //�� ������ �ֹ� ��
	        
	        long ans = 0;
	        for (int i = 1; i <= n; i++) {
	            ans += a[i] = Integer.parseInt(people[i - 1]);
	            //a[1]=Integer.parseInt(people[0]) ������ �ٲ۴�.
	            //ans�� n���� ������ �ֹ� ��
	            g[i] = new ArrayList<>();
	        }

	        //dp�迭 �ʱ�ȭ
	        for (int i = 1; i <= n; i++)
	            for (int j = 0; j < 2; j++)
	                dp[i][j] = -1;

	        //���� �� ���� �Է��ϴ� �� �ް� ArrayList�� �߰�(Ʈ�� ����)
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

	    //�ִ� �ֹ� �� ���ϴ� �Լ�
	    public static int dp(int v, int b, int prev) {
	        //b������ �̹� �湮�Ǿ��� ���
	    	if (dp[v][b] != -1) 
	        	return dp[v][b];
	        
	    	//b������ ���� �湮���� �ʾ��� ���
	        visited[v] = true; //�湮ǥ���ϰ�
	        dp[v][b] = (b == 1) ? a[v] : 0; //��Ʈ��带 �湮�ϴ� ���-��Ʈ����� �ֹ� ��
	        
	        for (int i = 0; i < g[v].size(); i++) {
	            int next = g[v].get(i);
	            if (next != prev)
	                dp[v][b] += (b == 1) ? dp(next, 0, v) : Math.max(dp(next, 0, v), dp(next, 1, v));
	        }
	        return dp[v][b];
	    }
}
