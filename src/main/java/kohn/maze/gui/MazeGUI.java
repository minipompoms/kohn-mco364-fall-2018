package kohn.maze.gui;

import javax.swing.*;

public class MazeGUI extends JFrame {

    private JButton startButton;

    private JTextField width;
    private JTextField height;
    int x;
    int y;




    public MazeGUI() {


        setTitle("Maze");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(340,90);

        //Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        JPanel panel = new JPanel();
      //  panel.setBorder(border);
        //panel.setLayout(new GridBagLayout());

        startButton = new JButton("Start");
        startButton.setOpaque(true);
        height = new JTextField();
        height.setText(String.valueOf(y));
        width = new JTextField();
        width.setText(String.valueOf(x));
        width.setColumns(2);
        height.setColumns(1);
        width.setEditable(true);
        height.setEditable(true);
        JLabel heightLabel = new JLabel("    H: ");
        JLabel widthLabel = new JLabel(" W:");
/*
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 10, 0, 0);
        c.weighty = .75;
        c.weightx = .05;
        c.gridx = 0;
        c.gridy = 0;
        // panel.add(widthLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.25;
        c.weighty = .75;
        c.gridx = 1;
        c.gridy = 0;
        //panel.add(width, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 20, 0, 0);

        c.weightx = 0.05;
        c.weighty = .75;
        c.gridx = 2;
        c.gridy = 0;
        //panel.add(heightLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.25;
        c.weighty = .75;
        c.gridx = 3;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 10);
        // panel.add(height, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 10, 0, 20);
        c.weightx = 0.1;
        c.weighty = .45;
        c.gridx = 4;
        c.gridy = 0;
        //panel.add(startButton, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 480;
        c.weightx = 0.0;
        c.gridwidth = 5;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.PAGE_END;*/
        // c.insets = new Insets(0,0,10,0);  //top padding

        //  startButton.addActionListener(e -> onStart());


        //   x = Integer.parseInt(String.valueOf(width.getText()));
        //    y = Integer.parseInt(String.valueOf(height.getText()));

        MazeView maze = new MazeView();
        panel.add(maze);
        add(panel);


    }

    public void onStart() {


        x = Integer.parseInt(String.valueOf(width.getText()));
        y = Integer.parseInt(String.valueOf(height.getText()));




    }


    public static void main(String[] args) {

        new MazeGUI().setVisible(true);
    }


}
