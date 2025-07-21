import java.util.Arrays;
import java.util.Comparator;

public class LambdaExpression {

    public static void main(String args[])
    {
        Integer a[] = {15,18,1,26,45,9};

        /* Without Lambda Expressions */
        Arrays.sort(a, new Comparator<Integer>() {
            public int compare(Integer a,Integer b){
                return b-a;
            }
        });
        print(a);
        /* With Lamda Expressions */
        Arrays.sort(a,(x,y)->{return y-x;});
        print(a);
    }

    public static void print(Integer a[]){
       for(Integer x:a){
        System.out.print(x+" ");
       }
       System.out.println();
    }
}
