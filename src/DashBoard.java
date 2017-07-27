import java.io.IOException;

/**
 * Created by kandarp.jo on 14/07/17.
 */
public class DashBoard {
    private char [][] dashArray;


    public DashBoard(){
        dashArray = new char[3][3];
        reset(dashArray);

    }
    private void reset(char dashArray[][]){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                dashArray[i][j] = ' ';
            }
        }
    }
    public boolean checkValidMove(int x,int y){
        if(x>=3 || y>=3 || x<0 || y<0){
            return false;
        }
        if(dashArray[x][y]!=' '){
            return false;
        }
        return true;
    }
    public void printDashboard(MessageWriter out) throws IOException{
        out.println("  0   1   2");
        out.println("0 " + dashArray[0][0] + " | " + dashArray[0][1] + " | " + dashArray[0][2]);
        out.println("------------");
        out.println("1 " + dashArray[1][0] + " | " + dashArray[1][1] + " | " + dashArray[1][2]);
        out.println("------------");
        out.println("2 " + dashArray[2][0] + " | " + dashArray[2][1] + " | " + dashArray[2][2]);
    }
    public boolean enterMove(Player p, int x, int y){
        if(!checkValidMove(x,y)){
            System.out.println("Invalid Move");
            return false;
        }
        this.dashArray[x][y] = p.getSign();
        return true;
    }
    public char checkWinner(){
        char horizontal = checkHorizontal();
        char vertical = checkVertical();
        char diagonal = checkDiagonals();
        if(horizontal!=' '){
            return horizontal;
        }
        if(vertical != ' '){
            return vertical;
        }
        return diagonal;
    }
    private char checkHorizontal(){
        for(int i=0;i<3;i++){
            char sign = dashArray[i][0];
            int counter =0;
            for(int j=0;j<3;j++){
                if(dashArray[i][j] == sign){
                    counter++;
                }
            }
            if(counter==3){
                return sign;
            }
        }
        return ' ';

    }
    private char checkVertical(){
        for(int i=0;i<3;i++){
            char sign = dashArray[i][0];
            int counter =0;
            for(int j=0;j<3;j++){
                if(dashArray[j][i] == sign){
                    counter++;
                }
            }
            if(counter==3){
                return sign;
            }
        }
        return ' ';
    }
    private char checkDiagonals(){
        int counter=0;
        char sign = dashArray[0][0];
        for(int i=0;i<3;i++){
            if(dashArray[i][i] == sign){
                counter++;
            }
        }
        if(counter==3){
            return sign;
        }
        counter=0;
        sign = dashArray[2][0];
        for(int i=0;i<3;i++){
            if(dashArray[i][2-i]==sign){
                counter++;
            }
        }
        if(counter==3){
            return sign;
        }
        return ' ';
    }


}
