package kohn.paint;

import java.awt.*;

public class ShapeTool implements Tool {

    Rectangle rectangle;

    public ShapeTool(Rectangle rectangle){
        this.rectangle = rectangle;
    }
    @Override
    public void onDrag(Point point) {

    }
}
