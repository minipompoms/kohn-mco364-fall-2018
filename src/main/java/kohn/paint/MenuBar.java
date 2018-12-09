package kohn.paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class MenuBar extends JMenuBar {

    private BufferedImage bufferedImage;

    private String filename = null;
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem exit;

    public MenuBar(Canvas canvas) {

        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");

        open.addActionListener(e -> {
            open(canvas);
        });

        save.addActionListener(e -> {
            save(filename, canvas);
            canvas.saveObject();
        });

        exit.addActionListener(e -> {
            System.exit(0);
        });



    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(exit);

        menuBar.add(fileMenu);
        return menuBar;
    }


    private void open(Canvas canvas) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            String filename = file.getPath();
            try {
                bufferedImage = ImageIO.read(new File(filename));
                canvas.setImage(bufferedImage);
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


    public void save(String name, Canvas canvas) {
        bufferedImage = new BufferedImage(canvas.getWidth(), canvas.getHeight(), TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        canvas.paint(g2);
        if (name == null) {
            JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(null) != JFileChooser.CANCEL_OPTION)
                name = fc.getSelectedFile().getAbsolutePath();
        }
        if (name != null) {
            try {
                File file = new File(name);
                ImageIO.write(bufferedImage, getFileExtension(file), file);
                JOptionPane.showMessageDialog(null, file.getName() + " successfully saved",
                        "Save File", JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error has occurred: File not saved. " + name,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }



}
