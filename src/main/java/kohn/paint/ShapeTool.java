package kohn.paint;

import java.awt.*;

public class ShapeTool implements Tool {

    private Rectangle rectangle;

    public ShapeTool(Rectangle rectangle){
        this.rectangle = rectangle;
    }


    @Override
    public void onDrag(Point point) {
        rectangle = new Rectangle(rectangle.getColor());
    }
}
