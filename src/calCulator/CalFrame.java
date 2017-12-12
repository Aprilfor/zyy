package calCulator;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;//布局管理器之边界管理器，含有五个格局，上 中 下 左 右 中，可以把组件放在这五个位置中的任何一个
import java.awt.GridLayout;//网格布局管理器，必须指定网格是几行几列的，也可以包含其间距大小
import java.awt.Color;//提供用于颜色空间的类
import java.awt.Component;
import java.awt.Container;

import javax.swing.JPanel;//组件类，用于初始化面板
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

/*
 * 一些事件处理方面的类
 * 相应一些高级事件，比如鼠标点击或者菜单之类的，但是在TextField中用鼠标点击ActionEvent不会响应，
 * 而在Button上鼠标点击，ActionEvent就会产生响应，ActionEvent对不同的组件触发条件也不一样么
 */
import java.awt.Dimension;//Dimension：对单个对象的组件的宽度、高度封装在一个类中。

/*
 * 该实验第一次错误原因：
 * JFrame frame=new JFrame();
 * 以及放进容器的一个操作：
 * frame.add(panel);
 * 实现面板放进容器中，屏幕的输出打印。
 * JFrame和JPanel都是Container的子类，所以Container是最终类，用它也可以实现操作。
 * 
 */
/*
 * 界面对象设置
 */
public class CalFrame extends JFrame{
      //显示计算结果的textField
	private JTextField textField=null;
	//用一个数组保存MC,MR,MS,M+等操作符
	private String[] mOp={"MC","MR","MS","M+"};
	//用一个数组保存结果操作符
	private String[] rOp={"Back","CE","C"};
	//用一个数组保存数字与其他操作符
	private String[] nOp={"7","8","9","/","sqrt","4","5","6","*",
			"%","1","2","3","-","1/x","0","+/-",".","+","="};
	//M操作标志按钮
	private JButton button=null;
	//业务逻辑类
	private CalService service=new CalService();
	//定义监听器
	private ActionListener actionListener=null;
	//设置panel的宽
	private final int PRE_WIDTH=360;
	//设置panel的高
	private final int PRE_HEIGHT=250;
	/*
	 * 默认构造器
	 */
	public CalFrame(){
		super();
		initialize();
	}
	/*
	 * 初始化界面
	 */
	private void initialize(){
		//设置窗口的标题
	     this.setTitle("计算器");
	     
	     //设计布局管理器
	     this.setLayout(new BorderLayout(10,1));
	     //设置容器
	     Container container=getContentPane();
	     //增加计算输入框
	     JPanel panel=new JPanel();
	     panel.setLayout(new BorderLayout(10,1));//设计布局管理器
	     
	     panel.add(getTextField(),BorderLayout.NORTH);
	     
	     panel.setPreferredSize(new Dimension(PRE_WIDTH,PRE_HEIGHT));
	     //borderlayout在north中放入一个panel，panel的高度可以通过这样实现；这样就设置了一个高度为100的panel，宽度随窗口变化。
	     
	     //增加左边存储操作键
	     JButton[] mButton=getMButton();
	     //新建一个panel,用于放置按钮
	     JPanel panel1=new JPanel();
	     //设置布局管理器
	     panel1.setLayout(new GridLayout(5,1,0,5));
	     //迭代增加按钮
	     for(JButton b:mButton){
	    	 panel1.add(b);
	     }
	     
	     //增加结果操作键
	     JButton[] rButton=getRButton();
	     JPanel panel2=new JPanel();
	     panel2.setLayout(new BorderLayout(1,5));
	     //新建一个panel，用于放置按钮
	     JPanel panel21=new JPanel();
	     //设置布局管理器
	     panel21.setLayout(new GridLayout(1,3,3,3));
	     //迭代增加按钮
	     for(JButton b:rButton){
	    	 panel21.add(b);
	     }
	     
	     //增加数字与其他运算符
	     JButton[] nButton=getNButton();
	     //新建一个panel，用于放置按钮
	     JPanel panel22=new JPanel();
	     //设置布局管理器
	     panel22.setLayout(new GridLayout(4,5,3,5));
	     //迭代增加按钮
	     for(JButton b:nButton){
	    	 panel22.add(b);
	     }
	     //把新增加的面板放进frame
	     panel2.add(panel21,BorderLayout.NORTH);
	     panel2.add(panel22,BorderLayout.CENTER);
	     panel.add(panel1,BorderLayout.WEST);
	     panel.add(panel2,BorderLayout.CENTER);
	     
	     container.add(panel);
	     
        
	}
	/*
	 * 此方法用来获得计算机的其他操作键
	 */
	private JButton[] getNButton() {
		// TODO Auto-generated method stub
		//这个数组保存需要设置为红色的操作符
		String[] redButton={"/","*","-","+","="};
		JButton[] result=new JButton[nOp.length];
		for(int i=0;i<this.nOp.length;i++){
			//新建按钮
			JButton b=new JButton(this.nOp[i]);
			//为按钮增加事件
			b.addActionListener(getActionListener());
			//对redButton 排序，才可以使用binarySearch方法（折半检索方法即二分法）
			Arrays.sort(redButton);
			//如果操作符在redButton中出现
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
	 * 此方法获得计算器的结果操作键
	 */
	private JButton[] getRButton() {
		// TODO Auto-generated method stub
		JButton[] result=new JButton[rOp.length];
		for(int i=0;i<this.rOp.length;i++){
			//新建按钮
			JButton b=new JButton(this.rOp[i]);
			//为按钮增加事件
			b.addActionListener(getActionListener());
			//设置按钮颜色
			b.setForeground(Color.red);
			result[i]=b;
		}		
		return result;	
	}
	/*
	 * 此方法获得计算器的存储操作键
	 */
	private JButton[] getMButton() {
		// TODO Auto-generated method stub
		JButton[] result=new JButton[mOp.length+1];
		result[0]=getButton();
		for(int i=0;i<this.mOp.length;i++){
			//新建按钮
			JButton b=new JButton(this.mOp[i]);
			//为按钮增加事件
			b.addActionListener(getActionListener());
			//设置按钮颜色
			b.setForeground(Color.red);
			result[i+1]=b;
		}
		return result;
	}
	/*
	 * 这个方法初始化输入框
	 */
	private JTextField getTextField() {
		// TODO Auto-generated method stub
		if(textField==null){
			//设置默认值为0
			textField=new JTextField("0");
			//设置为不可编辑
			textField.setEditable(false);
			//设置背景为白色
			textField.setBackground(Color.white);
		}
		return textField;
	}
	private JButton getButton(){
		if(button==null){
			//设置默认值为0
			button=new JButton();
		}
		return button;
	}
	/*
	 * 这个方法用来获取监听器
	 */
	public ActionListener getActionListener(){
		if(actionListener==null){
			actionListener=new ActionListener(){
				/*
				 * 实现接口中的actionPerformed方法
				 */
				public void actionPerformed(ActionEvent e){
					String cmd=e.getActionCommand();
					String result=null;
					try{
						//计算操作结果
						result=service.callMethod(cmd,textField.getText());		
					}catch(Exception el){
						System.out.println(el.getMessage());
					}
					//处理Button的标记
					if(cmd.indexOf("MC")==0){
						button.setText("");
					}else if(cmd.indexOf("M")==0&&service.getStore()>0){
						button.setText("M");
					}
					//设置计算结果
					if(result!=null){
						textField.setText(result);
					}
				}
		};		
		}
		return actionListener;
	}
}