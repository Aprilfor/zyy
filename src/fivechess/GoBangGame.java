package fivechess;
  import java.io.BufferedReader;
  import java.io.InputStreamReader;
  /*
   * ��������Ϸ��
   */
public class GoBangGame {
    //����ﵽӮ������������Ŀ
	private final int WIN_COUNT=5;
	//�����û������x����
	private int posX=0;
	//�����û������y����
	private int posY=0;
	//��������
	private Chessboard chessboard;
	
	/*
	 * �չ�����
	 */
	public GoBangGame(){
		
	}
	
	/*
	 * ����������ʼ�����̺���������
	 * @param chessboard
	 * ������
	 */
	public GoBangGame(Chessboard chessboard){
		this.chessboard=chessboard;
	}
	/*
	 * ��������Ƿ�Ϸ�
	 * @param inputStr
	 *      �ɿ���̨������ַ���
	 * @return �ַ����Ϸ�����true,���Ϸ�����false
	 */
	public boolean isValid(String inputStr){
		//���û�������ַ����Զ��ţ�������Ϊ�ָ����ָ��������ַ���
		String[] posStrArr=inputStr.split(",");
		try{
			posX=Integer.parseInt(posStrArr[0])-1;
			posY=Integer.parseInt(posStrArr[1])-1;
			//����Integer.parseInt�������ַ���ת����Ϊһ��Integer����
		}catch(NumberFormatException e){
			chessboard.printBoard();
			System.out.println("�������ָ�ʽ���룺");
			return false;
		}
		//�������ֵ�Ƿ��ڷ�Χ�ڣ�������ֵ��û�в�����̱߽�
		if(posX<0||posX>=Chessboard.BOARD_SIZE||posY<0||posY>=Chessboard.BOARD_SIZE){
			chessboard.printBoard();
			System.out.println("x��y����ֻ�ܴ��ڵ���1����С�ڵ���"+Chessboard.BOARD_SIZE+"���������룺");
			return false;
		}
		//��������λ���Ƿ��Ѿ�������
		String[][] board=chessboard.getBoard();
		if(board[posX][posY]!="+"){
			chessboard.printBoard();
			System.out.println("��λ���Ѿ������ӣ����������룺");
			return false;
		}
		return true;
	}
	
	/*
	 * ��ʼ����
	 */
	public void start() throws Exception{
		//true Ϊ��Ϸ����
		boolean isOver=false;
		chessboard.initBoard();
		chessboard.printBoard();
		//��ȡ��������
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//BuffereReader ��java io���е�һ���ַ���װ���������뽨�����ַ����Ļ����ϣ�����System.in���ֽ���������Ӧ����InputStreamReader�����װ���ַ���
		String inputStr=null;
		//br.readLine����ÿ����������һ�����ݰ��س���������������ݱ�br��ȡ��
	while((inputStr=br.readLine())!=null){
		    isOver=false;
		    if(!isValid(inputStr)){
		    	//������Ϸ�����Ҫ���������룬�ټ���
		    	continue;
		    }
		    //�Ѷ�Ӧ������Ԫ�ظ�Ϊ#
		    String chessman=Chessman.BLACK.getChessman();
		    chessboard.setBoard(posX,posY,chessman);
		    //�ж��û��Ƿ�Ӯ��
		    if(isWon(posX,posY,chessman)){
		    	isOver=true;
		    }else{
		    	//��������ѡ��λ��
		    	int[] computerPosArr=computerDo();
		    	chessman=Chessman.WHITE.getChessman();
		    	chessboard.setBoard(computerPosArr[0],computerPosArr[1],chessman);
		    	//�жϼ�����Ƿ�Ӯ��
		    	if(isWon(computerPosArr[0],computerPosArr[1],chessman)){
		    		isOver=true;
		    	}
		    }
		    //�������ʤ�ߣ�ѯ���û��Ƿ������Ϸ
		    if(isOver){
		    	//������������³�ʼ�����̣�������Ϸ
		    	if(isReplay(chessman)){
		    		chessboard.initBoard();
		    		chessboard.printBoard();
		    	}
		    	//������������˳�����
		    	break;
		    }
		    chessboard.printBoard();
		    System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
		}
	}
	/*
	 * �Ƿ����¿�ʼ����
	 * @param chessman  #Ϊ�û���oΪ�����
	 * @return ��ʼ����true,���򷵻�false
	 */
	public boolean isReplay(String chessman) throws Exception{
		boolean flag = false;
		chessboard.printBoard();
		String message=chessman.equals(Chessman.BLACK.getChessman())?"��ϲ������Ӯ��":"���ź���������";
		System.out.println(message+"����һ�֣���y/n��");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		if(br.readLine().equals("y")){
			//��ʼһ��
			flag = true;
		}
		return flag;
	}
	/*
	 * ������������
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
	 * �ж���Ӯ
	 * @return posX  ���ӵ�x����
	 * @return posY ���ӵ�y����
	 * @param ico   ���ӵ�����
	 * 
	 * @return������޿�������������һ��ֱ�ߣ������棬�����෴ 
	 */
	public boolean isWon(int posX,int posY,String ico){
		//ֱ������x����
		int startX=0;
		//ֱ������y����
		int startY=0;
		//ֱ�߽���X����
		int endX=Chessboard.BOARD_SIZE-1;
		//ֱ�߽���Y����
		int endY=Chessboard.BOARD_SIZE-1;
		//ͬ��ֱ�������������ۼ���
		int sameCount=0;
		int temp=0;
		
		//����������СX������Y����
		temp=posX-WIN_COUNT+1;
		startX=temp<0? 0:temp;
		temp=posY-WIN_COUNT+1;
		startY=temp<0?0:temp;
		//�����յ���������X������Y����
		temp=posX+WIN_COUNT-1;
		endX=temp>Chessboard.BOARD_SIZE-1?Chessboard.BOARD_SIZE-1:temp;
		temp=posY+WIN_COUNT-1;
		endY=temp>Chessboard.BOARD_SIZE-1?Chessboard.BOARD_SIZE-1:temp;
		//���ϵ��¼�����ͬ�������ӵ���Ŀ
		String[][] board=chessboard.getBoard();
		for(int i=startY;i<endY;i++){
			if(board[posX][i]==ico&&board[posX][i+1]==ico){
				sameCount++;
			}else if(sameCount!=WIN_COUNT-1){
				sameCount=0;
			}
		}
		if(sameCount==0){
			//�����Ҽ�����ͬ�������ӵ���Ŀ
			for(int i=startX;i<endX;i++){
				if(board[i][posY]==ico&&board[i+1][posY]==ico){
					sameCount++;
				}else if(sameCount!=WIN_COUNT-1){
					sameCount=0;
				}
			}
		}
		if(sameCount==0){
			//�����ϵ����¼�����ͬ�������ӵ���Ŀ
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
