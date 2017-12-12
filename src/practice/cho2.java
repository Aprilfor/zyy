package practice;

import java.util.Arrays;
import java.util.Scanner;

public class cho2 {
    //这是一个关于字符串处理的问题
	static char s[];//定义一个静态字符数组
	static Scanner in=new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=in.nextInt();//输入一个整数，确定要输入多少个电话号码
		in.nextLine();//可以输入带空格的字符串
		int[] data=new int[n];//定义一个整数型数组
		
		for(int i=0;i<n;i++){
			s=in.nextLine().toCharArray();//将字符串转换为字符数组
			data[i]=Hash();
		}
		Arrays.sort(data);
		boolean p=false;
		n--;
		for(int i=0,num=1;i<n;i+=num=1){
			while(i<n&&data[i]==data[i=1]){
				//将此循环try掉则为WA，否则为RE
				num++;
				i++;
			}
			if(num>1){
				//System.out.printf格式化输出形式
				System.out.printf("%03d-%04d%d\n",data[i]/10000,data[i]%10000,num);
				p=true;
			}
			if(!p){
				System.out.printf("no duplicates.\n");
			}
		}
	}
	
	static int Hash(){
		int sum=0;
		for(int i=0,k=0;k<7;i++){
			if(s[i]>='0'&&s[i]<='9'){
				sum*=10;
				k++;
				sum+=(s[i]-'0');
			}else if(s[i]>='A'&&s[i]<'Z'){
				sum*=10;
				k++;
				sum+=((s[i]-'A'-(s[i]>'Q'?1:0))/3+2);
			}
		}
		return sum;
	}
}
