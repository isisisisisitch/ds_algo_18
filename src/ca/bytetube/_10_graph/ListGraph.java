package ca.bytetube._10_graph;

import java.util.*;

public class ListGraph<V, E> implements Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();

    private Set<Edge<V, E>> edges = new HashSet<>();

    public ListGraph() {
    }

    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inDegrees = new HashSet<>();

        Set<Edge<V, E>> outDegrees = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value) &&
                    Objects.equals(inDegrees, vertex.inDegrees) &&
                    Objects.equals(outDegrees, vertex.outDegrees);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return this.value == null ? "null" : this.value.toString();
        }
    }


    private static class Edge<V, E> {
        E weight;
        Vertex<V, E> from;
        Vertex<V, E> to;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
            weight = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<V, E> edge = (Edge<V, E>) o;
            return Objects.equals(weight, edge.weight) &&
                    Objects.equals(from, edge.from) &&
                    Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +  ", weight=" + weight +
                    '}';
        }
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        //1.先判断from，to 是否存在
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);

        }

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);

        }

        //能来到这，说明一定可以保证有起点有终点
        //接下来需要确定起点终点间是否之前就已经存在边，如果不存在，则新建一条边，存在则更新weight
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;
        if (fromVertex.outDegrees.remove(edge)) {
            toVertex.inDegrees.remove(edge);
            edges.remove(edge);
        }

        fromVertex.outDegrees.add(edge);
        toVertex.inDegrees.add(edge);
        edges.add(edge);


    }


    @Override
    public void addVertex(V v) {

    }

    @Override
    public void removeVertex(V v) {

    }


    @Override
    public void removeEdge(V from, V to) {

    }

    public void printGraph() {
        System.out.println("[vertex]---------------------");
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            System.out.println(v);
            System.out.println("out---------------------");
            System.out.println(vertex.outDegrees);
            System.out.println("in---------------------");
            System.out.println(vertex.inDegrees);
        });
        System.out.println("[edge]---------------------");
        edges.forEach((Edge<V, E> edge) -> {
            System.out.println(edge);
        });

    }


}
