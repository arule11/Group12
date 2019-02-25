public class Board {

    public static void main(String[] args) {
        int[][] board = new int[10][10];
       
        initBoard(board);
       
        System.out.println();
      
        showBoard(board);
    }
    
    public static void initBoard(int[][] board){
        for(int row=0 ; row < 10 ; row++ )
            for(int column=0 ; column < 10 ; column++ )
                board[row][column]=-1;
    }
    
    public static void showBoard(int[][] board){
        System.out.println("\tA \tB \tC \tD \tE \tF \tG \tH \tI \tJ");
        System.out.println();
        
        for(int row=0 ; row < 10 ; row++ ){
            System.out.print((row+1)+"");
            for(int column=0 ; column < 10 ; column++ ){
                if(board[row][column]==-1){
                    System.out.print("\t"+"~");
                }else if(board[row][column]==0){
                    System.out.print("\t"+"*");
                }else if(board[row][column]==1){
                    System.out.print("\t"+"X");
                }    
            }
            System.out.println();
        }
    }
}
    
