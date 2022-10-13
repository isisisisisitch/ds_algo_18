package ca.bytetube._06_binarytree;

import java.util.Comparator;

public class HeightComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return Double.compare(o2.height,o1.height);
    }
}
