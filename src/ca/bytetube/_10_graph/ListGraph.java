package ca.bytetube._10_graph;

import java.util.*;

public class ListGraph<V, E> extends Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();

    private Set<Edge<V, E>> edges = new HashSet<>();


    private Comparator<Edge<V, E>> edgeComparator = new Comparator<Edge<V, E>>() {
        @Override
        public int compare(Edge<V, E> o1, Edge<V, E> o2) {
            return weightManager.compare(o1.weight, o2.weight);
        }
    };


    public ListGraph() {
    }

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
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


    public static class Edge<V, E> {
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


        public EdgeInfo<V, E> info() {
            return new EdgeInfo(weight, from.value, to.value);
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

    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) return;
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();

        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);
        while (!queue.isEmpty()) {
            Vertex<V, E> poll = queue.poll();
//            System.out.print(poll.value + " ");
            if (visitor.visit(poll.value)) return;

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


    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) return;
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();
        //1.先将起点入栈（打印），然后加入到set容器中，再从它的outDegrees中选一条边
        stack.push(beginVertex);
        // System.out.print(beginVertex.value + " ");
        if (visitor.visit(beginVertex.value)) return;
        visitedVertices.add(beginVertex);
        while (!stack.isEmpty()) {
            //2.弹出栈顶vertex，从它的outDegrees中选取一条边，将这条边的from，to顶点再次放入栈中，
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.outDegrees) {
                if (visitedVertices.contains(edge.to)) continue;
                stack.push(edge.from);
                stack.push(edge.to);
                //3.访问to
                //System.out.print(edge.to.value + " ");
                if (visitor.visit(edge.to.value)) return;
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

    /**
     * 准备一个queue，一个map（顶点，入度信息表），list
     * 1.把indegree=0的顶点放到queue中，不为0的放到map里
     * 2. 将queue顶头出队，放入list中，并且更新map表中的inDegree信息
     * 3.观察map中更新后是否有新的inDegree=0的顶点，如果有，则放入queue中
     * 4.不断重复2，3直到queue为空
     */
    public List<V> topologicalSort(V begin) {
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Map<Vertex<V, E>, Integer> map = new HashMap<>();
        List<V> list = new LinkedList<>();
//        Collection<Vertex<V, E>> values = vertices.values();
//        for (Vertex<V, E> veVertex : values) {
//            int in = veVertex.inDegrees.size();
//            if (in == 0) queue.offer(veVertex);
//            else map.put(veVertex,in);
//        }

        //1.把inDegree=0的顶点放到queue中，不为0的放到map里
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            int in = vertex.inDegrees.size();
            if (in == 0) queue.offer(vertex);
            else map.put(vertex, in);
        });
        while (!queue.isEmpty()) {
            //2.将queue顶头出队，放入list中，并且更新map表中的inDegree信息
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            for (Edge<V, E> edge : vertex.outDegrees) {
                //3.观察map中更新后是否有新的inDegree=0的顶点，如果有，则放入queue中
                int toIn = map.get(edge.to) - 1;
                if (toIn == 0) queue.offer(edge.to);
                map.put(edge.to, toIn);
            }

        }

        return list;

    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return prim();
    }

    private Set<EdgeInfo<V, E>> prim() {
        //边集合 set A
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        //点集合 Set S
        Set<Vertex<V, E>> addedVertices = new HashSet<>();

        Iterator<Vertex<V, E>> iterator = vertices.values().iterator();
        Vertex<V, E> vertex = iterator.next();//A
        addedVertices.add(vertex);
//        //nlogn
//        PriorityQueue<Edge<V,E>> minHeap = new PriorityQueue<>(edgeComparator);
//        for (Edge<V,E> edge : vertex.outDegrees) {
//            minHeap.offer(edge);
//        }

        //O(n)
        MinHeap<Edge<V, E>> minHeap = new MinHeap<>(vertex.outDegrees, edgeComparator);

        while (!minHeap.isEmpty() && addedVertices.size() < vertices.size()) {
            //从堆顶拿到weight最小的边
            Edge<V, E> edge = minHeap.remove();
            if (addedVertices.contains(edge.to)) continue;
            //AB --- set A
            edgeInfos.add(edge.info());
            //B --- Set S
            addedVertices.add(edge.to);
            //将B点的所有outDegrees放入到heap中，继续寻找AB集合中的最小横切边
            minHeap.addAll(edge.to.outDegrees);

        }

        return edgeInfos;
    }

    private Set<EdgeInfo<V, E>> kruskal() {
        int edgeSize = vertices.size() - 1;
        if (edgeSize == -1) return null;

        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        MinHeap<Edge<V, E>> minHeap = new MinHeap<>(edges, edgeComparator);
        UnionFind<Vertex<V, E>> uf = new UnionFind<>();
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            uf.makeSet(vertex);
        });

        while (!minHeap.isEmpty() && edgeInfos.size() < vertices.size() - 1) {
            Edge<V, E> edge = minHeap.remove();
            if (uf.isSame(edge.to, edge.from)) continue;
            edgeInfos.add(edge.info());
            uf.union(edge.from, edge.to);
        }
