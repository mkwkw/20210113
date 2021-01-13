//���� 2248
//������ ã��
//dp
//n�ڸ��� ������ �����
//1�� ������ l�� ������ �� ã��
//i��° �� ã��
//����: https://huiung.tistory.com/123
import java.util.*;
public class p1 {
	private static long dp[][];
	private static String answer="";
	
	//������ ã�� �Լ�
	private static void findfunc(int n, int l, long i) {
		//n==0�� ��, ������ �������� ����
		if(n==0)
			return;
		
		//l==0�� ��, answer�� 1�� ������ 0�̹Ƿ� 0���θ� �̷���� ���ڿ�
		if(l==0) {
			for(int j=0; j<n; j++)
				answer += "0";
			return;
		}
		
		
		long skip = 0;
		//n-1���� ��Ʈ���� 1�� ������ �� �ִ� ����� �� ��� ���ϱ�
		for(int k=0; k<=l; k++) {
			skip += dp[n-1][k];
			
		}
		
		//skip�� i���� ũ�ų� ������ anwer���� 0�� ��ġ��Ų��.
		if(skip >= i) {
			answer +="0";
			findfunc(n-1, l, i);
		}
		
		//skip�� i���� ������ anwer���� 1�� ��ġ��Ų��. 
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
		long i = sc.nextLong();
	
		dp= new long [32][32];
		
		//0���� ��Ʈ �� 1�� 0���� ���
		dp[0][0]=0; //���� ���� �ص� ��� ����
		
		//t���� ��Ʈ �� 1�� 0���� ���, 1�� t���� ��� ���� 1�������ۿ� ����.
		for(int t=1; t<32; t++) {
			dp[t][0]=1;
			dp[t][t]=1;
			
		}
		
		
		//2�̻��� j, 1�̻��� k�� ���ؼ� jCk = j-1Ck + j-1Ck-1 ���
		for(int j=2; j<=n; j++) {
			for(int k=1; k<=j; k++) {
				dp[j][k] = dp[j-1][k-1]+dp[j-1][k];
			}
		}
		
		findfunc(n,l,i);
		System.out.println(answer);
		sc.close();
	}

}
