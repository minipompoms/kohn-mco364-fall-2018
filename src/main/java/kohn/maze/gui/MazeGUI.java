package kohn.maze.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeGUI extends JFrame implements ActionListener {

    MazeView maze = new MazeView();
    JPanel panel = new JPanel();

    String width;
    String height;
    int rows = 10;
    int cols = 10;

    JTextField heightField;
    JTextField widthField;
    JLabel heightLabel;
    JLabel widthLabel;
    JButton enterButton;

    public MazeGUI() {

        setTitle("Maze Viewer");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(340, 90);

        Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        panel.setBorder(border);
        panel.setLayout(new BorderLayout(2, 1));

        JPanel entries = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.TRAILING, 30, 10);
        entries.setLayout(fl);

        heightField = new JTextField();
        widthField = new JTextField();
        heightField.setColumns(6);
        widthField.setColumns(6);
        heightLabel = new JLabel(" H: ");
        widthLabel = new JLabel(" W: ");
        heightField.setText(String.valueOf(cols));
        widthField.setText(String.valueOf(rows));
        entries.add(widthLabel);
        entries.add(widthField);
        entries.add(heightLabel);
        entries.add(heightField);
        panel.add(entries, BorderLayout.NORTH);

        enterButton = new JButton(" Enter ");
        enterButton.addActionListener(e -> {
            width = widthField.getText();
            height = heightField.getText();
            widthField.setText(width);
            heightField.setText(height);
            rows = Integer.valueOf(width);
            cols = Integer.valueOf(height);
            maze.setRows(rows);
            maze.setCols(cols);
            maze.createMaze();
            repaint();
        });

        maze.setRows(rows);
        maze.setCols(rows);
        maze.createMaze();
        entries.add(enterButton);
        panel.add(maze, BorderLayout.CENTER);
        add(panel);
        Color grey = new Color(180, 180, 180);
        entries.setBackground(Color.GRAY);
        panel.setBackground(grey);
    }


    public static void main(String[] args) {

        new MazeGUI().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
