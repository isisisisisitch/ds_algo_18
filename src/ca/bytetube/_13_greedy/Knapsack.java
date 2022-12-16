package ca.bytetube._13_greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Knapsack {


    public static void main(String[] args) {
        Article[] articles = new Article[]{
                new Article(35,10),
                new Article(30,40),
                new Article(60,30),
                new Article(50,50),
                new Article(40,35),
                new Article(10,40),
                new Article(25,30)
        };

        int val1 = new Knapsack().select(150, articles, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o2.value - o1.value;
            }
        });
        int val2 = new Knapsack().select(150, articles, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o1.weight - o2.weight;
            }
        });

        int val3 = new Knapsack().select(150, articles, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return Double.compare(o2.getDensity(),o1.getDensity()) ;
            }
        });

        System.out.println(val1);
        System.out.println(val2);
        System.out.println(val3);
        System.out.println();

    }

    public int select(int W, Article[] articles, Comparator<Article> comparator){
        Arrays.sort(articles,comparator);
        int weight = 0;
        int value = 0;
        List<Article> articleList = new LinkedList<>();
        for (int i = 0; i < articles.length; i++) {
            int newWeight = articles[i].weight + weight;
            if (newWeight <= W) {
                weight = newWeight;
                value += articles[i].value;
                articleList.add(articles[i]);
            }
        }

        System.out.println(articleList);

        return value;

    }
}
