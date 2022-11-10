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
            return Objects.equals(from, edge.from) &&
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
                    ", to=" + to + ", weight=" + weight +
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
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;

        //代码能够来到这，说明起点和终点都存在，但是不代表起点终点之间有边
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outDegrees.remove(edge)) {
            toVertex.inDegrees.remove(edge);
            edges.remove(edge);
        }
    }


    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) return;

//        vertex.outDegrees.forEach((Edge<V, E> edge)->{
//            removeEdge(edge.from.value,edge.to.value);
//            edges.remove(edge);
//        });
//
//
//        vertex.inDegrees.forEach((Edge<V, E> edge)->{
//            removeEdge(edge.to.value,edge.from.value);
//            edges.remove(edge);
//        });

        //如果你有边遍历边删除的需求，请使用迭代器
        for (Iterator<Edge<V, E>> iterator = vertex.outDegrees.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            //需要通过这条边找到这条边的终点，在终点edge.to的inDegrees中把edge删掉
            edge.to.inDegrees.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

        for (Iterator<Edge<V, E>> iterator = vertex.inDegrees.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            //需要通过这条边找到这条边的终点，在终点edge.to的inDegrees中把edge删掉
            edge.from.outDegrees.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

    }

    @Override
    public void bfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();

        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);
        while (!queue.isEmpty()) {
            Vertex<V, E> poll = queue.poll();
            System.out.print(poll.value + " ");
            for (Edge<V, E> edge : poll.outDegrees) {
                if (visitedVertices.contains(edge.to)) continue;
                queue.offer(edge.to);
                visitedVertices.add(edge.to);
            }

        }
    }

    /**
     * 1.先将起点入栈（打印），然后加入到set容器中，
     * 2.弹出栈顶vertex，从它的outDegrees中选取一条边，将这条边的from，to顶点再次放入栈中，
     * 3.访问to
     * 4.将to放入到set中
     * 5.break（不去访问outDegrees中的其他边，而是访问一条边上剩余的点）
     */
    @Override
    public void dfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();
        //1.先将起点入栈（打印），然后加入到set容器中，再从它的outDegrees中选一条边
        stack.push(beginVertex);
        System.out.print(beginVertex.value + " ");
        visitedVertices.add(beginVertex);
        while (!stack.isEmpty()) {
            //2.弹出栈顶vertex，从它的outDegrees中选取一条边，将这条边的from，to顶点再次放入栈中，
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.outDegrees) {
                if (visitedVertices.contains(edge.to)) continue;
                stack.push(edge.from);
                stack.push(edge.to);
                //3.访问to
                System.out.print(edge.to.value + " ");
                //4.将to放入到set中
                visitedVertices.add(edge.to);
                //5.break（不去访问outDegrees中的其他边，而是访问一条边上剩余的点）
                break;
            }
        }
    }


    public void dfs1(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        dfs1(beginVertex, visitedVertices);
    }

    public void dfs1(Vertex<V, E> beginVertex, Set<Vertex<V, E>> set) {
        System.out.print(beginVertex.value + " ");
        set.add(beginVertex);
        for (Edge<V, E> edge : beginVertex.outDegrees) {
            if (set.contains(edge.to)) continue;
            dfs1(edge.to, set);
        }


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
