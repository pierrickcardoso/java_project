public class cell{
    /*  
     * This class represents a cell in the Minesweeper game.
     * It contains methods to manage the cell's state,
     * including whether it contains a bomb, the number of bombs in neighboring cells,
     * whether it has been revealed, and whether it is flagged.
     * * @author pierrick cardoso
     * @param is_bomb indicates if the cell contains a bomb 
     */
    private boolean is_bomb;
    private int bomb_in_neighboor;
    private boolean is_reveal ;
    private boolean is_flag ;
    // Constructor to initialize the cell with a bomb status
    public cell(boolean is_bomb){
        this.is_bomb = is_bomb ;
        this.bomb_in_neighboor=0;
        this.is_reveal=false;
        this.is_flag = false;
    }
    // Method to check if the cell is flagged
    public boolean isFlag(){
        return this.is_flag;
    }
    // Method to toggle the flagged status of the cell
    public void flagged(){
        this.is_flag=!this.is_flag;
    }
    // Method to get the bomb status of the cell either it contains a bomb or not
    public boolean get_bomb_status(){
        return this.is_bomb;
    }  
    // Method to increase the count of bombs in neighboring cells
    public void increase_bomb(){
        this.bomb_in_neighboor++;
    }
    // Method to get the number of bombs in neighboring cells
    public int get_bomb_neighboor(){
        return this.bomb_in_neighboor;
    }
    //Method to turn the cell into a bomb
    public void turn_into_bomb(){
        this.is_bomb = true;
    }
    // Method to check if the cell has been revealed
    public boolean get_reveal_status(){
        return this.is_reveal;
    }
    // Method to reveal the cell
    public void reveal_cell(){
        this.is_reveal=true;
    }
}