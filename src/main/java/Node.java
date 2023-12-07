public class Node {
    int value;
    Node[] next;

    public Node(int value, int level) {
        this.value = value;
        this.next = new Node[level + 1];
    }
}
