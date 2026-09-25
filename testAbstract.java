abstract class TestAbs {

    public abstract void display();

    abstract void newMethod();

    public void show() {
        System.out.println("Called");
    }
}

class ExtendsTesAbs extends TestAbs {

    @Override
    public void display() {
        System.out.println("Display method called");
    }

    @Override
    void newMethod() {
        System.out.println("New method called");
    }
}

class TestAbstract {

    public static void main(String[] args) {

       

        ExtendsTesAbs obj = new ExtendsTesAbs();

        obj.display();
        obj.newMethod();
        obj.show();
    }
}