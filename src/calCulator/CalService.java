package calCulator;
import java.math.BigDecimal;
import java.lang.*;
/*
 * 计算机业务类
 */
public class CalService {
    //存储器，默认为0，用于保存需要暂时保存的计算结果
	private double store=0;
	//第一个操作数
	private String firstNum=null;
	//上次操作数
	private String lastOp=null;
	//第二个操作数、
	private String secondNum=null;
	//是否第二个操作数，如果是，点击数字键时，则在文本框中重新输入
	private boolean isSecondNum=false;
	
	//数字
	private String numString="0123456789.";
	//四则运算
	private String opString="+-*/";
	
	/*
	 * 默认构造器
	 */
	public CalService(){
		super();
	}
	
	/*
	 * 调用方法并返回计算结果
	 * @return String
	 */
	
	public String callMethod(String cmd,String text) throws Exception{
		if(cmd.equals("C")){
			return clearAll();
		}else if(cmd.equals("CE")){
			return clear(text);
		}else if(cmd.equals("Back")){
			return backSpace(text);
		}else if(numString.indexOf(cmd)!=-1){
			return catNum(cmd,text);
		}else if(opString.indexOf(cmd)!=-1){
			return setOp(cmd,text);
		}else if(cmd.equals("=")){
			return cal(text,false);
		}else if(cmd.equals("+/-")){
			return setNegative(text);
		}else if(cmd.equals("1/x")){
			return setReciprocal(text);
		}else if(cmd.equals("sqrt")){
			return sqrt(text);
		}else if(cmd.equals("%")){
			return cal(text,true);
		}else{
			return mCmd(cmd,text);
		}
	}
    /*
     * 实现存储操作命令
     * @param cmd
     *        String 操作符号
     *        @param  text    String 现在文本框的结果
     *        @return String
     */
	private String mCmd(String cmd, String text) {
		// TODO Auto-generated method stub
		if(cmd.equals("M+")){
			//如果是M+操作，刚把计算结果累计到store中
			store=MyMath.add(store,Double.valueOf(text));
		}else if(cmd.equals("Mc")){
			//如果是MC操作，则清楚store
			store=0;
		}else if(cmd.equals("MR")){
			//如果是MR操作，则把store的值读出来
			isSecondNum=true;
			return String.valueOf(store);
		}else if(cmd.equals("MS")){
			//如果是MS操作，则把计算机结果，保存到store 中
			store=Double.valueOf(text).doubleValue();
		}
		return null;
	}
    /*
     * 计算开方
     * @param text String 输入框中的值
     * @return String 封闭成字符串的结果
     */
	private String sqrt(String text) {
		// TODO Auto-generated method stub
		//将isSecondNum标志为true
		this.isSecondNum=true;
		//计算结果并返回
		
		return String.valueOf(MyMath.divide(1,Double.valueOf(text)));
	}
    /*
     * 计算倒数
     * @param text String 输入框中的值
     * @return String 封闭成字符串的结果
     */
	private String setReciprocal(String text) {
		//hTODO Auto-generated method stub
		//如果text 为0，则不求倒数
		if(text.equals("0")){
			return text;
		}
		else{
			//将isSecondNum标志为true
			this.isSecondNum=true;
			//计算结果并返回
			return String.valueOf(MyMath.divide(1,Double.valueOf(text)));
		}
	}
     /*
     * 设置正负数
     * @param text    String 输入框中的值
     * @return String 封闭成字符串的结果
     */
	private String setNegative(String text) {
		// TODO Auto-generated method stub
	    //如果text是负数，就将它变成正数
		if(text.indexOf("-")==0){
			return text.substring(1,text.length());
		}
		//否则将正数变成负数
		return text.equals("0")?text:"-"+text;
	}
  /*
   * 计算四则运算结果
   * @param text  String 输入框中的值
   * @param b    boolean 是否有"%"运算
   * @return String 封闭成字符串的计算结果
   */
	private String cal(String text, boolean b) throws Exception {
		// TODO Auto-generated method stub
		//初始化第二个操作数
		double secondResult=secondNum==null? Double.valueOf(text)
				.doubleValue():Double.valueOf(secondNum).doubleValue();
	   //如果除数为0，不处理
		if(secondResult==0&&this.lastOp.equals("/")){
			return "0";
		}
		//如果有"%"操作，则第二个操作数等于两数相乘再除以100
		if(b){
			secondResult=MyMath.multiply(Double.valueOf(firstNum),MyMath.
					divide(secondResult,100));
		}
		//四则运算，返回结果赋给第一个操作符
		if(this.lastOp.equals("+")){
			firstNum=String.valueOf(MyMath.add(Double.valueOf(firstNum),
					secondResult));
		}else if(this.lastOp.equals("-")){
			firstNum=String.valueOf(MyMath.subtract(Double.valueOf(firstNum),
					secondResult));
		}else if(this.lastOp.equals("*")){
			firstNum=String.valueOf(MyMath.subtract(Double.valueOf(firstNum),
					secondResult));
		}else if(this.lastOp.equals("/")){
			firstNum=String.valueOf(MyMath.divide(Double.valueOf(firstNum),
					secondResult));
		}
		//给第二个操作数重新赋值
		secondNum=secondNum==null?text:secondNum;
		//把isSecondNum标志为true
		this.isSecondNum=true;
		return firstNum;
	}
	
	
    /*
     * 设置操作符号
     * 
     * @param cmd   String 操作符号
     * @param text   String 输入框中的值
     * @return String   封闭成字符串的结果
     */
	private String setOp(String cmd, String text) {
		// TODO Auto-generated method stub
		//将此操作符号设置为上次的操作
		this.lastOp=cmd;
		//设置第一个操作数的值
		this.firstNum=text;
		//将第二个操作数赋值为空
		this.secondNum=null;
		//将isSecondNum标志为true
		this.isSecondNum=true;
		//返回空值
		return null;
	}
	
	
  /*
   * 连接输入的数字，每次点击数字，把新加的数字追加到后面
   * @param cmd
   *       String 操作符号
   *       @param text 
   *               String 输入框中的值
   * @return String 封闭成字符串的结果
   */
	private String catNum(String cmd, String text) {
		// TODO Auto-generated method stub
		String result=cmd;
		//如果目前的text 不等于0
		if(!text.equals("0")){
			if(isSecondNum){
				//将isSecondNum标志为false
				isSecondNum=false;
			}else{
				//刚返回结果为目前的text加上新点击的数字
				result=text+cmd;
			}
		}
		//如果有.开头的话，就在前面补0
		if(result.indexOf(".")==0){
			result="0"+result;
		}
		return result;
	}
  /*
   * 实现backspace功能
   */
	private String backSpace(String text) {
		// TODO Auto-generated method stub
	  	return text.equals("0")||text.equals("")?"0":text.substring(0, text.length()-1);
	}
  /*
   * 清除上次计算结果
   */
	private String clear(String text) {
		// TODO Auto-generated method stub
		return "0";
	}
    /*
     * 清楚所有计算结果
     */
	private String clearAll() {
		// TODO Auto-generated method stub
		//将第一第二操作数恢复成默认值
		this.firstNum="0";
		this.secondNum= null;
		return this.firstNum;
	}
	/*
	 * 返回存储器中的结果
	 */
	public double getStore(){
		return this.store;
	}
}
