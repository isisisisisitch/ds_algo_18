package ca.bytetube._10_graph;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Graph<V, E> {

    protected WeightManager<E> weightManager;

    public Graph() { }

    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    public abstract int verticesSize();

    public abstract int edgesSize();

    public abstract void addVertex(V v);

    public abstract void removeVertex(V v);

    public abstract void addEdge(V from, V to);

    public abstract void addEdge(V from, V to, E weight);

    public abstract void removeEdge(V from, V to);

    public abstract void bfs(V begin);

    public abstract void dfs(V begin);

    public abstract void bfs(V begin, VertexVisitor<V> visitor);

    public abstract void dfs(V begin, VertexVisitor<V> visitor);

    public abstract Set<EdgeInfo<V, E>> mst();

    public abstract Map<V,E> shortestPathWithoutPathInfo(V begin);

    public abstract Map<V,PathInfo<V,E>> shortestPath(V begin);

    public abstract Map<V, Map<V,PathInfo<V,E>> >shortestPath();

    public static class PathInfo<V,E>{
        protected E weight;
        protected List<EdgeInfo<V, E>> edgeInfos = new LinkedList<>();

        public PathInfo() {
        }

        public PathInfo(E weight) {
            this.weight = weight;
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }

        public List<EdgeInfo<V, E>> getEdgeInfos() {
            return edgeInfos;
        }

        public void setEdgeInfos(List<EdgeInfo<V, E>> edgeInfos) {
            this.edgeInfos = edgeInfos;
        }

        @Override
        public String toString() {
            return "PathInfo{" +
                    "weight=" + weight +
                    ", edgeInfos=" + edgeInfos + '}';
        }
    }

    public static class EdgeInfo<V, E> {
        E weight;
        V from;
        V to;

        public EdgeInfo(E weight, V from, V to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }


        @Override
        public String toString() {
            return "from=" + from +
                    ", to=" + to + ", weight=" + weight + "\n";
        }
    }


    public static abstract class VertexVisitor<V> {

        public abstract boolean visit(V val);
    }

    //对于大型项目，我们可能对于某一个属性不仅仅有一种操作方式，
    // 还会有很多其他的操作，所以我们可以把具体的操作方式交给外界用户去确定
    public interface WeightManager<E> {
        public int compare(E w1, E w2);

        public E add(E w1, E w2);

        public E zero();
        //...

    }
}
