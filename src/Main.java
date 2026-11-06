import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        testProblem1();
        testProblem2();
        testProblem3();
        testProblem4();
        testProblem5();
        testHeapProblem();

        System.out.println("All tests passed.");
    }

    private static void testProblem1() {
        PriorityQueueStack<String> stack = new PriorityQueueStack<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        check("C".equals(stack.peek()), "Problem 1: top should return C.");

        check("C".equals(stack.pop()), "Problem 1: pop should return C.");

        stack.push("D");

        check("D".equals(stack.peek()), "Problem 1: top should return D.");

        check(stack.size() == 3, "Problem 1: size should be 3.");

        System.out.println("Problem 1 tests passed.");
    }

    private static void testProblem2() {
        Coordinate origin = new Coordinate(0, 0);

        List<Coordinate> points = Arrays.asList(new Coordinate(3, 4), new Coordinate(1, 1), new Coordinate(-2, 0), new Coordinate(0, 5));

        List<Coordinate> result = Problem2Solution.orderByDistance(origin, points);

        long previousDistance = -1;

        for (Coordinate point : result) {
            long dx = (long) point.getX() - origin.getX();

            long dy = (long) point.getY() - origin.getY();

            long currentDistance = dx * dx + dy * dy;

            check(currentDistance >= previousDistance, "Problem 2: distances are not ordered.");

            previousDistance = currentDistance;
        }

        System.out.println("Problem 2 result: " + result);

        System.out.println("Problem 2 tests passed.");
    }

    private static void testProblem3() {
        LinkedPositionalList<Integer> list = new LinkedPositionalList<>();

        Position<Integer> first4 = list.addLast(4);

        Position<Integer> first8 = list.addLast(8);

        list.addLast(3);
        list.addLast(8);

        Position<Integer> second4 = list.addLast(4);

        Position<Integer> final7 = list.addLast(7);

        check(Problem3Solution.isPalindrome(list, first4, second4), "Problem 3: [4,8,3,8,4] " + "should be a palindrome.");

        check(!Problem3Solution.isPalindrome(list, first8, final7), "Problem 3: [8,3,8,4,7] " + "should not be a palindrome.");

        System.out.println("Problem 3 tests passed.");
    }

    private static void testProblem4() {
        LinkedBinaryTree<String> first = new LinkedBinaryTree<>();

        Position<String> firstRoot = first.addRoot("A");

        Position<String> firstLeft = first.addLeft(firstRoot, "B");

        first.addRight(firstRoot, "C");
        first.addLeft(firstLeft, "D");

        LinkedBinaryTree<Integer> second = new LinkedBinaryTree<>();

        Position<Integer> secondRoot = second.addRoot(10);

        Position<Integer> secondLeft = second.addLeft(secondRoot, 20);

        second.addRight(secondRoot, 30);
        second.addLeft(secondLeft, 40);

        check(Problem4Solution.isIsomorphic(first, second), "Problem 4: trees should be isomorphic.");

        second.addRight(secondLeft, 50);

        check(!Problem4Solution.isIsomorphic(first, second), "Problem 4: trees should not be isomorphic.");

        System.out.println("Problem 4 tests passed.");
    }

    private static void testProblem5() {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();

        Position<String> a = tree.addRoot("A");
        Position<String> b = tree.addLeft(a, "B");
        Position<String> c = tree.addRight(a, "C");
        Position<String> d = tree.addLeft(b, "D");
        Position<String> e = tree.addRight(b, "E");
        Position<String> f = tree.addRight(c, "F");

        check(Problem5Solution.preorderNext(tree, a) == b, "Problem 5: A should be followed by B.");

        check(Problem5Solution.preorderNext(tree, b) == d, "Problem 5: B should be followed by D.");

        check(Problem5Solution.preorderNext(tree, d) == e, "Problem 5: D should be followed by E.");

        check(Problem5Solution.preorderNext(tree, e) == c, "Problem 5: E should be followed by C.");

        check(
                Problem5Solution.preorderNext(tree, c) == f, "Problem 5: C should be followed by F.");

        check(Problem5Solution.preorderNext(tree, f) == null, "Problem 5: F should be last.");

        System.out.println("Problem 5 tests passed.");
    }

    private static void testHeapProblem() {
        check(HeapSolution.isMinHeap(new int[]{2, 5, 6, 9, 7, 8, 10}), "The first array should be a min-heap.");

        check(!HeapSolution.isMinHeap(new int[]{2, 5, 6, 9, 1, 8, 10}), "The second array should not be a min-heap.");

        check(HeapSolution.isMinHeap(new int[]{}), "An empty array should be a min-heap.");

        check(HeapSolution.isMinHeap(new int[]{4}), "A one-element array should be a min-heap.");

        System.out.println("Heap problem tests passed.");
    }

    private static void check(
            boolean condition,
            String message) {

        if (!condition) {
            throw new AssertionError(message);
        }
    }
}