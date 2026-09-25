class testcondition
{
    public String outputResult(int num)
    {
        if(num % 2 == 0)
        {
            return "Even";
        }
        else
        {
            return "odd";
        }
    }
}

public class exr4 {
    public static void main(String[] args)
    {
        System.out.println("From main class");
        testcondition condition = new testcondition();
        System.out.println(condition.outputResult(13));
    }
}