package refiveChess;
/*
 * 初始化棋盘，打印棋盘操作
 */

public class Chessboard {
    public static final  int board_size=10;
    private String[][] array;
    //初始化棋盘
    public void setBoard(){
    	array=new String[board_size][board_size];
    	for(int i=0;i<board_size;i++){
    		for(int j=0;j<board_size;j++){
    			array[i][j]="+";
    		}
    		System.out.println();
    	}
    }
    //打印棋盘
    public void printBoard(){
    	for(int i=0;i<board_size;i++){
    		for(int j=0;j<board_size;j++){
    			System.out.print(array[i][j]);
    		}
    		System.out.println();
    	}
    }
    //获得棋盘
    public String[][] getBoard(){
    	return this.array;
    }
    /*
     * 给棋盘赋值
     * posX为棋子x坐标
     * posY为棋子y坐标
     * chessman为棋子
     */
    public void setBoard(int posX,int posY,String chessman){
    	this.array[posX][posY]=chessman;
    }
}
