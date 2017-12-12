package fivechess;
  import java.io.BufferedReader;
  import java.io.InputStreamReader;
  /*
   * 五子棋游戏类
   */
public class GoBangGame {
    //定义达到赢条件的棋子数目
	private final int WIN_COUNT=5;
	//定义用户输入的x坐标
	private int posX=0;
	//定义用户输入的y坐标
	private int posY=0;
	//定义棋盘
	private Chessboard chessboard;
	
	/*
	 * 空构造器
	 */
	public GoBangGame(){
		
	}
	
	/*
	 * 构造器，初始化棋盘和棋子属性
	 * @param chessboard
	 * 棋盘类
	 */
	public GoBangGame(Chessboard chessboard){
		this.chessboard=chessboard;
	}
	/*
	 * 检查输入是否合法
	 * @param inputStr
	 *      由控制台输入的字符串
	 * @return 字符串合法返回true,不合法返回false
	 */
	public boolean isValid(String inputStr){
		//将用户输入的字符串以逗号（，）作为分隔，分隔成两个字符串
		String[] posStrArr=inputStr.split(",");
		try{
			posX=Integer.parseInt(posStrArr[0])-1;
			posY=Integer.parseInt(posStrArr[1])-1;
			//调用Integer.parseInt方法将字符串转换成为一个Integer类型
		}catch(NumberFormatException e){
			chessboard.printBoard();
			System.out.println("请以数字格式输入：");
			return false;
		}
		//检查输入值是否在范围内，即输入值有没有查过棋盘边界
		if(posX<0||posX>=Chessboard.BOARD_SIZE||posY<0||posY>=Chessboard.BOARD_SIZE){
			chessboard.printBoard();
			System.out.println("x与y坐标只能大于等于1，与小于等于"+Chessboard.BOARD_SIZE+"请重新输入：");
			return false;
		}
		//检查输入的位置是否已经有棋子
		String[][] board=chessboard.getBoard();
		if(board[posX][posY]!="+"){
			chessboard.printBoard();
			System.out.println("此位置已经有棋子，请重新输入：");
			return false;
		}
		return true;
	}
	
	/*
	 * 开始下棋
	 */
	public void start() throws Exception{
		//true 为游戏结束
		boolean isOver=false;
		chessboard.initBoard();
		chessboard.printBoard();
		//获取键盘输入
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//BuffereReader 是java io流中的一个字符包装流，它必须建立在字符流的基础上，但是System.in是字节流，所以应该用InputStreamReader将其包装成字符流
		String inputStr=null;
		//br.readLine方法每当键盘输入一行内容按回车键，则输入的内容被br读取到
	while((inputStr=br.readLine())!=null){
		    isOver=false;
		    if(!isValid(inputStr)){
		    	//如果不合法，则要求重新输入，再继续
		    	continue;
		    }
		    //把对应的数组元素赋为#
		    String chessman=Chessman.BLACK.getChessman();
		    chessboard.setBoard(posX,posY,chessman);
		    //判断用户是否赢了
		    if(isWon(posX,posY,chessman)){
		    	isOver=true;
		    }else{
		    	//计算机随机选择位置
		    	int[] computerPosArr=computerDo();
		    	chessman=Chessman.WHITE.getChessman();
		    	chessboard.setBoard(computerPosArr[0],computerPosArr[1],chessman);
		    	//判断计算机是否赢了
		    	if(isWon(computerPosArr[0],computerPosArr[1],chessman)){
		    		isOver=true;
		    	}
		    }
		    //如果产生胜者，询问用户是否继续游戏
		    if(isOver){
		    	//如果继续，重新初始化棋盘，继续游戏
		    	if(isReplay(chessman)){
		    		chessboard.initBoard();
		    		chessboard.printBoard();
		    	}
		    	//如果不继续，退出程序
		    	break;
		    }
		    chessboard.printBoard();
		    System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
		}
	}
	/*
	 * 是否重新开始下棋
	 * @param chessman  #为用户，o为计算机
	 * @return 开始返回true,反则返回false
	 */
	public boolean isReplay(String chessman) throws Exception{
		boolean flag = false;
		chessboard.printBoard();
		String message=chessman.equals(Chessman.BLACK.getChessman())?"恭喜您，您赢了":"很遗憾，您输了";
		System.out.println(message+"在下一局？（y/n）");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		if(br.readLine().equals("y")){
			//开始一局
			flag = true;
		}
		return flag;
	}
	/*
	 * 计算机随机下棋
	 */
	public int[] computerDo(){
		int posX=(int)(Math.random()*(Chessboard.BOARD_SIZE-1));
		int posY=(int)(Math.random()*(Chessboard.BOARD_SIZE-1));
		String[][] board=chessboard.getBoard();
		while(board[posX][posY]!="+"){
			posX=(int)(Math.random()*(Chessboard.BOARD_SIZE-1));
			posY=(int)(Math.random()*(Chessboard.BOARD_SIZE-1));
		}
		int[] result={posX,posY};
		return result;
	}
	/*
	 * 判断输赢
	 * @return posX  棋子的x坐标
	 * @return posY 棋子的y坐标
	 * @param ico   棋子的类型
	 * 
	 * @return如果有无可相邻棋子连城一条直线，返回真，否则相反 
	 */
	public boolean isWon(int posX,int posY,String ico){
		//直线起点的x坐标
		int startX=0;
		//直线起点的y坐标
		int startY=0;
		//直线结束X坐标
		int endX=Chessboard.BOARD_SIZE-1;
		//直线结束Y坐标
		int endY=Chessboard.BOARD_SIZE-1;
		//同条直线上相邻棋子累计数
		int sameCount=0;
		int temp=0;
		
		//计算起点的最小X坐标与Y坐标
		temp=posX-WIN_COUNT+1;
		startX=temp<0? 0:temp;
		temp=posY-WIN_COUNT+1;
		startY=temp<0?0:temp;
		//计算终点的最大坐标X坐标与Y坐标
		temp=posX+WIN_COUNT-1;
		endX=temp>Chessboard.BOARD_SIZE-1?Chessboard.BOARD_SIZE-1:temp;
		temp=posY+WIN_COUNT-1;
		endY=temp>Chessboard.BOARD_SIZE-1?Chessboard.BOARD_SIZE-1:temp;
		//从上到下计算相同相邻棋子的数目
		String[][] board=chessboard.getBoard();
		for(int i=startY;i<endY;i++){
			if(board[posX][i]==ico&&board[posX][i+1]==ico){
				sameCount++;
			}else if(sameCount!=WIN_COUNT-1){
				sameCount=0;
			}
		}
		if(sameCount==0){
			//从左到右计算相同相邻棋子的数目
			for(int i=startX;i<endX;i++){
				if(board[i][posY]==ico&&board[i+1][posY]==ico){
					sameCount++;
				}else if(sameCount!=WIN_COUNT-1){
					sameCount=0;
				}
			}
		}
		if(sameCount==0){
			//从左上到右下计算相同相邻棋子的数目
			int j=startY;
			for(int i=startX;i<endX;i++){
				if(j>endY){
					if(board[i][j]==ico&&board[i+1][j+1]==ico){
						sameCount++;
					}else if(sameCount!=WIN_COUNT-1){
						sameCount=0;
					}
					j++;
				}
			}
		}
		return sameCount==WIN_COUNT-1?true:false;
	}
	public static void main(String[] args)throws Exception{
		GoBangGame gb=new GoBangGame(new Chessboard());
		gb.start();
	}
}
