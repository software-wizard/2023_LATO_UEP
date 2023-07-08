// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ShortestPathAlgorythm {
    private static class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int g;
        private int h;
        private int f;
        private Node parent;

        public Node(Point aPoint) {
            this.x = aPoint.getX();
            this.y = aPoint.getY();
            this.g = 0;
            this.h = 0;
            this.f = 0;
            this.parent = null;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getG() {
            return g;
        }

        public void setG(int g) {
            this.g = g;
        }

        public void setH(int h) {
            this.h = h;
        }

        public void setF(int f) {
            this.f = f;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getParent() {
            return parent;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f);
        }
    }

    public  List<Point> findPath(int[][] grid, Point aStart, Point aEnd) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Validate start and goal positions
        if (aStart.getX() < 0 || aStart.getX() >= rows || aStart.getY() < 0 || aStart.getY() >= cols ||
                aEnd.getX() < 0 || aEnd.getX() >= rows || aEnd.getY() < 0 || aEnd.getY() >= cols) {
            throw new IllegalArgumentException("Invalid start or goal position");
        }

        // Initialize open and closed sets
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        boolean[][] closedSet = new boolean[rows][cols];

        // Create start and goal nodes
        Node startNode = new Node(aStart);
        Node goalNode = new Node(aEnd);

        // Add start node to open set
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            // Get the node with the lowest f value from the open set
            Node current = openSet.poll();

            // Mark the current node as visited
            closedSet[current.getX()][current.getY()] = true;

            // Check if the goal has been reached
            if (current.getX() == goalNode.getX() && current.getY() == goalNode.getY()) {
                // Reconstruct the path
                return reconstructPath(current);
            }

            // Generate neighbor nodes
            List<Node> neighbors = generateNeighbors(current, grid, rows, cols);

            for (Node neighbor : neighbors) {
                // Skip if neighbor is already visited or is a wall
                if (closedSet[neighbor.getX()][neighbor.getY()] || grid[neighbor.getX()][neighbor.getY()] == Integer.MAX_VALUE) {
                    continue;
                }

                // Calculate tentative g and h scores
                int tentativeG = current.getG() + 1;
                int h = calculateHeuristic(neighbor, goalNode);

                // Check if neighbor is in open set or has a better g score
                if (!openSet.contains(neighbor) || tentativeG < neighbor.getG()) {
                    // Update neighbor values
                    neighbor.setParent(current);
                    neighbor.setG(tentativeG);
                    neighbor.setH(h);
                    neighbor.setF(tentativeG + h);

                    // Add neighbor to open set if it's not already present
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        // No path found
        return null;
    }

    private List<Point> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();

        while (node != null) {
            path.add(node);
            node = node.getParent();
        }

        // Reverse the path since it was constructed backwards
        Collections.reverse(path);

        return path.stream().map(n -> new Point(n.getX(), n.getY())).collect(Collectors.toList());
    }

    private List<Node> generateNeighbors(Node node, int[][] grid, int rows, int cols) {
        int x = node.getX();
        int y = node.getY();
        List<Node> neighbors = new ArrayList<>();

        // Generate all possible neighbor positions
        int[][] positions = {
                {x - 1, y}, {x + 1, y}, {x, y - 1}, {x, y + 1},
        };

        for (int[] pos : positions) {
            int newX = pos[0];
            int newY = pos[1];
            Point newPoint = new Point(newX,newY);

            // Check if the neighbor position is valid
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                neighbors.add(new Node(newPoint));
            }
        }

        return neighbors;
    }

    private int calculateHeuristic(Node node, Node goalNode) {
        // Manhattan distance heuristic
        return Math.abs(node.getX() - goalNode.getX()) + Math.abs(node.getY() - goalNode.getY());
    }
    /*
    private int[][] grid;
    private int rows, cols;
    private PriorityQueue<Point> openSet;
    private HashSet<Point> closedSet;
    private Point start, end;

    public ShortestPathAlgorythm(int[][] grid) {
        this.grid = grid;
        rows = (grid.length)-1;
        cols = (grid[0].length)-1;
        openSet = new PriorityQueue<>(new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return Integer.compare(getFCost(p1), getFCost(p2));
            }
        });
        closedSet = new HashSet<>();
    }

    public List<Point> findPath(Point start, Point end) {
        this.start = new Point(start.getY(), start.getX());
        this.end = new Point(end.getY(), end.getX());
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
        int x = point.getX();
        int y = point.getY();

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
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("Points cannot be null");
        }
        return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
    }

    private int getGCost(Point point) {
        return grid[point.getX()][point.getY()];
    } //start - current

    private void setGCost(Point point, int gCost) {
        grid[point.getX()][point.getY()] = gCost;
    }

    private int getHCost(Point point) {
        return getDistance(point, end);
    } // current - goal

    private void setHCost(Point point, int hCost) {
        // Do nothing since we're using Manhattan distance as our heuristic
    }

    private int getFCost(Point point) {
        return getGCost(point) + getHCost(point);
    } // start - goal
    private Point getParent(Point point) {
        int x = point.getX();
        int y = point.getY();
        int parentX = grid[x][y] >> 16;
        int parentY = grid[x][y] & 0xffff;
        if (parentX == -1 || parentY == -1) {
            return null;
        }
        return new Point(parentX, parentY);
    }
    private void setParent(Point point, Point parent) {
        int x = point.getX();
        int y = point.getY();
        int parentX = parent.getX();
        int parentY = parent.getY();
        grid[x][y] = (parentX << 16) | parentY;
    }
     */

}
