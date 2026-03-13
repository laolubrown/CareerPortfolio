package securecomputing;

public class StaticScope{
    static int x = 10; 

    public static void outer() {
        class Inner {
            public void inner() {
                System.out.println("Inner function accessing x: " + x);
                x = 20;  // Modify static x
            }
        }
        Inner innerClass = new Inner();
        innerClass.inner();
    }

    public static void main(String[] args) {
        System.out.println("Global x before calling functions: " + x);
        outer();
        System.out.println("Global x after calling functions: " + x);
    }
}
