import java.io.Serializable;

/**
 * Created by simon.knott on 17.11.2017.
 */
public class Main {
    public static void main(String... args) {
        Stack<Integer> stack = new Stack<>();

        Stack<Serializable> serializableStack = new Stack<>();

        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }

        stack.insert(5, 10);
        stack.set(6, 102);

        while(!stack.isEmpty()) {
            System.out.println(stack.top());
            stack.pop();
        }
    }
}
