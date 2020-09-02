package maze;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(10, 10);
        Menu menu = new Menu();
        Scanner sc = new Scanner(System.in);

        while (!menu.isExit()) {
            menu.ShowMenu();
            menu.ChooseFeature(sc.nextInt(), maze);
            if(menu.needreturn) {
                maze = menu.Return();
            }
        }
        System.out.println("Bye!");
    }
}



