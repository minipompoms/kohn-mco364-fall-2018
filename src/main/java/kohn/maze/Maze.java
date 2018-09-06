package kohn.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Maze {


    private String[][] maze;
    private int SZ = 0;

    public Maze() {
        maze = new String[SZ][SZ];
        generateMaze(SZ);
    }

    public String[][] generateMaze(int SZ) {
        int padding = (SZ * 2) + 1;
        maze = new String[padding][padding];

        for (int row = 0; row < padding; row++) {
            for (int col = 0; col < padding; col++) {
                if (col == 1 && row == 0) {
                    maze[row][col] = "S";
                }
                else if (col == padding && row == 2 * SZ) {
                    maze[row][col] = "E";
                }
                else if (col % 2 == 0) {
                    if (row % 2 == 0) {
                        maze[row][col] = "+";
                    }
                    else {
                        maze[row][col] = "|";
                    }
                }
                else {
                    if (row % 2 == 0) {
                        maze[row][col] = "-";
                    }
                    else {
                        maze[row][col] = "0";
                    }
                }
            }
        }
        return maze;
    }

    public void createPath(String[][] path) {
        Stack<Cell> stack = new Stack<>();
        int size = (path.length - 1) / 2;
        int allCells = size * size;
        int visited = 1;
        Cell current = new Cell(0, 0);

        while (visited < allCells) {
            ArrayList<String> direction = new ArrayList<>();
            Collections.addAll(direction, "N", "E", "S", "W");
            Collections.shuffle(direction);

            String random = checkNeighbors(path, current, direction);

            if (random == "REVERSE") {
                current = stack.pop();
                continue;
            }
            current = removeWall(path, current, random);
            visited = visited + 1;
            stack.push(current);
        }
    }

    private String checkNeighbors(String[][] theMaze, Cell current, ArrayList<String> directions) {
        int size = (theMaze.length - 1) / 2;
        int row = 2 * current.getRow() + 1;
        int col = 2 * current.getCol() + 1;

        if (directions.size() == 0) {
            return "REVERSE";
        }
        String check = directions.remove(0);

        if (check == "N") {
            if (current.getCol() - 1 < 0) {

                return checkNeighbors(theMaze, current, directions);
            }
            if ((theMaze[col - 3][row] == "#" || theMaze[col - 1][row] == "#")
                    || (theMaze[col - 2][row - 1] == "#" || theMaze[col - 2][row + 1] == "#")) {

                return checkNeighbors(theMaze, current, directions);
            }
        }
        else if (check == "E") {
            if (current.getRow() + 1 >= size) {

                return checkNeighbors(theMaze, current, directions);
            }
            if (((theMaze[col + 1][row + 2] == "#" || theMaze[col - 1][row + 2] == "#")
                    || (theMaze[col][row + 1] == "#" || theMaze[col][row + 3] == "#"))) {

                return checkNeighbors(theMaze, current, directions);
            }
        }
        else if (check == "S") {
            if (current.getCol() + 1 >= size) {

                return checkNeighbors(theMaze, current, directions);
            }
            if (((theMaze[col + 1][row] == "#" || theMaze[col + 3][row] == "#")
                    || (theMaze[col + 2][row - 1] == "#" || theMaze[col + 2][row + 1] == "#"))) {

                return checkNeighbors(theMaze, current, directions);
            }
        }
        else if (check == "W") {
            if (current.getRow() - 1 < 0) {

                return checkNeighbors(theMaze, current, directions);
            }
            if (((theMaze[col - 1][row - 2] == "#" || theMaze[col + 1][row - 2] == "#")
                    || (theMaze[col][row - 3] == "#" || theMaze[col][row - 1] == "#"))) {

                return checkNeighbors(theMaze, current, directions);
            }
        }
        return check;
    }


    private Cell removeWall(String[][] theMaze, Cell current, String check) {

        theMaze[1][1] = "#";

        if (check == "N") {
            current.setNext(new Cell(current.getRow(), current.getCol() - 1));
            current = current.getNext();
            theMaze[2 * current.getCol() + 2][2 * current.getRow() + 1] = "#";
            theMaze[2 * current.getCol() + 1][2 * current.getRow() + 1] = "#";

        }
        else if (check == "E") {
            current.setNext(new Cell(current.getRow() + 1, current.getCol()));
            current = current.getNext();
            theMaze[2 * current.getCol() + 1][2 * current.getRow()] = "#";
            theMaze[2 * current.getCol() + 1][2 * current.getRow() + 1] = "#";

        }
        else if (check == "S") {
            current.setNext(new Cell(current.getRow(), current.getCol() + 1));
            current = current.getNext();
            theMaze[2 * current.getCol()][2 * current.getRow() + 1] = "#";
            theMaze[2 * current.getCol() + 1][2 * current.getRow() + 1] = "#";

        }
        else if (check == "W") {
            current.setNext(new Cell(current.getRow() - 1, current.getCol()));
            current = current.getNext();
            theMaze[2 * current.getCol() + 1][2 * current.getRow() + 1] = "#";
            theMaze[2 * current.getCol() + 1][2 * current.getRow() + 2] = "#";
        }
        return current;
    }

    private String displayMaze(String[][] theMaze) {
        StringBuilder display = new StringBuilder();
        int sz = theMaze.length;

        for (int col = 0; col < sz; col++) {
            for (int row = 0; row < sz; row++) {
                if (theMaze[col][row] == "+") {
                    display.append("+");
                }
                else if (theMaze[col][row] == "-") {
                    display.append("---");
                }
                else if (theMaze[col][row] == "|") {
                    display.append("|");
                }
                else if (theMaze[col][row] == "#" && col % 2 == 1) {
                    if (row % 2 == 0) {
                        display.append(" ");
                    }
                    else if (row % 2 == 1) {
                        display.append("   ");
                    }
                }
                else if (theMaze[col][row] == "#" && col % 2 == 0) {
                    display.append("   ");
                }
                else if (theMaze[col][row] == "S" || theMaze[col][row] == "E") {
                    display.append("   ");
                }
                else if (theMaze[col][row] == " " && col % 2 == 1 && row % 2 == 0) {
                    display.append(" ");
                }
                else if (theMaze[col][row] == " ") {
                    display.append("   ");
                }
                else {
                    display.append(" ").append(theMaze[col][row]).append(" ");
                }
                if (row == (sz - 1) && col != (sz - 1)) {
                    display.append(System.lineSeparator());
                }
            }
        }
        return display.toString();
    }


    private String[][] DFS(String[][] theMaze) {
        clearPath(theMaze);
        Stack<Cell> location = new Stack<>();
        int sz = (theMaze.length - 1) / 2;
        int allCells = sz * sz;
        int visited = 1;
        Cell current = new Cell(0, 0);
        theMaze[1][1] = "0";

        while (visited < allCells) {
            ArrayList<String> directions = new ArrayList<>();
            Collections.addAll(directions, "N", "E", "S", "W");
            Collections.shuffle(directions);

            String check = isValidDirection(theMaze, current, directions);

            if (check == "REVERSE") {
                current = location.pop();
                continue;
            }
            current = move(theMaze, current, check, visited);
            visited = visited + 1;
            location.push(current);

            if (current.getRow() == sz - 1 && current.getCol() == sz - 1) {
                return theMaze;
            }
        }

        return theMaze;
    }

    private String[][] clearPath(String[][] theMaze) {
        int sz = theMaze.length;
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                if (theMaze[i][j] == "#") {
                    theMaze[i][j] = " ";
                }
            }
        }
        return theMaze;
    }


    private String isValidDirection(String[][] theMaze, Cell current, ArrayList<String> direction) {
        int sz = (theMaze.length - 1) / 2;
        int row = 2 * current.getRow() + 1;
        int col = 2 * current.getCol() + 1;

        if (direction.size() == 0) {
            return "REVERSE";
        }

        String random = direction.remove(0);

        if (random == "N") {
            if (current.getCol() - 1 < 0) {
                return isValidDirection(theMaze, current, direction);
            }
            if (theMaze[col - 1][row] != " ") {
                return isValidDirection(theMaze, current, direction);
            }
        }
        else if (random == "E") {
            if (current.getRow() + 1 >= sz) {
                return isValidDirection(theMaze, current, direction);
            }
            if (theMaze[col][row + 1] != " ") {
                return isValidDirection(theMaze, current, direction);
            }
        }
        else if (random == "S") {
            if (current.getCol() + 1 >= sz) {
                return isValidDirection(theMaze, current, direction);
            }
            if (theMaze[col + 1][row] != " ") {
                return isValidDirection(theMaze, current, direction);
            }
        }
        else if (random == "W") {
            if (current.getRow() - 1 < 0) {
                return isValidDirection(theMaze, current, direction);
            }
            if (theMaze[col][row - 1] != " ") {
                return isValidDirection(theMaze, current, direction);
            }
        }
        return random;
    }


    private Cell move(String[][] theMaze, Cell current, String check, int count) {

        String path = Integer.toString(count % 10);

        if (check == "N") {
            current.setNext(new Cell(current.getRow(), current.getCol() - 1));
            current = current.getNext();
            theMaze[2 * current.getCol() + 2][2 * current.getRow() + 1] = "#";
            theMaze[2 * current.getCol() + 1][2 * current.getRow() + 1] = path;

        }
        else if (check == "E") {
            current.setNext(new Cell(current.getRow() + 1, current.getCol()));
            current = current.getNext();
            theMaze[2 * current.getCol() + 1][2 * current.getRow()] = "#";
            theMaze[2 * current.getCol() + 1][2 * current.getRow() + 1] = path;

        }
        else if (check == "S") {
            current.setNext(new Cell(current.getRow(), current.getCol() + 1));
            current = current.getNext();
            theMaze[2 * current.getCol()][2 * current.getRow() + 1] = "#";
            theMaze[2 * current.getCol() + 1][2 * current.getRow() + 1] = path;

        }
        else if (check == "W") {
            current.setNext(new Cell(current.getRow() - 1, current.getCol()));
            current = current.getNext();
            theMaze[2 * current.getCol() + 1][2 * current.getRow() + 2] = "#";
            theMaze[2 * current.getCol() + 1][2 * current.getRow() + 1] = path;
        }
        return current;
    }

    class Cell {

        Cell node;
        int row;
        int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public Cell getNext() {
            return node;
        }

        protected void setNext(Cell node) {
            this.node = node;
        }
    }

    public static void main(String[] args) {

        Maze m = new Maze();

        Scanner scan = new Scanner(System.in);
        int size;

        System.out.print("Maze size ? ");
        size = scan.nextInt();

        String[][] theMaze = m.generateMaze(size);
        m.createPath(theMaze);

        System.out.println(m.displayMaze(theMaze));

        System.out.println();

        m.DFS(theMaze);
        System.out.println(m.displayMaze(theMaze));

    }


}
