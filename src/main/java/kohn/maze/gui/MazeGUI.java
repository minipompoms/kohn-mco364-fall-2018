package kohn.maze.gui;

import javax.swing.*;
import javax.swing.border.Border;

public class MazeGUI extends JFrame {


    MazeView maze;

    public MazeGUI() {


        setTitle("Maze");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(340, 90);

        Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        JPanel panel = new JPanel();
        panel.setBorder(border);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        maze = new MazeView();
        panel.add(maze);
        add(panel);


    }


    public static void main(String[] args) {

        new MazeGUI().setVisible(true);
    }


}
