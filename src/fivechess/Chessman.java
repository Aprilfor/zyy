package fivechess;

/*
 * ����ö����
 */

public enum Chessman {
    BLACK("#"),WHITE("o");
    private String chessman;
    
    /*
     * ˽�й�����
     */
    private Chessman(String chessman){
    	this.chessman=chessman;
    }
    /*
     * @return String������߰���
     */
    public String getChessman(){
    	return this.chessman;
    }
}
