import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

record Edge(double x1, double x2, double y1, double y2) {
}

public class p2 {
    public static void main(String[] args) throws IOException {
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            List<List<Integer>> redTiles = new ArrayList<>();
            List<Edge> horizontalEdges = new ArrayList<>();
            List<Edge> verticalEdges = new ArrayList<>();
            String line;
            while ((line = input.readLine()) != null) {
                String[] coords = line.split(",");
                redTiles.add(List.of(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])));
            }

            redTiles.add(redTiles.getFirst());

            for (int i = 1; i < redTiles.size(); i++) {
                int x1 = redTiles.get(i - 1).getFirst(), y1 = redTiles.get(i - 1).getLast();
                int x2 = redTiles.get(i).getFirst(), y2 = redTiles.get(i).getLast();

                if (x1 > x2) {
                    int temp = x1;
                    x1 = x2;
                    x2 = temp;
                }

                if (y1 > y2) {
                    int temp = y1;
                    y1 = y2;
                    y2 = temp;
                }

                if (x1 == x2)
                    verticalEdges.add(new Edge(x1, x2, y1, y2));
                else
                    horizontalEdges.add(new Edge(x1, x2, y1, y2));
            }

            redTiles.removeLast();

            long result = 0;
            for (int i = 0; i < redTiles.size(); i++) {
                for (int j = i + 1; j < redTiles.size(); j++) {
                    int x1 = redTiles.get(i).getFirst(), y1 = redTiles.get(i).getLast();
                    int x2 = redTiles.get(j).getFirst(), y2 = redTiles.get(j).getLast();

                    long area = (1L + Math.abs(x1 - x2)) * (1L + Math.abs(y1 - y2));
                    boolean valid = true;

                    double minX = Math.min(x1, x2);
                    double maxX = Math.max(x1, x2);
                    double minY = Math.min(y1, y2);
                    double maxY = Math.max(y1, y2);

                    for (Edge he : horizontalEdges) {
                        if (he.y1() > minY && he.y1() < maxY &&
                                he.x2() > minX && he.x1() < maxX) {
                            valid = false;
                            break;
                        }
                    }

                    if (valid) {
                        for (Edge ve : verticalEdges) {
                            if (ve.x1() > minX && ve.x1() < maxX &&
                                    ve.y2() > minY && ve.y1() < maxY) {
                                valid = false;
                                break;
                            }
                        }
                    }

                    if (valid) {
                        double cx = minX + 0.5;
                        double cy = minY + 0.5;
                        if (isInside(cx, cy, verticalEdges)) {
                            result = Math.max(result, area);
                        }
                    }
                }
            }

            System.out.println(result);
        }
    }

    static boolean isInside(double cx, double cy, List<Edge> verticalEdges) {
        int intersections = 0;
        for (Edge ve : verticalEdges) {
            if (ve.x1() > cx && cy > ve.y1() && cy < ve.y2()) {
                intersections++;
            }
        }
        return intersections % 2 != 0;
    }
}