package pl.psi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShortestPathAlgorythmTest {
    @Test
    void shouldFindShortestPathWithoutAnyObstacles(){
        int [][] grid = new int[5][5];
        for( int x = 0; x < 5; x++ ){
            for( int y = 0; y < 5; y++ )
            {
                grid[x][y] = 1;
            }
        }
        ShortestPathAlgorythm pathAlgorythm = new ShortestPathAlgorythm(grid);
        List<Point> path = pathAlgorythm.findPath(new Point(0, 1), new Point(5, 5));
        assertThat(path).isNotEmpty();
    }

}
