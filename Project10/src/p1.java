//백준 2248
//이진수 찾기
//dp
//n자리의 이진수 만들기
//1의 개수가 l개 이하인 수 찾기
//i번째 수 찾기
import java.util.*;
public class p1 {
	private static long dp[][];
	private static String answer="";
	
	private static void findfunc(int n, int l, long i) {
		//n==0일 때, 이진수 생성되지 않음
		if(n==0)
			return;
		
		//l==0일 때, answer는 1의 개수가 0이므로 0으로만 이루어진 문자열
		if(l==0) {
			for(int j=0; j<n; j++)
				answer += "0";
			return;
		}
		
		long skip = 0;
		for(int k=0; k<=l; k++) {
			skip += dp[n-1][k];
		}
		
		if(skip >= i) {
			answer +="0";
			findfunc(n-1, l, i);
		}
		
		else {
			answer += "1";
			findfunc(n-1, l-1, i-skip);
		}
		
		return;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int l = sc.nextInt();
		int i = sc.nextInt();
	
		dp= new long [32][32];
		
		dp[0][0]=1;
		for(int t=1; t<32; t++) {
			dp[t][0]=1;
			dp[t][t]=1;
			
		}
		
		for(int j=2; j<=n; j++) {
			for(int k=1; k<=j; k++) {
				dp[j][k] = dp[j-1][k-1]+dp[j-1][k];
			}
		}
		
		findfunc(n,l,i);
		System.out.println(answer);
	}

}
