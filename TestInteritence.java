class ParentClass {
    int a = 10;
}

class ChildClass extends ParentClass {
}

public class TestInteritence {
    public static void main(String[] args) {
        ChildClass childClass = new ChildClass();
        System.out.println(childClass.a);
    }
}