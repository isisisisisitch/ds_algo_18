package ca.bytetube._13_greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {

    public static void main(String[] args) {
        int[] costs = {100,20,40,30,50};
        int[] profits = {20,4,5,20,15};
        int ipo = IPO(costs, profits, 50, 3);
        System.out.println(ipo);

    }

    private static class Project {
        public int cost;
        public int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Project{" +
                    "cost=" + cost +
                    ", profit=" + profit +
                    '}';
        }
    }


    public static int IPO(int[] costs, int[] profits, int W, int k) {
        if (costs == null || profits== null || costs.length != profits.length) throw new RuntimeException("data error");
        Project[] projects = new Project[costs.length];
        for (int i = 0; i < projects.length; i++) {
            projects[i] = new Project(costs[i], profits[i]);
        }

        PriorityQueue<Project> minCostQ = new PriorityQueue<>(new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o1.cost - o2.cost;
            }
        });

        for (int i = 0; i < projects.length; i++) minCostQ.offer(projects[i]);
        System.out.println("minCostQ:" + minCostQ + "minCostQ size:" + minCostQ.size());

        PriorityQueue<Project> maxProfitQ = new PriorityQueue<>(new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o2.profit - o1.profit;
            }
        });

        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().cost <= W) {
                maxProfitQ.offer(minCostQ.poll());
            }
            System.out.println("maxProfitQ:" + maxProfitQ + "maxProfitQ size:" + maxProfitQ.size());
            Project project = maxProfitQ.poll();
            System.out.println(project);
            W += project.profit;

        }

        return W;
    }

}
