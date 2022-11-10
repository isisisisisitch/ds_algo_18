package ca.bytetube._10_graph;

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
}
