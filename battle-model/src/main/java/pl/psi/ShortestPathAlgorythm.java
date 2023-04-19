package pl.psi;
import java.awt.Point;
import java.util.*;

public class ShortestPathAlgorythm {
    private int[][] grid;
    private int rows, cols;
    private PriorityQueue<Point> openSet;
    private HashSet<Point> closedSet;
    private Point start, end;

    public ShortestPathAlgorythm(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        openSet = new PriorityQueue<>(new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return Integer.compare(getFCost(p1), getFCost(p2));
            }
        });
        closedSet = new HashSet<>();
    }

    public List<Point> findPath(int startX, int startY, int endX, int endY) {
        start = new Point(startX, startY);
        end = new Point(endX, endY);
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Point curr = openSet.poll();

            if (curr.equals(end)) {
                List<Point> path = new ArrayList<>();
                while (!curr.equals(start)) {
                    path.add(curr);
                    curr = getParent(curr);
                }
                Collections.reverse(path);
                return path;
            }

            closedSet.add(curr);

            for (Point neighbor : getNeighbors(curr)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGCost = getGCost(curr) + getDistance(curr, neighbor);

                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tentativeGCost >= getGCost(neighbor)) {
                    continue;
                }

                setParent(neighbor, curr);
                setGCost(neighbor, tentativeGCost);
                setHCost(neighbor, getDistance(neighbor, end));
            }
        }

        return null;
    }

    private List<Point> getNeighbors(Point point) {
        List<Point> neighbors = new ArrayList<>();
        int x = point.x;
        int y = point.y;

        if (x > 0) {
            neighbors.add(new Point(x - 1, y));
        }
        if (y > 0) {
            neighbors.add(new Point(x, y - 1));
        }
        if (x < rows - 1) {
            neighbors.add(new Point(x + 1, y));
        }
        if (y < cols - 1) {
            neighbors.add(new Point(x, y + 1));
        }

        return neighbors;
    }

    private int getDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    private int getGCost(Point point) {
        return grid[point.x][point.y];
    }

    private void setGCost(Point point, int gCost) {
        grid[point.x][point.y] = gCost;
    }

    private int getHCost(Point point) {
        return getDistance(point, end);
    }

    private void setHCost(Point point, int hCost) {
        // Do nothing since we're using Manhattan distance as our heuristic
    }

    private int getFCost(Point point) {
        return getGCost(point) + getHCost(point);
    }
    private Point getParent(Point point) {
        int x = point.x;
        int y = point.y;
        int parentX = grid[x][y] >> 16;
        int parentY = grid[x][y] & 0xffff;
        if (parentX == -1 || parentY == -1) {
            return null;
        }
        return new Point(parentX, parentY);
    }
    private void setParent(Point point, Point parent) {
        int x = point.x;
        int y = point.y;
        int parentX = parent.x;
        int parentY = parent.y;
        grid[x][y] = (parentX << 16) | parentY;
    }

}
