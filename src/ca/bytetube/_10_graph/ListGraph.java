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
        //1.?????????from???to ????????????
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

        //?????????????????????????????????????????????????????????
        //???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????weight
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

        //????????????????????????????????????????????????????????????????????????????????????????????????
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

        //????????????????????????????????????????????????????????????
        for (Iterator<Edge<V, E>> iterator = vertex.outDegrees.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            //?????????????????????????????????????????????????????????edge.to???inDegrees??????edge??????
            edge.to.inDegrees.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

        for (Iterator<Edge<V, E>> iterator = vertex.inDegrees.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            //?????????????????????????????????????????????????????????edge.to???inDegrees??????edge??????
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
     * 1.????????????????????????????????????????????????set????????????
     * 2.????????????vertex????????????outDegrees????????????????????????????????????from???to???????????????????????????
     * 3.??????to
     * 4.???to?????????set???
     * 5.break???????????????outDegrees?????????????????????????????????????????????????????????
     */
    @Override
    public void dfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();
        //1.????????????????????????????????????????????????set????????????????????????outDegrees???????????????
        stack.push(beginVertex);
        System.out.print(beginVertex.value + " ");
        visitedVertices.add(beginVertex);
        while (!stack.isEmpty()) {
            //2.????????????vertex????????????outDegrees????????????????????????????????????from???to???????????????????????????
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.outDegrees) {
                if (visitedVertices.contains(edge.to)) continue;
                stack.push(edge.from);
                stack.push(edge.to);
                //3.??????to
                System.out.print(edge.to.value + " ");
                //4.???to?????????set???
                visitedVertices.add(edge.to);
                //5.break???????????????outDegrees?????????????????????????????????????????????????????????
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
        //1.????????????????????????????????????????????????set????????????????????????outDegrees???????????????
        stack.push(beginVertex);
        // System.out.print(beginVertex.value + " ");
        if (visitor.visit(beginVertex.value)) return;
        visitedVertices.add(beginVertex);
        while (!stack.isEmpty()) {
            //2.????????????vertex????????????outDegrees????????????????????????????????????from???to???????????????????????????
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.outDegrees) {
                if (visitedVertices.contains(edge.to)) continue;
                stack.push(edge.from);
                stack.push(edge.to);
                //3.??????to
                //System.out.print(edge.to.value + " ");
                if (visitor.visit(edge.to.value)) return;
                //4.???to?????????set???
                visitedVertices.add(edge.to);
                //5.break???????????????outDegrees?????????????????????????????????????????????????????????
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
     * ????????????queue?????????map?????????????????????????????????list
     * 1.???indegree=0???????????????queue????????????0?????????map???
     * 2. ???queue?????????????????????list??????????????????map?????????inDegree??????
     * 3.??????map???????????????????????????inDegree=0?????????????????????????????????queue???
     * 4.????????????2???3??????queue??????
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

        //1.???inDegree=0???????????????queue????????????0?????????map???
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            int in = vertex.inDegrees.size();
            if (in == 0) queue.offer(vertex);
            else map.put(vertex, in);
        });
        while (!queue.isEmpty()) {
            //2.???queue?????????????????????list??????????????????map?????????inDegree??????
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            for (Edge<V, E> edge : vertex.outDegrees) {
                //3.??????map???????????????????????????inDegree=0?????????????????????????????????queue???
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
         * ???paths???????????????????????????????????????A??????B,D,E??????????????????("B",10)
         *??????B?????????map?????????????????????????????????????????????????????????
         * ????????????map???????????????????????????map?????????????????????????????????
         * Map<V,E> selectedPaths = new HashMap<>();
         * selectedPaths.put("B",10);
         * paths.remove("B");
         * ???B????????????outEdges????????????????????????????????????????????????A??????????????????
         */
        Map<Vertex<V, E>, E> paths = new HashMap<>();

        Map<V, E> selectedPaths = new HashMap<>();

        //?????????paths??????A???B,D,E???????????????????????????map???
        for (Edge<V, E> edge : beginVertex.outDegrees) {
            paths.put(edge.to, edge.weight);
        }
        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, E> minEntry = getMinPathWithoutPathInfo(paths);//("B",10);
            Vertex<V, E> minVertex = minEntry.getKey();
            E minWeight = minEntry.getValue();
            selectedPaths.put(minVertex.value, minWeight);//B????????????
            paths.remove(minVertex);
            //???B????????????outEdges????????????????????????????????????????????????A??????????????????
            for (Edge<V, E> edge : minVertex.outDegrees) {

            /*Relaxation
            ??????????????????????????????weight????????????weight?????????????????????
            //??????newWeight < oldWeight ,?????????paths
            //??????newWeight > oldWeight ,????????????paths
             */
                //1.?????????newWeight
                E newWeight = weightManager.add(minEntry.getValue(), edge.weight);
                //2.??????oldWeight
                E oldWeight = paths.get(edge.to);//null
                if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0) {
                    paths.put(edge.to, newWeight);
                }

            }

        }

        return selectedPaths;
    }

    public Map<V, PathInfo<V, E>> shortestPath(V begin) {
        return bellmanFord(begin);
    }

    @Override
    public Map<V, Map<V, PathInfo<V, E>>> shortestPath() {
        Map<V, Map<V, PathInfo<V, E>>> paths = new HashMap<>();

        //paths ????????? ?????????????????????????????????????????????????????????
        for (Edge<V,E> edge : edges) {
              //??????                                   //??????
            Map<V, PathInfo<V, E>> map = paths.get(edge.from.value);
            if (map == null) {
                map  = new HashMap<>();
                paths.put(edge.from.value,map);//(A,map(null))
            }

            PathInfo<V,E> pathInfo = new PathInfo<>(edge.weight);
            pathInfo.edgeInfos.add(edge.info());
            map.put(edge.to.value,pathInfo);
           // paths.put(edge.from.value,map);

        }

        vertices.forEach((V v2, Vertex<V, E> vertex2) ->{

            vertices.forEach((V v1, Vertex<V, E> vertex1) ->{

                vertices.forEach((V v3, Vertex<V, E> vertex3) ->{
                    //v1--->v2  //v2--->v3  //v1--->v3
                    //v1--->v2
                    PathInfo<V, E> path12 = getPathInfo(v1,v2,paths);

                    if (path12 == null) return;
                    //v2--->v3
                    PathInfo<V, E> path23 = getPathInfo(v2,v3,paths);
                    if (path23 == null) return;
                    E newWeight = weightManager.add(path12.weight, path23.weight);

                    //v1--->v3
                    PathInfo<V, E> path13 = getPathInfo(v1,v3,paths);

                    if (path13 != null && weightManager.compare(newWeight, path13.weight) >= 0) return;
                    if (path13 == null){
                        path13 = new PathInfo<>();
                       paths.get(v1).put(v3,path13);

                    } else path13.edgeInfos.clear();

                    path13.weight = newWeight;
                    //1-2-3 1-3
                    path13.edgeInfos.addAll(path12.edgeInfos);

                    path13.edgeInfos.addAll(path23.edgeInfos);

                });
            });
        });

        return paths;
    }

    private PathInfo<V,E> getPathInfo(V v1, V v2, Map<V, Map<V, PathInfo<V,E>>> paths) {
        Map<V, PathInfo<V, E>> infoMap = paths.get(v1);
        if (infoMap == null) {
            return null;
        }
       return infoMap.get(v2);
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


    private Set<EdgeInfo<V, E>> prim() {
        //????????? set A
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        //????????? Set S
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
            //???????????????weight????????????
            Edge<V, E> edge = minHeap.remove();
            if (addedVertices.contains(edge.to)) continue;
            //AB --- set A
            edgeInfos.add(edge.info());
            //B --- Set S
            addedVertices.add(edge.to);
            //???B????????????outDegrees?????????heap??????????????????AB???????????????????????????
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
//        while (???????????????) {
//            edgeInfos.add(edge3.info());
//        }

        return edgeInfos;
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

        for (Edge<V, E> edge : edges) {

            PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);

            if (fromPath == null) continue;

            if( relaxationForBellmanFord(edge, selectedPaths, selectedPaths.get(edge.from.value))){
                try {
                    throw new Exception("there is a negative cycle! ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        selectedPaths.remove(beginVertex.value);

        return selectedPaths;
    }

    private boolean relaxationForBellmanFord(Edge<V, E> edge, Map<V, PathInfo<V, E>> paths, PathInfo<V, E> fromPath) {
        //1.?????????newWeight
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        //2.??????oldWeight
        PathInfo<V, E> oldPath = paths.get(edge.to.value);//null
        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return false;
        if (oldPath == null){
            oldPath = new PathInfo<>();
            paths.put(edge.to.value, oldPath);

        } else oldPath.edgeInfos.clear();

        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());


        return true;

    }


    private Map<V, PathInfo<V, E>> dijkstra(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;
        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();

        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();

        //?????????paths??????A???B,D,E???????????????????????????map???
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
            selectedPaths.put(minVertex.value, minPath);//B????????????
            paths.remove(minVertex);

            //???B????????????outEdges????????????????????????????????????????????????A??????????????????
            for (Edge<V, E> edge : minVertex.outDegrees) {
                if (selectedPaths.containsKey(edge.to.value)) continue;
                relaxationForDijkstra(edge, paths, minPath);
            }
        }
        return selectedPaths;
    }

    private void relaxationForDijkstra(Edge<V, E> edge, Map<Vertex<V, E>, PathInfo<V, E>> paths, PathInfo<V, E> minPath) {
        //1.?????????newWeight
        E newWeight = weightManager.add(minPath.weight, edge.weight);
        //2.??????oldWeight
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


}
