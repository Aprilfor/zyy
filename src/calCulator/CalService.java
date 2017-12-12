package calCulator;
import java.math.BigDecimal;
import java.lang.*;
/*
 * �����ҵ����
 */
public class CalService {
    //�洢����Ĭ��Ϊ0�����ڱ�����Ҫ��ʱ����ļ�����
	private double store=0;
	//��һ��������
	private String firstNum=null;
	//�ϴβ�����
	private String lastOp=null;
	//�ڶ�����������
	private String secondNum=null;
	//�Ƿ�ڶ���������������ǣ�������ּ�ʱ�������ı�������������
	private boolean isSecondNum=false;
	
	//����
	private String numString="0123456789.";
	//��������
	private String opString="+-*/";
	
	/*
	 * Ĭ�Ϲ�����
	 */
	public CalService(){
		super();
	}
	
	/*
	 * ���÷��������ؼ�����
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
     * ʵ�ִ洢��������
     * @param cmd
     *        String ��������
     *        @param  text    String �����ı���Ľ��
     *        @return String
     */
	private String mCmd(String cmd, String text) {
		// TODO Auto-generated method stub
		if(cmd.equals("M+")){
			//�����M+�������հѼ������ۼƵ�store��
			store=MyMath.add(store,Double.valueOf(text));
		}else if(cmd.equals("Mc")){
			//�����MC�����������store
			store=0;
		}else if(cmd.equals("MR")){
			//�����MR���������store��ֵ������
			isSecondNum=true;
			return String.valueOf(store);
		}else if(cmd.equals("MS")){
			//�����MS��������Ѽ������������浽store ��
			store=Double.valueOf(text).doubleValue();
		}
		return null;
	}
    /*
     * ���㿪��
     * @param text String ������е�ֵ
     * @return String ��ճ��ַ����Ľ��
     */
	private String sqrt(String text) {
		// TODO Auto-generated method stub
		//��isSecondNum��־Ϊtrue
		this.isSecondNum=true;
		//������������
		
		return String.valueOf(MyMath.divide(1,Double.valueOf(text)));
	}
    /*
     * ���㵹��
     * @param text String ������е�ֵ
     * @return String ��ճ��ַ����Ľ��
     */
	private String setReciprocal(String text) {
		//hTODO Auto-generated method stub
		//���text Ϊ0��������
		if(text.equals("0")){
			return text;
		}
		else{
			//��isSecondNum��־Ϊtrue
			this.isSecondNum=true;
			//������������
			return String.valueOf(MyMath.divide(1,Double.valueOf(text)));
		}
	}
     /*
     * ����������
     * @param text    String ������е�ֵ
     * @return String ��ճ��ַ����Ľ��
     */
	private String setNegative(String text) {
		// TODO Auto-generated method stub
	    //���text�Ǹ������ͽ����������
		if(text.indexOf("-")==0){
			return text.substring(1,text.length());
		}
		//����������ɸ���
		return text.equals("0")?text:"-"+text;
	}
  /*
   * ��������������
   * @param text  String ������е�ֵ
   * @param b    boolean �Ƿ���"%"����
   * @return String ��ճ��ַ����ļ�����
   */
	private String cal(String text, boolean b) throws Exception {
		// TODO Auto-generated method stub
		//��ʼ���ڶ���������
		double secondResult=secondNum==null? Double.valueOf(text)
				.doubleValue():Double.valueOf(secondNum).doubleValue();
	   //�������Ϊ0��������
		if(secondResult==0&&this.lastOp.equals("/")){
			return "0";
		}
		//�����"%"��������ڶ���������������������ٳ���100
		if(b){
			secondResult=MyMath.multiply(Double.valueOf(firstNum),MyMath.
					divide(secondResult,100));
		}
		//�������㣬���ؽ��������һ��������
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
		//���ڶ������������¸�ֵ
		secondNum=secondNum==null?text:secondNum;
		//��isSecondNum��־Ϊtrue
		this.isSecondNum=true;
		return firstNum;
	}
	
	
    /*
     * ���ò�������
     * 
     * @param cmd   String ��������
     * @param text   String ������е�ֵ
     * @return String   ��ճ��ַ����Ľ��
     */
	private String setOp(String cmd, String text) {
		// TODO Auto-generated method stub
		//���˲�����������Ϊ�ϴεĲ���
		this.lastOp=cmd;
		//���õ�һ����������ֵ
		this.firstNum=text;
		//���ڶ�����������ֵΪ��
		this.secondNum=null;
		//��isSecondNum��־Ϊtrue
		this.isSecondNum=true;
		//���ؿ�ֵ
		return null;
	}
	
	
  /*
   * ������������֣�ÿ�ε�����֣����¼ӵ�����׷�ӵ�����
   * @param cmd
   *       String ��������
   *       @param text 
   *               String ������е�ֵ
   * @return String ��ճ��ַ����Ľ��
   */
	private String catNum(String cmd, String text) {
		// TODO Auto-generated method stub
		String result=cmd;
		//���Ŀǰ��text ������0
		if(!text.equals("0")){
			if(isSecondNum){
				//��isSecondNum��־Ϊfalse
				isSecondNum=false;
			}else{
				//�շ��ؽ��ΪĿǰ��text�����µ��������
				result=text+cmd;
			}
		}
		//�����.��ͷ�Ļ�������ǰ�油0
		if(result.indexOf(".")==0){
			result="0"+result;
		}
		return result;
	}
  /*
   * ʵ��backspace����
   */
	private String backSpace(String text) {
		// TODO Auto-generated method stub
	  	return text.equals("0")||text.equals("")?"0":text.substring(0, text.length()-1);
	}
  /*
   * ����ϴμ�����
   */
	private String clear(String text) {
		// TODO Auto-generated method stub
		return "0";
	}
    /*
     * ������м�����
     */
	private String clearAll() {
		// TODO Auto-generated method stub
		//����һ�ڶ��������ָ���Ĭ��ֵ
		this.firstNum="0";
		this.secondNum= null;
		return this.firstNum;
	}
	/*
	 * ���ش洢���еĽ��
	 */
	public double getStore(){
		return this.store;
	}
}
