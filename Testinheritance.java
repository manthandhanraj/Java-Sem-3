class A
{
    int a = 10;

    public void call()
    {
        System.out.println("Hi from call");
    }
}

class B extends A
{
    int b = 20;
}

class Testinheritance
{
    public static void main(String args[])
    {
        B obj1 = new B();

        System.out.println(obj1.a + obj1.b);
        obj1.call();
    }
}