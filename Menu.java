package maze;

import java.util.Scanner;

public class Menu {
    boolean exit = false;
    boolean needreturn = false;
    Maze maze = new Maze(10,10);
    boolean mazeexist = false;

    public  void ShowMenu() {
        if(mazeexist) {
            System.out.println("=== Menu ===");
            System.out.println("1. Generate a new maze");
            System.out.println("2. Load a maze");
            System.out.println("3. Save the maze");
            System.out.println("4. Display the maze");
            System.out.println("5. Find the escape");
            System.out.println("0. Exit");
        } else {
            System.out.println("=== Menu ===");
            System.out.println("1. Generate a new maze");
            System.out.println("2. Load a maze");
            System.out.println("0. Exit");
        }

    }

    public void Load() {
        Scanner sc = new Scanner(System.in);
        maze.LoadMaze(sc.nextLine());
        needreturn = true;
        mazeexist = true;
    }

    public void Save(Maze maze) {
        Scanner sc = new Scanner(System.in);
        maze.SaveMaze(sc.nextLine());
    }

    public  void Generate() {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        this.maze = new Maze(length, length);
        maze.showMaze();
        needreturn = true;
        mazeexist = true;
    }

    public void Dispalay(Maze maze) {
        maze.showMaze();
    }

    public void FindEscape(Maze maze) {
        maze.showMazeWithEscape();
    }

    public void Exit() {
        exit = true;
    }

    public Maze Return() {
        return this.maze;
    }

    public boolean isExit() {
        return exit;
    }

    public void ChooseFeature(int number, Maze maze) {
        if(mazeexist) {
            switch (number) {
                case 0: {
                    Exit();
                    break;
                }
                case 1: {
                    Generate();
                    break;
                }
                case 2: {
                    Load();
                    break;
                }
                case 3: {
                    Save(maze);
                    break;
                }
                case 4: {
                    Dispalay(maze);
                    break;
                }
                case 5 : {
                    FindEscape(maze);
                    break;
                }
                default: {
                    System.out.println("Incorrect option. Please try again");
                    break;
                }
            }
        } else {
            switch (number) {
                case 0: {
                    Exit();
                    break;
                }
                case 1: {
                    Generate();
                    break;
                }
                case 2: {
                    Load();
                    break;
                }
            }
        }
    }
}
