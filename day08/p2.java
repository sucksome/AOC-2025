import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class Point {
    double getDistance(Point b) {
        return Math.sqrt(Math.pow(b.x - x, 2) + Math.pow(b.y - y, 2) + Math.pow(b.z - z, 2));
    }

    Point(String csv) {
        String[] coordinates = csv.split(",");
        x = Long.parseLong(coordinates[0]);
        y = Long.parseLong(coordinates[1]);
        z = Long.parseLong(coordinates[2]);
    }

    long x, y, z;
}

class DSU {
    HashMap<Point, Point> parent = new HashMap<>();
    HashMap<Point, Integer> size = new HashMap<>();

    Point find(Point a) {
        parent.putIfAbsent(a, a);
        size.putIfAbsent(a, 1);

        if (parent.get(a).equals(a))
            return a;
        parent.put(a, find(parent.get(a)));
        return parent.get(a);
    }

    boolean union(Point a, Point b) {
        Point pa = find(a), pb = find(b);
        if (pa.equals(pb))
            return false;

        if (size.get(pb) > size.get(pa)) {
            size.merge(pb, size.get(pa), Integer::sum);
            size.remove(pa);
            parent.put(pa, pb);
        } else {
            size.merge(pa, size.get(pb), Integer::sum);
            size.remove(pb);
            parent.put(pb, pa);
        }

        return true;
    }
}

public class p2 {

    public static void main(String[] args) throws IOException {
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            ArrayList<Point> points = new ArrayList<>();
            String line;
            while ((line = input.readLine()) != null)
                points.add(new Point(line));

            Comparator<List<Double>> minComparator = Comparator.comparing((List<Double> list) -> list.get(0))
                    .thenComparing(list -> list.get(1)).thenComparing(list -> list.get(2));
            PriorityQueue<List<Double>> minDistances = new PriorityQueue<>(minComparator);
            DSU dsu = new DSU();

            for (int i = 0; i < points.size(); i++) {
                for (int j = i + 1; j < points.size(); j++) {
                    minDistances.add(List.of(points.get(i).getDistance(points.get(j)), (double) i, (double) j));
                }
            }

            long x1 = 0, x2 = 0;
            while (!minDistances.isEmpty()) {
                int i = minDistances.peek().get(1).intValue(), j = minDistances.peek().get(2).intValue();
                minDistances.poll();
                if (dsu.union(points.get(i), points.get(j))) {
                    x1 = points.get(i).x;
                    x2 = points.get(j).x;
                }
            }

            System.out.println(x1 * x2);
        }
    }
}