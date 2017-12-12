package calCulator;
import javax.swing.JFrame;
/*
 * 计算器入口类
 * 导入了javax.swing.JFrame（桌子）中使用jpanel（桌布）来布局
 */
/*
 * 该试验最后计算结果存在较大的误差
 * 加减没有太大的问题，但是乘除，开方等有问题
 * 其他操作按键都有了正确的实现。
 */
public class Main {
    public static void main(String[] args){
    	CalFrame f=new CalFrame();//界面对象类初始对象
    	f.pack();//依据你放置的组件设定窗口的大小 使之正好能容纳你放置的所有组件
    	f.setResizable(false);
    	f.setVisible(true);//设置窗口界面可见
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	/*
    	 * 设置用户在此窗体上发起close时默认执行的操作，必须指定以下的选项
    	 * DO_NOTHING_ON_CLOSE（在 WindowConstants 中定义）：
    	 * 不执行任何操作；要求程序在已注册的 WindowListener 对象的 windowClosing 方法中处理该操作。
    	 * HIDE_ON_CLOSE（在 WindowConstants 中定义）：（未指定时为默认操作）
    	 * 调用任意已注册的 WindowListener 对象后自动隐藏该窗体。
    	 * DISPOSE_ON_CLOSE（在 WindowConstants 中定义）：
    	 * 调用任意已注册 WindowListener 的对象后自动隐藏并释放该窗体。
    	 * EXIT_ON_CLOSE（在 JFrame 中定义）：
    	 * 使用 System exit 方法退出应用程序。仅在应用程序中使用。    
    	 */
    }
}
