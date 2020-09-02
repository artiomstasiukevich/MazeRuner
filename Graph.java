package maze;

import kotlin.Pair;

import java.util.ArrayList;
import java.util.Random;

public class Graph {
    int width;
    int length;
//    Random random = new Random();
    Vertex[][] vertexes;
    Edge[] edges;

    Pair<ArrayList<Pair<Integer, Integer>>, ArrayList<Integer>> createMinimumSpaningTree() {
        ArrayList<Pair<Integer, Integer>> inProcess = new ArrayList<>();
        ArrayList<Integer> nodes = new ArrayList<>();
        nodes.add(0);
        while (nodes.size() < width * length) {
            int min = 11;
            Edge minEdge = null;
            int nodetoadd = 0;
            for (Integer node : nodes) {
                for (Edge edge : edges) {

                    if (nodes.contains(edge.neghbours.getFirst())
                            && nodes.contains(edge.neghbours.getSecond())) {
                        continue;
                    }

                    if (edge.neghbours.getFirst().equals(node) ||
                            edge.neghbours.getSecond().equals(node)) {
                        if (edge.weight < min) {
                            min = edge.weight;
                            minEdge = edge;
                            nodetoadd = edge.neghbours.getFirst().equals(node) ?
                                    edge.neghbours.getSecond() : edge.neghbours.getFirst();
                        }
                    }

                }
            }
            if (minEdge != null) {
                inProcess.add(new Pair<>(minEdge.neghbours.getFirst() == nodetoadd ? minEdge.neghbours.getSecond() : minEdge.neghbours.getFirst()
                        ,nodetoadd));
                nodes.add(nodetoadd);
            }
        }
        return new Pair<>(inProcess, nodes);
    }


    public Graph(int width, int length) {
        this.width = width;
        this.length = length;

        vertexes = new Vertex[width][length];

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < length; j++) {
                vertexes[i][j] = new Vertex(i*length + j);
            }
        }
        edges = new Edge[(width - 1) * length + (length - 1) * width];
        for(int i = 0; i < edges.length; i++) {
            edges[i] = new Edge();
        }


        int a = 0;
        int num = 0;
        int b = 1;
        for (Edge edge : edges) {
            if (num < length - 1) {
                edge.setNeghbours(new Pair<Integer, Integer>(a, ++a));
                num += 1;
            } else {
                edge.setNeghbours(new Pair<Integer, Integer>(a - length + b,
                        a  + b));
                b += 1;
                num += 1;
                if (num == 2 * length - 1) {
                    num = 0;
                    b = 1;
                    a += 1;
                }
            }
        }
    }

    private class Vertex {
        int number;

        public Vertex(int number) {
            this.number = number;
        }
    }

    private class Edge {
        int weight;
        Pair<Integer, Integer> neghbours;

        public Edge() {
            this.weight = (int) (Math.random()*10);
            ;
        }

        public void setNeghbours(Pair<Integer, Integer> neghbours) {
            this.neghbours = neghbours;
        }
    }
}
