package practice;

import java.util.Arrays;
import java.util.Scanner;

public class cho2 {
    //����һ�������ַ������������
	static char s[];//����һ����̬�ַ�����
	static Scanner in=new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=in.nextInt();//����һ��������ȷ��Ҫ������ٸ��绰����
		in.nextLine();//����������ո���ַ���
		int[] data=new int[n];//����һ������������
		
		for(int i=0;i<n;i++){
			s=in.nextLine().toCharArray();//���ַ���ת��Ϊ�ַ�����
			data[i]=Hash();
		}
		Arrays.sort(data);
		boolean p=false;
		n--;
		for(int i=0,num=1;i<n;i+=num=1){
			while(i<n&&data[i]==data[i=1]){
				//����ѭ��try����ΪWA������ΪRE
				num++;
				i++;
			}
			if(num>1){
				//System.out.printf��ʽ�������ʽ
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
