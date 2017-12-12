package calCulator;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;//���ֹ�����֮�߽�����������������֣��� �� �� �� �� �У����԰�������������λ���е��κ�һ��
import java.awt.GridLayout;//���񲼾ֹ�����������ָ�������Ǽ��м��еģ�Ҳ���԰��������С
import java.awt.Color;//�ṩ������ɫ�ռ����
import java.awt.Component;
import java.awt.Container;

import javax.swing.JPanel;//����࣬���ڳ�ʼ�����
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

/*
 * һЩ�¼����������
 * ��ӦһЩ�߼��¼���������������߲˵�֮��ģ�������TextField���������ActionEvent������Ӧ��
 * ����Button���������ActionEvent�ͻ������Ӧ��ActionEvent�Բ�ͬ�������������Ҳ��һ��ô
 */
import java.awt.Dimension;//Dimension���Ե������������Ŀ�ȡ��߶ȷ�װ��һ�����С�

/*
 * ��ʵ���һ�δ���ԭ��
 * JFrame frame=new JFrame();
 * �Լ��Ž�������һ��������
 * frame.add(panel);
 * ʵ�����Ž������У���Ļ�������ӡ��
 * JFrame��JPanel����Container�����࣬����Container�������࣬����Ҳ����ʵ�ֲ�����
 * 
 */
/*
 * �����������
 */
