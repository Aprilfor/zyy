package refiveChess;
/*
 * ��ʼ�����̣���ӡ���̲���
 */

public class Chessboard {
    public static final  int board_size=10;
    private String[][] array;
    //��ʼ������
    public void setBoard(){
    	array=new String[board_size][board_size];
    	for(int i=0;i<board_size;i++){
    		for(int j=0;j<board_size;j++){
    			array[i][j]="+";
    		}
    		System.out.println();
    	}
    }
    //��ӡ����
    public void printBoard(){
    	for(int i=0;i<board_size;i++){
    		for(int j=0;j<board_size;j++){
    			System.out.print(array[i][j]);
    		}
    		System.out.println();
    	}
    }
    //�������
    public String[][] getBoard(){
    	return this.array;
    }
    /*
     * �����̸�ֵ
     * posXΪ����x����
     * posYΪ����y����
     * chessmanΪ����
     */
    public void setBoard(int posX,int posY,String chessman){
    	this.array[posX][posY]=chessman;
    }
}
