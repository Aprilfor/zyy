package practice;
import java.math.BigDecimal;
import java.util.*;

public class cho1 {

	public static void main(String[] args) {
        Scanner cin=new Scanner(System.in);
        BigDecimal a;
        int b;
      while(cin.hasNext()){
        	a=cin.nextBigDecimal();
        	b=cin.nextInt();
        	a=a.pow(b);//��a��b�η�
        	//�������ַ����������������ȥ��С����ͺ����
        	String res=a.stripTrailingZeros().toPlainString();
       	//�����0��ͷ�ģ�ȥ��ǰ��0
        	if(res.startsWith("0")){
        	res=res.substring(1);
        	}
        	System.out.println(res);
        }
		
	}
}
