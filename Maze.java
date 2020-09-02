package maze;

import kotlin.Pair;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Maze {
    boolean[][] maze;
    int[][] mazewithescape;
    int length;
    int width;
    ;

    public Maze(int length, int width) {
        this.width = width;
        this.length = length;
        maze = new boolean[width][length];
        mazewithescape = new int[width][length];
        ArrayList<Integer> escape = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> pathtoescape = new ArrayList<>();


        Graph graph = new Graph((width - 1) / 2, (length / 2));
        Pair<ArrayList<Pair<Integer, Integer>>, ArrayList<Integer>> path =
                graph.createMinimumSpaningTree();
        for (Pair<Integer, Integer> cell : path.getFirst()) {

            int x = ((cell.getFirst() % (length / 2)) * 2 + 1 + (cell.getSecond() % (length / 2)) * 2 + 1) / 2;
            int y = ((cell.getFirst() / (length / 2)) * 2 + 1 + (cell.getSecond() / (length / 2)) * 2 + 1) / 2;

            maze[y][x] = true;
            mazewithescape[y][x] = 1;
        }
        //create escape
        int node = (width - 1) / 2 * (length / 2) - 1;
        while (node != 0) {
            escape.add(node);
            for (Pair<Integer, Integer> cell : path.getFirst()) {
                if (cell.getSecond() == node) {
                    pathtoescape.add(cell);
                    node = cell.getFirst();
                    break;
                }
            }
        }
        escape.add(node);

        for (Pair<Integer, Integer> cell : pathtoescape) {

            int x = ((cell.getFirst() % (length / 2)) * 2 + 1 + (cell.getSecond() % (length / 2)) * 2 + 1) / 2;
            int y = ((cell.getFirst() / (length / 2)) * 2 + 1 + (cell.getSecond() / (length / 2)) * 2 + 1) / 2;

            mazewithescape[y][x] = 2;
        }

        for (int i = 1; i < width; i += 2) {
            for (int j = 1; j < length; j += 2) {
                maze[i][j] = true;
                mazewithescape[i][j] = 1;
            }
        }

        for (Integer cell : escape) {

            int x = ((cell % (length / 2)) * 2 + 1);
            int y = ((cell / (length / 2)) * 2 + 1);

            mazewithescape[y][x] = 2;
        }

        for (int i = 0; i < length; i++) {
            maze[width - 1][i] = false;
        }
        for (int j = 0; j < width; j++) {
            maze[j][length - 1] = false;
        }

        maze[1][0] = true;
        mazewithescape[1][0] = 2;
        maze[width - 2][length - 1] = true;
        maze[width - 2][length - 2] = true;
        mazewithescape[width - 2][length - 1] = 2;
        mazewithescape[width - 2][length - 2] = 2;
    }


    public void showMaze() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (!maze[i][j]) {
                    System.out.print("\u2588\u2588");
                } else {

                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public void showMazeWithEscape() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (mazewithescape[i][j] == 0) {
                    System.out.print("\u2588\u2588");
                } else if (mazewithescape[i][j] == 1) {

                    System.out.print("  ");
                } else {
                    System.out.print("//");
                }
            }
            System.out.println();
        }
    }

    public void SaveMaze(String name) {
        try (FileWriter writer = new FileWriter(name, false)) {
            writer.write(width);
            writer.write(length);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < length; j++) {
                    if(mazewithescape[i][j] == 0) {
                        writer.write(0);
                    } else if(mazewithescape[i][j] == 1) {
                        writer.write(1);
                    } else {
                        writer.write(2);
                    }
                    writer.write(" ");
                }
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public void LoadMaze(String name) {
        try (FileReader reader = new FileReader(name)) {
            this.length = reader.read();
            this.width = reader.read();
            int c;
            ArrayList<Integer> maze = new ArrayList<>();
            while ((c = reader.read()) != -1) {
                if (c != 0 && c != 1 && c != 2 && c != 32) {
                    System.out.println("Cannot load the maze. It has invalid format");
                    return;
                }
                if (c == 32) {
                    continue;
                }
                maze.add(c);
            }
            this.maze = new boolean[width][length];
            this.mazewithescape = new int[width][length];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < length; j++) {
                    this.maze[i][j] = maze.get(i * length + j) != 0;
                    this.mazewithescape[i][j] = maze.get(i * length + j);
                }
            }
        } catch (IOException ex) {
            System.out.println("The file ... does not exist");
        }
    }

}

