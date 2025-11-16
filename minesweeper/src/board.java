import java.util.Random;
public class board {
    private Random rand = new Random();
    private int height;
    private int witdh;
    private int amout_bomb;
    private cell [][] board ;
    private boolean bombsPlaced = false;
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

    public boolean is_in_the_grid(int x , int y ){
		return 0 <= x && x< this.witdh && 0 <= y && y < this.height ;}
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
    public void flag(int i, int j ){
        if (!is_in_the_grid(j, i)) {
            return;}
        else{
            this.board[i][j].flagged();
        }

    }

    public void play(int i , int j) throws BombHereException{
        if (!is_in_the_grid(j, i)) return;
        // place bombs on first move to ensure the first revealed cell is never a bomb
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
            // count neighboring bombs (increment the cell's counter)
            for (cell elem : all_neighboor_of(i, j)){
                if (elem == null) continue;
                if (elem.get_bomb_status()){
                    this.board[i][j].increase_bomb();
                }
            }
            // if no neighboring bombs, reveal neighbors recursively
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
    public String display(){
        String res = "";
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.witdh; j++) {
                cell c = this.board[i][j];
                if (c.isFlag()){
                    res=res+'/';
                }
                else if (!c.get_reveal_status()) {
                    res=res+"."; // hidden
                } else if (c.get_bomb_status()) {
                    res=res+'*'; // bomb 
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
    