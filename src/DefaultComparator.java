import java.util.Comparator;

public class DefaultComparator<E>
        implements Comparator<E> {

    @Override
    @SuppressWarnings("unchecked")
    public int compare(E first, E second)
            throws ClassCastException {

        return ((Comparable<E>) first).compareTo(second);
    }
}