package refiveChess;
/*
 * ��Ϸ��������
 * �����Ա����԰԰
 * ���ʱ�䣺2017/10/31
 * ��ʱ��5��
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class GoBangGame {
     private static  boolean isOver = false;
	private Chessboard chessboard;
     //Ӯ����������
     private final int win_size=5;
     //�û������x����
     private int posX;
     //�û������y����
     private int posY;
    
	//����������ʼ��������������
     public GoBangGame(Chessboard chessboard){
    	 this.chessboard=chessboard;
     }
	/*
	 * ��ʼ��Ϸ
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
			//���ø�λ������Ϊ"#"
			String chessman=Chessman.BLACK.getChessman();
			chessboard.setBoard(posX,posY,chessman);
			//�ж��û��Ƿ�Ӯ��
			if(isWon(posX,posY,chessman)){
				isOver=true;
				
				break;
			}else{
				//������������
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
    	 //�����ʼ���Է����ӵ�λ��
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
    	  * �ж��û��Ƿ�Ӯ��
    	  */
    	 int startX=0;
    	 int startY=0;
    	 int endX=Chessboard.board_size-1;
    	 int endY=endX;
    	 int sameCount=0;
    	 int temp=0;
    	 //����������СX,Y����
    	 temp=posX-win_size+1;
    	 startX=temp<0?0:temp;
    	 temp=posY-win_size+1;
    	 startY=temp<0?0:temp;
    	 //�����յ�����x,y����
    	 temp=posX+win_size-1;
    	 endX=temp>Chessboard.board_size?Chessboard.board_size:temp;
    	 temp=posY+win_size-1;
    	 endY=temp>Chessboard.board_size?Chessboard.board_size:temp;
    	 
    	 //���ϵ��¼����ۼ�������
    	 String[][] board=chessboard.getBoard();
    	 for(int i=startY;i<endY;i++){
    		 if(board[posX][i]==chessman&&board[posX][i+1]==chessman){
    			 sameCount++;
    		 }
    		 else if(sameCount!=win_size-1){
    			 sameCount=0;
    		 }
    	 }
    	 //�����Ҽ����ۼ�������
    	 for(int i=startX;i<endX;i++){
    		 if(board[i][posY]==chessman&&board[i+1][posY]==chessman){
    			 sameCount++;
    		 }else if(sameCount!=win_size-1){
    			 sameCount=0;
    		 }
    	 }
    	 //�����ϵ����¼��������ۼ���
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
    	 //��һ���ַ����ö��ŷָ�������ַ���
    	 String[] posStr=inputStr.split(",");
    	 //�ж��Ƿ������ֵ���ʽ����
    	 try{
    		 posX=Integer.parseInt(posStr[0])-1;
    		 posY=Integer.parseInt(posStr[1])-1;
    	 }catch(NumberFormatException e){
    		 chessboard.printBoard();
    		 System.out.println("�������ֵ���ʽ���룺");
    		 return false;
    	 }
    	 //�ж����������Ƿ񳬹��˱߽�
    	 if(posX<0||posX>=Chessboard.board_size||posY<0||posY>Chessboard.board_size){
    		 chessboard.printBoard();
    		 System.out.println("���ѳ����߽��ˣ�����������:");
    		 return false;
    	 }
    	 //�жϸ�λ���Ƿ��Ѿ���������
    	 String[][] board=chessboard.getBoard();
    	 if(board[posX][posY]!="+"){
    		 chessboard.printBoard();
    		 System.out.println("��λ���Ѿ������ӡ����������룺");
    		 return false;
    	 }
		return true;
	}
	public static void main(String[] args) throws Exception{
    	 GoBangGame gb=new GoBangGame(new Chessboard());
    	 boolean arr=gb.start();
    	 if(arr==true){
    		 System.out.println("��ϲ������Ӯ��");
    	 }else if(arr==false){
    		 System.out.println("�ܱ�Ǹ�������ˣ�");
    	 }
     }
}
