public class cellmain {
        public static void main(String[] args) {
            cell cell = new cell(false);
            System.out.println(cell.isFlag());
            cell.flagged();
            System.out.println(cell.isFlag());
}}
