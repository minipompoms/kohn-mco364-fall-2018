package kohn.maze.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MazeGUI extends JFrame {

    private JButton startButton;

    private JTextField width;
    private JTextField height;
    int x,y;
    private Maze maze;
    public MazeGUI(){
        maze = new Maze(10, 10);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startButton = new JButton("Start");
        height = new JTextField();
        width = new JTextField();
        width.setPreferredSize(new Dimension(10,20));
        height.setPreferredSize(new Dimension(10,20));
        width.setEditable(true);
        height.setEditable(true);
        JLabel heightLabel = new JLabel("height");
        JLabel widthLabel = new JLabel("width");

        startButton.addActionListener(e -> {
                    onStart();
           }
        );
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridy =0;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        panel.add(widthLabel, constraints);
        constraints.gridy =0;
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        panel.add(width, constraints);
        constraints.gridy =0;
        constraints.gridx = 2;
        panel.add(heightLabel, constraints);
        constraints.gridy =0;
        constraints.gridx = 3;
        panel.add(height, constraints);
        constraints.gridy =0;
        constraints.gridx = 4;
        panel.add(startButton, constraints);

        constraints.ipady=350;
        constraints.weightx =0;
        constraints.ipadx = 320;
        constraints.gridx = 1;
        constraints.gridy = 1;

        panel.add(maze, constraints);

        Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        panel.setBorder(border);

        add(panel);

    }

    public void onStart(){
        x = Integer.parseInt(String.valueOf(width.getText()));
        y = Integer.parseInt(String.valueOf(height.getText()));
        maze = new Maze(x,y);
        maze.generateMaze();

    }
    public static void main(String[] args) {
        new MazeGUI().setVisible(true);
    }
}
