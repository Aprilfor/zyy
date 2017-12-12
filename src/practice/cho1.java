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
        	a=a.pow(b);//求a的b次方
        	//传换成字符串，如果是整数，去掉小数点和后面的
        	String res=a.stripTrailingZeros().toPlainString();
       	//如果是0开头的，去点前导0
        	if(res.startsWith("0")){
        	res=res.substring(1);
        	}
        	System.out.println(res);
        }
		
	}
}
