package ca.bytetube._09_hash;

import java.util.HashMap;
import java.util.Objects;

public class Person {
    private int age;
    private float height;
    private String name;

    public Person() {
    }

    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * 重写equals的目的：当发生hash冲突时，用来比较2个keys是否相同
     * 假设有一个index的位置上连接了多个node（bucket）
     * 这时我们新插入一个数据，让它生成自己的hash code，生成的code通过%运算得到bucket array所对应的index
     * 假设新插入数据的key所对应的index和之前的数据的key所对应的index相同，
     * 那么，既然index是相同的，我们则需要拿到这个key和list上的多个node（bukcet）所对应的key进行比较，
     * 如果是同一个key，则发生覆盖
     * 如果不同，则在list上追加
     * <p>
     * 在jdk的HashMap中，为什么不直接用hash code来进行比较进而确定他们是不是同一个对象呢？
     * 不能！
     * 原因：
     * 1.不同类型的数据可能对应相同的hash code ； "Dal"—> 123; 123—> 123
     * 2.同一类型数据也有可能对应相同的hash code ；[54, 17.7f, "jeff"],[45, 18.2f, "pony"]
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return this.age == person.age &&
                Float.compare(person.height, this.height) == 0 &&
                Objects.equals(this.name, person.name);
    }

    @Override
    public int hashCode() {
        int hashCode = Integer.hashCode(this.age);
        hashCode = hashCode * 31 + Float.hashCode(this.height);
        hashCode = hashCode * 31 + (this.name != null ? this.name.hashCode() : 0);
        return hashCode;
    }


    public static void main(String[] args) {
//        Person p1 = new Person(54, 17.7f, "jeff");
//        Person p2 = new Person(45, 18.2f, "pony");
//        Person p3 = new Person(54, 17.7f, "jeff");
//
////        System.out.println(p1 == p3);
//        System.out.println(p1.hashCode());//1639705018 address  -262661225
//        System.out.println(p2.hashCode());//1627674070          -254354787
//        System.out.println(p3.hashCode());//1360875712          -262661225
//
//        HashMap<Person, Integer> map = new HashMap<>();
//        map.put(p1, 999);
//        map.put(p2, 222);
//        map.put(p3, 555);
//        System.out.println(map.size());// 2/3
        // System.out.println(p1 == p3);//false

        //如果只写了hashCode() 那么map.size() = 3
        //如果只写了equals(Object o)  那么map.size() = 2/3
        //如果写了hashCode()，equals(Object o)  那么map.size() = 2
        test();//


    }

    public static void test() {
        Person p1 = new Person(54, 17.7f, "jeff");

        Person p3 = new Person(54, 17.7f, "jeff");

        HashMap<Object, Integer> map = new HashMap<>();
        map.put(p1, 111);
        map.put("test", 222);
        map.put(p3, 333);
        System.out.println(map.size());


    }
}
