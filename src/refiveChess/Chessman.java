package refiveChess;
/*
 * 棋子枚举类
 * 黑子和白子两个属性
 */
public enum Chessman {
    BLACK("#"),WHITE("o");
    private String chessman;
    
    //私有构造器
    private Chessman(String chessman){
    	this.chessman=chessman;
    }
    
    //获得棋子
    public String getChessman(){
    	return this.chessman;
    }
}
