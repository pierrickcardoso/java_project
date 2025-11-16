public class cell{
    private boolean is_bomb;
    private int bomb_in_neighboor;
    private boolean is_reveal ;
    private boolean is_flag ;
    public cell(boolean is_bomb){
        this.is_bomb = is_bomb ;
        this.bomb_in_neighboor=0;
        this.is_reveal=false;
        this.is_flag = false;
    }
    public boolean isFlag(){
        return this.is_flag;
    }
    public void flagged(){
        this.is_flag=!this.is_flag;
    }
    public boolean get_bomb_status(){
        return this.is_bomb;
    }  
    public void increase_bomb(){
        this.bomb_in_neighboor++;
    }
    public int get_bomb_neighboor(){
        return this.bomb_in_neighboor;
    }
    public void turn_into_bomb(){
        this.is_bomb = true;
    }
    public boolean get_reveal_status(){
        return this.is_reveal;
    }
    public void reveal_cell(){
        this.is_reveal=true;
    }
}