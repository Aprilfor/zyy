package fivechess;
/*
 * ���̶���
 * ������Ҫ�������ڳ�ʼ�����̲���ӡ���
 */

public class Chessboard {
   //����һ����ά�������䵱һ������
	private String[][] board;
	//�������̵Ĵ�С
	public static final int BOARD_SIZE=10;
	/*
	 * ��ʼ������
	 */
	public void initBoard(){
		//��ʼ����������
		board=new String[BOARD_SIZE][BOARD_SIZE];
		//��ÿ��Ԫ�ظ�ֵΪ��+�������ڿ���̨�������
		for(int i=0;i<BOARD_SIZE;i++){
			for(int j=0;j<BOARD_SIZE;j++){
				board[i][j]="+";
			}
		}
	}
	/*
	 * ����̨��ӡ�������
	 */
	public void printBoard(){
		//��ӡÿ������Ԫ��
		for(int i=0;i<BOARD_SIZE;i++){
			for(int j=0;j<BOARD_SIZE;j++){
				//��ӡ�󲻻���
				System.out.print(board[i][j]);
			}
			//ÿ��ӡ��һ������Ԫ�ؾͻ���һ��
			System.out.println("\n");
		}
	}
	/*
	 * �����̸�ֵ
	 * 
	 * @param posX   X����
	 * @param posY y����
	 * @param chessman   ����
	 * 
	 */
	public void setBoard(int posX,int posY,String chessman){
		 this.board[posX][posY]=chessman;
	}
	/*
	 * ��������
	 * @return ��������
	 */
	
	public String[][] getBoard(){
		return this.board;
	}
}
