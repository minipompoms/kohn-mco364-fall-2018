package kohn.maze.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MazeGUI extends JFrame {

    private JButton startButton;
    private JButton stopButton;
    private Maze maze;

    public MazeGUI(){
        maze = new Maze(5, 5);
        maze.generateMaze();
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        setContentPane(panel);

        panel.add(startButton);
        panel.add(stopButton);
        panel.add(maze);

        Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        panel.setBorder(border);




    }


    public static void main(String[] args) {
        new MazeGUI().setVisible(true);
    }
}
