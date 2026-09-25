class CallSoD
{
    public int reverse(int num)
    {
        int rev = 0;

        for(; num > 0; )
        {
            int rem = num % 10;
            rev = rev * 10 + rem;
            num = num / 10;
        }

        return rev;
    }
}

class Palindrome
{
    public static void main(String[] args)
    {
        CallSoD obj = new CallSoD();
        int n = 2345;

        System.out.println("Original = " + n);
        System.out.println("Reverse = " + obj.reverse(n));
    }
}