package ca.bytetube._10_graph;



import java.util.Set;

public interface Graph<V, E> {


    public int verticesSize();

    int edgesSize();

    public void addVertex(V v);

    public void removeVertex(V v);

    public void addEdge(V from, V to);

    public void addEdge(V from, V to, E weight);

    public void removeEdge(V from, V to);

    public void bfs(V begin);

    public void dfs(V begin);

    public void bfs(V begin, VertexVisitor<V> visitor);

    public void dfs(V begin, VertexVisitor<V> visitor);

    public Set<ListGraph.Edge<V, E>> mst();

    public static abstract class VertexVisitor<V> {

        public abstract boolean visit(V val);
    }
}