public class CalFrame extends JFrame{
      //��ʾ��������textField
	private JTextField textField=null;
	//��һ�����鱣��MC,MR,MS,M+�Ȳ�����
	private String[] mOp={"MC","MR","MS","M+"};
	//��һ�����鱣����������
	private String[] rOp={"Back","CE","C"};
	//��һ�����鱣������������������
	private String[] nOp={"7","8","9","/","sqrt","4","5","6","*",
			"%","1","2","3","-","1/x","0","+/-",".","+","="};
	//M������־��ť
	private JButton button=null;
	//ҵ���߼���
	private CalService service=new CalService();
	//���������
	private ActionListener actionListener=null;
	//����panel�Ŀ�
	private final int PRE_WIDTH=360;
	//����panel�ĸ�
	private final int PRE_HEIGHT=250;
	/*
	 * Ĭ�Ϲ�����
	 */
	public CalFrame(){
		super();
		initialize();
	}
	/*
	 * ��ʼ������
	 */
	private void initialize(){
		//���ô��ڵı���
	     this.setTitle("������");
	     
	     //��Ʋ��ֹ�����
	     this.setLayout(new BorderLayout(10,1));
	     //��������
	     Container container=getContentPane();
	     //���Ӽ��������
	     JPanel panel=new JPanel();
	     panel.setLayout(new BorderLayout(10,1));//��Ʋ��ֹ�����
	     
	     panel.add(getTextField(),BorderLayout.NORTH);
	     
	     panel.setPreferredSize(new Dimension(PRE_WIDTH,PRE_HEIGHT));
	     //borderlayout��north�з���һ��panel��panel�ĸ߶ȿ���ͨ������ʵ�֣�������������һ���߶�Ϊ100��panel������洰�ڱ仯��
	     
	     //������ߴ洢������
	     JButton[] mButton=getMButton();
	     //�½�һ��panel,���ڷ��ð�ť
	     JPanel panel1=new JPanel();
	     //���ò��ֹ�����
	     panel1.setLayout(new GridLayout(5,1,0,5));
	     //�������Ӱ�ť
	     for(JButton b:mButton){
	    	 panel1.add(b);
	     }
	     
	     //���ӽ��������
	     JButton[] rButton=getRButton();
	     JPanel panel2=new JPanel();
	     panel2.setLayout(new BorderLayout(1,5));
	     //�½�һ��panel�����ڷ��ð�ť
	     JPanel panel21=new JPanel();
	     //���ò��ֹ�����
	     panel21.setLayout(new GridLayout(1,3,3,3));
	     //�������Ӱ�ť
	     for(JButton b:rButton){
	    	 panel21.add(b);
	     }
	     
	     //�������������������
	     JButton[] nButton=getNButton();
	     //�½�һ��panel�����ڷ��ð�ť
	     JPanel panel22=new JPanel();
	     //���ò��ֹ�����
	     panel22.setLayout(new GridLayout(4,5,3,5));
	     //�������Ӱ�ť
	     for(JButton b:nButton){
	    	 panel22.add(b);
	     }
	     //�������ӵ����Ž�frame
	     panel2.add(panel21,BorderLayout.NORTH);
	     panel2.add(panel22,BorderLayout.CENTER);
	     panel.add(panel1,BorderLayout.WEST);
	     panel.add(panel2,BorderLayout.CENTER);
	     
	     container.add(panel);
	     
        
	}
	/*
	 * �˷���������ü����������������
	 */
	private JButton[] getNButton() {
		// TODO Auto-generated method stub
		//������鱣����Ҫ����Ϊ��ɫ�Ĳ�����
		String[] redButton={"/","*","-","+","="};
		JButton[] result=new JButton[nOp.length];
		for(int i=0;i<this.nOp.length;i++){
			//�½���ť
			JButton b=new JButton(this.nOp[i]);
			//Ϊ��ť�����¼�
			b.addActionListener(getActionListener());
			//��redButton ���򣬲ſ���ʹ��binarySearch�������۰�������������ַ���
			Arrays.sort(redButton);
			//�����������redButton�г���
			if(Arrays.binarySearch(redButton, nOp[i])>=0){
				b.setForeground(Color.red);
			}else{
				b.setForeground(Color.blue);
			}
			result[i]=b;
		}
		return result;
	}
	/*
	 * �˷�����ü������Ľ��������
	 */
	private JButton[] getRButton() {
		// TODO Auto-generated method stub
		JButton[] result=new JButton[rOp.length];
		for(int i=0;i<this.rOp.length;i++){
			//�½���ť
			JButton b=new JButton(this.rOp[i]);
			//Ϊ��ť�����¼�
			b.addActionListener(getActionListener());
			//���ð�ť��ɫ
			b.setForeground(Color.red);
			result[i]=b;
		}		
		return result;	
	}
	/*
	 * �˷�����ü������Ĵ洢������
	 */
	private JButton[] getMButton() {
		// TODO Auto-generated method stub
		JButton[] result=new JButton[mOp.length+1];
		result[0]=getButton();
		for(int i=0;i<this.mOp.length;i++){
			//�½���ť
			JButton b=new JButton(this.mOp[i]);
			//Ϊ��ť�����¼�
			b.addActionListener(getActionListener());
			//���ð�ť��ɫ
			b.setForeground(Color.red);
			result[i+1]=b;
		}
		return result;
	}
	/*
	 * ���������ʼ�������
	 */
	private JTextField getTextField() {
		// TODO Auto-generated method stub
		if(textField==null){
			//����Ĭ��ֵΪ0
			textField=new JTextField("0");
			//����Ϊ���ɱ༭
			textField.setEditable(false);
			//���ñ���Ϊ��ɫ
			textField.setBackground(Color.white);
		}
		return textField;
	}
	private JButton getButton(){
		if(button==null){
			//����Ĭ��ֵΪ0
			button=new JButton();
		}
		return button;
	}
	/*
	 * �������������ȡ������
	 */
	public ActionListener getActionListener(){
		if(actionListener==null){
			actionListener=new ActionListener(){
				/*
				 * ʵ�ֽӿ��е�actionPerformed����
				 */
				public void actionPerformed(ActionEvent e){
					String cmd=e.getActionCommand();
					String result=null;
					try{
						//����������
						result=service.callMethod(cmd,textField.getText());		
					}catch(Exception el){
						System.out.println(el.getMessage());
					}
					//����Button�ı��
					if(cmd.indexOf("MC")==0){
						button.setText("");
					}else if(cmd.indexOf("M")==0&&service.getStore()>0){
						button.setText("M");
					}
					//���ü�����
					if(result!=null){
						textField.setText(result);
					}
				}
		};		
		}
		return actionListener;
	}
}