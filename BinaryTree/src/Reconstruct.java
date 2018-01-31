/*
 * Copyright (c) Simon Knott 2018.
 */

/**
 * Created by simon.knott on 31.01.2018.
 */
public class Reconstruct{
    static <T> BinaryTree<T> preOrderInOrder(List<T> preOrder, List<T> inOrder) {
        if (preOrder.isEmpty()) {
            return new BinaryTree<>();
        }

        preOrder.toFirst();
        final T root = preOrder.getContent();
        preOrder.remove();

        final List<T> inOrderLeft = new List<>();

        // Construct Left Subtree
        inOrder.toFirst();
        while (inOrder.hasAccess() && inOrder.getContent() != root) {
            inOrderLeft.append(inOrder.getContent());
            inOrder.remove();
        }

        // Skip Root
        inOrder.remove();

        final BinaryTree<T> result = new BinaryTree<>(
                root,
                preOrderInOrder(preOrder, inOrderLeft),
                preOrderInOrder(preOrder, inOrder) // inOrder is right subtree
        );

        return result;
    }
}
