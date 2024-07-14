package org.example;

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + '}';
    }

    public static void main(String[] args) {
        User user = new User("example", 25);
        System.out.println(user);

        user.setName("new name");
        user.setAge(30);
        System.out.println("Updated User: " + user.getName() + ", Age: " + user.getAge());
    }
}