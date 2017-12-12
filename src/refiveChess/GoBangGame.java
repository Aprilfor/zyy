package refiveChess;
/*
 * 游戏：五子棋
 * 编程人员：张园园
 * 完成时间：2017/10/31
 * 耗时：5天
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class GoBangGame {
     private static  boolean isOver = false;
	private Chessboard chessboard;
     //赢的条件个数
     private final int win_size=5;
     //用户输入的x坐标
     private int posX;
     //用户输入的y坐标
     private int posY;
    
	//构造器，初始化棋子棋盘属性
     public GoBangGame(Chessboard chessboard){
    	 this.chessboard=chessboard;
     }
	/*
	 * 开始游戏
	 */
	public boolean start() throws Exception{		
		chessboard.setBoard();
		chessboard.printBoard();
		BufferedReader Str=new BufferedReader(new InputStreamReader(System.in));
		String inputStr=null;
		while((inputStr=Str.readLine())!=null){
			if(!isValid(inputStr)){
				continue;
			}
			//设置该位置棋子为"#"
			String chessman=Chessman.BLACK.getChessman();
			chessboard.setBoard(posX,posY,chessman);
			//判断用户是否赢了
			if(isWon(posX,posY,chessman)){
				isOver=true;
				
				break;
			}else{
				//计算机随机下棋
				int[] computerPos=computerDo();
				String ico=Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPos[0],computerPos[1],ico);
				if(isWon(computerPos[0],computerPos[1],ico)){
					isOver=false;
					break;
				}
			}
			
		}
		return isOver;
	}
	
     
	private int[] computerDo() {
		// TODO Auto-generated method stub
    	 //随机初始电脑方棋子的位置
    	 int posX=(int)(Math.random()*(Chessboard.board_size-1));
    	 int posY=(int)(Math.random()*(Chessboard.board_size-1));
    	 String[][] board=chessboard.getBoard();
    	 if(board[posX][posY]!="+"){
    	    posX=(int)(Math.random()*(Chessboard.board_size-1));
    		posY=(int)(Math.random()*(Chessboard.board_size-1));
    	 }
    	 int[] result={posX,posY};
		return result;
	}
	private boolean isWon(int posX, int posY, String chessman) throws Exception {
		// TODO Auto-generated method stub
    	 /*
    	  * 判断用户是否赢了
    	  */
    	 int startX=0;
    	 int startY=0;
    	 int endX=Chessboard.board_size-1;
    	 int endY=endX;
    	 int sameCount=0;
    	 int temp=0;
    	 //计算起点的最小X,Y坐标
    	 temp=posX-win_size+1;
    	 startX=temp<0?0:temp;
    	 temp=posY-win_size+1;
    	 startY=temp<0?0:temp;
    	 //计算终点的最大x,y坐标
    	 temp=posX+win_size-1;
    	 endX=temp>Chessboard.board_size?Chessboard.board_size:temp;
    	 temp=posY+win_size-1;
    	 endY=temp>Chessboard.board_size?Chessboard.board_size:temp;
    	 
    	 //从上到下计算累计棋子数
    	 String[][] board=chessboard.getBoard();
    	 for(int i=startY;i<endY;i++){
    		 if(board[posX][i]==chessman&&board[posX][i+1]==chessman){
    			 sameCount++;
    		 }
    		 else if(sameCount!=win_size-1){
    			 sameCount=0;
    		 }
    	 }
    	 //从左到右计算累计棋子数
    	 for(int i=startX;i<endX;i++){
    		 if(board[i][posY]==chessman&&board[i+1][posY]==chessman){
    			 sameCount++;
    		 }else if(sameCount!=win_size-1){
    			 sameCount=0;
    		 }
    	 }
    	 //从左上到右下计算棋子累计数
    	 if(sameCount==0){
    		 int j=startY;
    		 for(int i=startX;i<endX;i++){
    			 if(j<endY){
    				 if(board[i][j]==chessman&&board[i+1][j+1]==chessman){
    					 sameCount++;
    				 }else if(sameCount!=win_size-1){
    					 sameCount=0;
    				 }
    			 }
    			 j++;
    		 }
    	 }
		return sameCount==win_size-1?true:false;
	}
	private boolean isValid (String inputStr) throws Exception {
		// TODO Auto-generated method stub
    	 //讲一个字符串用逗号分割成两个字符串
    	 String[] posStr=inputStr.split(",");
    	 //判断是否以数字的形式输入
    	 try{
    		 posX=Integer.parseInt(posStr[0])-1;
    		 posY=Integer.parseInt(posStr[1])-1;
    	 }catch(NumberFormatException e){
    		 chessboard.printBoard();
    		 System.out.println("请以数字的形式输入：");
    		 return false;
    	 }
    	 //判断输入坐标是否超过了边界
    	 if(posX<0||posX>=Chessboard.board_size||posY<0||posY>Chessboard.board_size){
    		 chessboard.printBoard();
    		 System.out.println("您已超过边界了，请重新输入:");
    		 return false;
    	 }
    	 //判断该位置是否已经有棋子了
    	 String[][] board=chessboard.getBoard();
    	 if(board[posX][posY]!="+"){
    		 chessboard.printBoard();
    		 System.out.println("该位置已经有棋子。请重新输入：");
    		 return false;
    	 }
		return true;
	}
	public static void main(String[] args) throws Exception{
    	 GoBangGame gb=new GoBangGame(new Chessboard());
    	 boolean arr=gb.start();
    	 if(arr==true){
    		 System.out.println("恭喜您，您赢了");
    	 }else if(arr==false){
    		 System.out.println("很抱歉，您输了！");
    	 }
     }
}
