import java.util.Scanner;

public class boardMain {
    public static void main(String[] args) {
        board board = new board(10, 10, 20);
        try (Scanner sc = new Scanner(System.in)) {
            while (!board.is_win()){
                System.out.print(board.display());
                System.out.println("Where do you wanna try , tape line/column add a number (not 0) so you have 3 number at the start to flag the cell?");
                int play = sc.nextInt();
                if (String.valueOf(play).length()==3){
                    String temp = String.valueOf(play);
                    play = Integer.parseInt((temp.subSequence(1, 3).toString()));                    
                    int line= play/10;
                    int column = play%10;
                    board.flag(line, column);
                }
                else{
                    int line= play/10;
                    int column = play%10;
                    if (!board.is_in_the_grid(column, line)){
                    System.out.println("Coordinates out of grid, try again.");
                    continue;
                }
                try {
                    board.play(line, column);
                }
                
                catch (BombHereException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }
        if (board.is_win()){
            System.out.println("You win !");
    }
}
}
}