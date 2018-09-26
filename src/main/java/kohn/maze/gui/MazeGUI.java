package kohn.maze.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MazeGUI extends JFrame {

    private Maze maze;

    JPanel panel = new JPanel();


    public MazeGUI() {


        maze = new Maze(20, 20);

        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        panel.setBorder(border);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 480;
        c.weightx = 1;
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,10,10,0);

        maze.generateMaze();
        panel.add(maze,c);

        add(panel);
    }

    public static void main(String[] args) {
        new MazeGUI().setVisible(true);
    }


}
