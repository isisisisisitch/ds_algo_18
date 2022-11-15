package ca.bytetube._10_graph;


import java.util.List;

public class Main {
    public static void main(String[] args) {
//      test2();
//      testBFS_02();
//        testDFS_01();
        // testDFS_02();
       // testBFS_withVisitor();
        testTopo();

    }

    public static void testTopo() {
        Graph<Object, Double> directGraph = directGraph(Data.TOPO);
        List<Object> res = ((ListGraph) directGraph).topologicalSort(3);
        System.out.println(res);
    }



    public static void testBFS_withVisitor() {
        Graph<Object, Double> directGraph = directGraph(Data.BFS_02);
        directGraph.bfs(0, new Graph.VertexVisitor<Object>() {
            @Override
            public boolean visit(Object val) {
                System.out.print(val + " ");
                return val.equals(6);
            }
        });
    }

    public static void testDFS_02() {
        Graph<Object, Double> directGraph = directGraph(Data.DFS_02);
        directGraph.dfs("a");
    }


    public static void testDFS_01() {
        Graph<Object, Double> unDirectGraph = unDirectGraph(Data.DFS_01);
        unDirectGraph.dfs(1);
    }


    public static void testBFS_02() {
        Graph<Object, Double> directGraph = directGraph(Data.BFS_02);
        directGraph.bfs(0);
    }


    public static void testBFS_01() {
        Graph<Object, Double> unDirectGraph = unDirectGraph(Data.BFS_01);
        unDirectGraph.bfs("A");
    }

    public static void test2() {
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("v1", "v0", 9);
        graph.addEdge("v1", "v2", 3);
        graph.addEdge("v0", "v4", 6);
        graph.addEdge("v2", "v0", 2);
        graph.addEdge("v2", "v3", 5);
        graph.addEdge("v3", "v4", 1);
        graph.removeVertex("v0");
        graph.printGraph();

    }

    public static void test1() {
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("v1", "v0", 9);
        graph.addEdge("v1", "v2", 3);
        graph.addEdge("v0", "v4", 6);
        graph.addEdge("v2", "v0", 2);
        graph.addEdge("v2", "v3", 5);
        graph.addEdge("v3", "v4", 1);

        graph.printGraph();

    }


    public static Graph<Object, Double> directGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }

        return graph;

    }


    public static Graph<Object, Double> unDirectGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }

        return graph;
    }


}
