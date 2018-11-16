package kohn.paint;

import java.awt.*;

public class PencilTool implements Tool {

    Line line;

    public PencilTool(Line line){
        this.line = line;
    }

    @Override
    public void onDrag(Point point) {
        line.getList().add(new Point(point));
    }
}
