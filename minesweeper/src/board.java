import java.util.Random;
public class board {
    /*  
     * This class represents the board of the minesweeper game.
     * It contains methods to initialize the board, place bombs,
     * reveal cells, flag cells, and check for win conditions.
     * @author pierrick cardoso
     * @param height the height of the board
     * @param witdh the width of the board
     * @param amout_bomb the number of bombs on the board
     * 
     */
    private Random rand = new Random();
    private int height;
    private int witdh;
    private int amout_bomb;
    private cell [][] board ;
    private boolean bombsPlaced = false;
    // Constructor to initialize the board with given height, width, and number of bombs
    public board(int height , int witdh , int amout_bomb){
        this.height=height;
        this.witdh=witdh;
        this.amout_bomb=amout_bomb;
        this.board = new cell[this.height][this.witdh];
        for (int i=0;i<this.height;i++){
            for (int j = 0; j < this.witdh; j++) {
                this.board[i][j] = new cell(false);

            }
        }}
    // Method to place bombs on the board, ensuring that the first cell clicked is not a bomb
        public void place_bomb(int init_i,int init_j){
        int bomb_place=0;
        while (bomb_place!=this.amout_bomb){
            int i = rand.nextInt(this.height);
            int j = rand.nextInt(this.witdh);
            if (!(i==init_i && j==init_j)){
            if (!(this.board[i][j].get_bomb_status())){
                this.board[i][j].turn_into_bomb();
                bomb_place++;
            }}
        }
    }
    // Method to check if a cell is within the grid boundaries
    public boolean is_in_the_grid(int x , int y ){
		return 0 <= x && x< this.witdh && 0 <= y && y < this.height ;}
    // Method to get all neighboring cells of a given cell
    public cell[] all_neighboor_of(int i , int j){
        cell[] res = new cell[8];
        int idx = 0;
        for (int x=-1;x<=1;x++){
            for (int y=-1 ;y<=1;y++){
                if (x==0 && y==0) continue;
                int ni = i + x;
                int nj = j + y;
                if (is_in_the_grid(nj, ni)){
                    res[idx++] = this.board[ni][nj];
                }
            }
        }
        return res;
    }
    // Method to flag a cell, marking it as potentially containing a bomb
    public void flag(int i, int j ){
        if (!is_in_the_grid(j, i)) {
            return;}
        else{
            this.board[i][j].flagged();
        }

    }
    // Method to play the game by revealing a cell and checking for bombs
    public void play(int i , int j) throws BombHereException{
        if (!is_in_the_grid(j, i)) return;
        if (!bombsPlaced) {
            place_bomb(i, j);
            bombsPlaced = true;
        }
        if (this.board[i][j].get_reveal_status()) 
            {return;}
        this.board[i][j].reveal_cell();
        if (this.board[i][j].get_bomb_status()){
            throw new BombHereException("There is a bomb here you lose");
        } else {
            for (cell elem : all_neighboor_of(i, j)){
                if (elem == null) continue;
                if (elem.get_bomb_status()){
                    this.board[i][j].increase_bomb();
                }
            }
            if (this.board[i][j].get_bomb_neighboor() == 0){
                for (int x=-1;x<=1;x++){
                    for (int y=-1;y<=1;y++){
                        int ni = i + x;
                        int nj = j + y;
                        if (is_in_the_grid(nj, ni) && !(ni==i && nj==j)){
                            if (!this.board[ni][nj].get_reveal_status()){
                                play(ni, nj);
                            }
                        }
                    }
                }
            }
        }
    }
    // Method to check if the player has won the game
    public boolean is_win(){
        for (int i=0;i<this.height;i++){
            for (int j = 0; j < this.witdh; j++) {
                if (!(this.board[i][j].get_bomb_status())){
                    if (!(this.board[i][j].get_reveal_status())){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    // Method to display the current state of the board
    public String display(){
        String res = "";
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.witdh; j++) {
                cell c = this.board[i][j];
                if (c.isFlag()){
                    res=res+'/';
                }
                else if (!c.get_reveal_status()) {
                    res=res+"."; 
                } else if (c.get_bomb_status()) {
                    res=res+'*'; 
                    }
                else {
                    int n = c.get_bomb_neighboor();
                    res=res+Integer.toString(n);
                }
            }
            res=res+"\n";
        }
        return res;
    }
}
    