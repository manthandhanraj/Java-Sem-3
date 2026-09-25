class A
{
    void add()
    {
        int a = 10;
        int b = 20;
        System.out.println("Addition: " + (a + b));
    }

    void add(int a)
    {
        int b = 30;
        System.out.println("Addition: " + (a + b));
    }

    void show()
    {
        System.out.println("Parent class A");
    }
}

class B extends A
{
    @Override
    void show()
    {
        System.out.println("Child class B");
    }
}

public class overloadandwrite
{
    public static void main(String args[])
    {
        B obj = new B();

        obj.add();
        obj.add(30);

        obj.show();
    }
}