//        Edge<V, E> edge = minHeap.remove();
//        edgeInfos.add(edge.info());
//        Edge<V, E> edge2 = minHeap.remove();
//        edgeInfos.add(edge2.info());
//        Edge<V, E> edge3 = minHeap.remove();
//        while (如果不成环) {
//            edgeInfos.add(edge3.info());
//        }

        return edgeInfos;
    }


    @Override
    public Map<V, E> shortestPathWithoutPathInfo(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;
        /**
         * Map<V,E> paths = new HashMap<>();
         * paths.put("B",10);
         * paths.put("D",30);
         * paths.put("E",100);
         *
         * 从paths中找到第一个起飞点：找到从A点到B,D,E的最短路径：("B",10)
         *由于B点还在map中，下次选最短路径时，可能会被重复选择
         * 所以一个map不够，需要再做一个map，用来保留已经走过的路
         * Map<V,E> selectedPaths = new HashMap<>();
         * selectedPaths.put("B",10);
         * paths.remove("B");
         * 对B点所有的outEdges做一次松弛操作（更新其他点到源点A的最短路径）
         */
        Map<Vertex<V, E>, E> paths = new HashMap<>();

        Map<V, E> selectedPaths = new HashMap<>();

        //初始化paths：将A到B,D,E的最短路径信息放入map中
        for (Edge<V, E> edge : beginVertex.outDegrees) {
            paths.put(edge.to, edge.weight);
        }
        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, E> minEntry = getMinPathWithoutPathInfo(paths);//("B",10);
            Vertex<V, E> minVertex = minEntry.getKey();
            E minWeight = minEntry.getValue();
            selectedPaths.put(minVertex.value, minWeight);//B点起飞了
            paths.remove(minVertex);
            //对B点所有的outEdges做一次松弛操作（更新其他点到源点A的最短路径）
            for (Edge<V, E> edge : minVertex.outDegrees) {

            /*Relaxation
            分别需要得到更新后的weight和之前的weight，然后大小比较
            //如果newWeight < oldWeight ,则更新paths
            //如果newWeight > oldWeight ,则不更新paths
             */
                //1.先算出newWeight
                E newWeight = weightManager.add(minEntry.getValue(), edge.weight);
                //2.算出oldWeight
                E oldWeight = paths.get(edge.to);//null
                if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0) {
                    paths.put(edge.to, newWeight);
                }

            }

        }

        return selectedPaths;
    }

    private Map.Entry<Vertex<V, E>, E> getMinPathWithoutPathInfo(Map<Vertex<V, E>, E> paths) {
        Iterator<Map.Entry<Vertex<V, E>, E>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, E> minEntry = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, E> nextEntry = iterator.next();
            if (weightManager.compare(nextEntry.getValue(), minEntry.getValue()) < 0) {
                minEntry = nextEntry;
            }
        }
        return minEntry;
    }


    public Map<V, PathInfo<V, E>> shortestPath(V begin) {
        return bellmanFord(begin);
    }

    private Map<V, PathInfo<V, E>> bellmanFord(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        selectedPaths.put(beginVertex.value, new PathInfo<>(weightManager.zero()));
        for (int i = 0; i < vertices.size() - 1; i++) {
            for (Edge<V, E> edge : edges) {

                PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);

                if (fromPath == null) continue;

                relaxationForBellmanFord(edge, selectedPaths, selectedPaths.get(edge.from.value));
            }
        }

        selectedPaths.remove(beginVertex.value);

        return selectedPaths;
    }

    private void relaxationForBellmanFord(Edge<V, E> edge, Map<V, PathInfo<V, E>> paths, PathInfo<V, E> fromPath) {
        //1.先算出newWeight
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        //2.算出oldWeight
        PathInfo<V, E> oldPath = paths.get(edge.to.value);//null
        if (oldPath == null || weightManager.compare(newWeight, oldPath.weight) < 0) {
            PathInfo<V, E> path = new PathInfo<>();
            path.weight = newWeight;
            path.edgeInfos.addAll(fromPath.edgeInfos);
            path.edgeInfos.add(edge.info());
            paths.put(edge.to.value, path);

        }
    }


    private Map<V, PathInfo<V, E>> dijkstra(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;
        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();

        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();

        //初始化paths：将A到B,D,E的最短路径信息放入map中
        for (Edge<V, E> edge : beginVertex.outDegrees) {
            PathInfo<V, E> path = new PathInfo<>();
            path.weight = edge.weight;
            path.edgeInfos.add(edge.info());
            paths.put(edge.to, path);
        }
        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinPath(paths);//("B",10);
            Vertex<V, E> minVertex = minEntry.getKey();
            PathInfo<V, E> minPath = minEntry.getValue();
            selectedPaths.put(minVertex.value, minPath);//B点起飞了
            paths.remove(minVertex);

            //对B点所有的outEdges做一次松弛操作（更新其他点到源点A的最短路径）
            for (Edge<V, E> edge : minVertex.outDegrees) {
                if (selectedPaths.containsKey(edge.to.value)) continue;
                relaxationForDijkstra(edge, paths, minPath);
            }
        }
        return selectedPaths;
    }

    private void relaxationForDijkstra(Edge<V, E> edge, Map<Vertex<V, E>, PathInfo<V, E>> paths, PathInfo<V, E> minPath) {
        //1.先算出newWeight
        E newWeight = weightManager.add(minPath.weight, edge.weight);
        //2.算出oldWeight
        PathInfo<V, E> oldPath = paths.get(edge.to);//null
        if (oldPath == null || weightManager.compare(newWeight, oldPath.weight) < 0) {
            PathInfo<V, E> path = new PathInfo<>();
            path.weight = newWeight;
            path.edgeInfos.addAll(minPath.edgeInfos);
            path.edgeInfos.add(edge.info());
            paths.put(edge.to, path);

        }
    }

    private Map.Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Iterator<Map.Entry<Vertex<V, E>, PathInfo<V, E>>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> nextEntry = iterator.next();
            if (weightManager.compare(nextEntry.getValue().weight, minEntry.getValue().weight) < 0) {
                minEntry = nextEntry;
            }
        }

        return minEntry;
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
