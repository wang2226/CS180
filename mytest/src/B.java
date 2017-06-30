/**
 * Created by Bruce on 22/11/2016.
 */
public class B extends A{
    protected int a;

    public void setA(int value) {
        a = value;
    }

    public static void main(String[] args){
        A objectA = new A();
        B objectB = new B();
        objectA.setA ( 5 );
        objectB.setA ( 10 );
        System.out.println ("A = " + objectA.getA () );
        System.out.println ("B = " + objectB.getA () );
    }
}
