import java.io.Serializable;

/**
 * Created by simon.knott on 17.11.2017.
 */
public class Main {
    public static void main(String... args) {
        Queue<Integer> stack = new Queue<>();

        for (int i = 0; i < 20; i++) {
            stack.enqueue(i);
        }

        while(!stack.isEmpty()) {
            System.out.println(stack.front());
            stack.dequeue();
        }
    }
}
