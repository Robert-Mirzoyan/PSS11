import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractTree<E> implements Tree<E> {

    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private class ElementIterator implements Iterator<E> {
        private final Iterator<Position<E>> positionIterator =
                positions().iterator();

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement();
        }

        @Override
        public void remove() {
            positionIterator.remove();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();

        if (!isEmpty()) {
            preorderSubtree(root(), snapshot);
        }

        return snapshot;
    }

    private void preorderSubtree(
            Position<E> p,
            List<Position<E>> snapshot) {

        snapshot.add(p);

        for (Position<E> child : children(p)) {
            preorderSubtree(child, snapshot);
        }
    }

    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();

        if (!isEmpty()) {
            postorderSubtree(root(), snapshot);
        }

        return snapshot;
    }

    private void postorderSubtree(
            Position<E> p,
            List<Position<E>> snapshot) {

        for (Position<E> child : children(p)) {
            postorderSubtree(child, snapshot);
        }

        snapshot.add(p);
    }

    @Override
    public Iterable<Position<E>> positions() {
        return preorder();
    }
}