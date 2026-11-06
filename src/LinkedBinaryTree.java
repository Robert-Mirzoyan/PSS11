public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(
                E element,
                Node<E> parent,
                Node<E> left,
                Node<E> right) {

            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public E getElement() {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }

    private Node<E> root;
    private int size;

    protected Node<E> createNode(
            E element,
            Node<E> parent,
            Node<E> left,
            Node<E> right) {

        return new Node<>(element, parent, left, right);
    }

    protected Node<E> validate(Position<E> p) {
        if (!(p instanceof Node<?>)) {
            throw new IllegalArgumentException(
                    "The position does not belong to this tree.");
        }

        @SuppressWarnings("unchecked")
        Node<E> node = (Node<E>) p;

        if (node.getParent() == node) {
            throw new IllegalArgumentException(
                    "The position is no longer valid.");
        }

        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) {
        return validate(p).getParent();
    }

    @Override
    public Position<E> left(Position<E> p) {
        return validate(p).getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) {
        return validate(p).getRight();
    }

    public Position<E> addRoot(E element) {
        if (!isEmpty()) {
            throw new IllegalStateException(
                    "The tree already has a root.");
        }

        root = createNode(element, null, null, null);
        size = 1;
        return root;
    }

    public Position<E> addLeft(
            Position<E> p,
            E element) {

        Node<E> parent = validate(p);

        if (parent.getLeft() != null) {
            throw new IllegalArgumentException(
                    "The position already has a left child.");
        }

        Node<E> child =
                createNode(element, parent, null, null);

        parent.setLeft(child);
        size++;
        return child;
    }

    public Position<E> addRight(
            Position<E> p,
            E element) {

        Node<E> parent = validate(p);

        if (parent.getRight() != null) {
            throw new IllegalArgumentException(
                    "The position already has a right child.");
        }

        Node<E> child =
                createNode(element, parent, null, null);

        parent.setRight(child);
        size++;
        return child;
    }

    public E set(Position<E> p, E element) {
        Node<E> node = validate(p);
        E oldElement = node.getElement();
        node.setElement(element);
        return oldElement;
    }

    public E remove(Position<E> p) {
        Node<E> node = validate(p);

        if (numChildren(p) == 2) {
            throw new IllegalArgumentException(
                    "The position has two children.");
        }

        Node<E> child =
                node.getLeft() != null
                        ? node.getLeft()
                        : node.getRight();

        if (child != null) {
            child.setParent(node.getParent());
        }

        if (node == root) {
            root = child;
        } else {
            Node<E> parent = node.getParent();

            if (node == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }

        size--;

        E answer = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);

        return answer;
    }
}