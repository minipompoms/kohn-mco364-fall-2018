package kohn;

import kohn.paint.Canvas;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaintTest {
    Canvas canvas = new Canvas();

    @Test
    void createCanvas(){
        assertNotNull(canvas);
    }

    
}
