package calCulator;
import javax.swing.JFrame;
/*
 * �����������
 * ������javax.swing.JFrame�����ӣ���ʹ��jpanel��������������
 */
/*
 * �����������������ڽϴ�����
 * �Ӽ�û��̫������⣬���ǳ˳���������������
 * ��������������������ȷ��ʵ�֡�
 */
public class Main {
    public static void main(String[] args){
    	CalFrame f=new CalFrame();//����������ʼ����
    	f.pack();//��������õ�����趨���ڵĴ�С ʹ֮��������������õ��������
    	f.setResizable(false);
    	f.setVisible(true);//���ô��ڽ���ɼ�
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	/*
    	 * �����û��ڴ˴����Ϸ���closeʱĬ��ִ�еĲ���������ָ�����µ�ѡ��
    	 * DO_NOTHING_ON_CLOSE���� WindowConstants �ж��壩��
    	 * ��ִ���κβ�����Ҫ���������ע��� WindowListener ����� windowClosing �����д���ò�����
    	 * HIDE_ON_CLOSE���� WindowConstants �ж��壩����δָ��ʱΪĬ�ϲ�����
    	 * ����������ע��� WindowListener ������Զ����ظô��塣
    	 * DISPOSE_ON_CLOSE���� WindowConstants �ж��壩��
    	 * ����������ע�� WindowListener �Ķ�����Զ����ز��ͷŸô��塣
    	 * EXIT_ON_CLOSE���� JFrame �ж��壩��
    	 * ʹ�� System exit �����˳�Ӧ�ó��򡣽���Ӧ�ó�����ʹ�á�    
    	 */
    }
}
