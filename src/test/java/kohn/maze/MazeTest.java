package kohn.maze;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MazeTest {

    Maze maze = new Maze(5,5);

    @Test
    void testMaze() {
        assertNotNull(maze);
    }

    @Test
    void generateMaze() {
    }

    @Test
    void createPath() {
        
    }

    @Test
    void getRows() {
        assertEquals(5, maze.getRows());
    }

    @Test
    void getCols() {
        assertFalse(maze.getCols() == 10);
    }
}