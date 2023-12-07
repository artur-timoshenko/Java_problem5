import java.util.concurrent.ThreadLocalRandom;


public class SkipList {
    private static final int MAX_LEVEL = 6;
    private Node head;
    private int level;

    public SkipList() {
        this.head = new Node(Integer.MIN_VALUE, MAX_LEVEL);
        this.level = 0;
    }


    private int randomLevel() {
        int level = 0;
        while (ThreadLocalRandom.current().nextInt(2) == 0 && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    // Поиск узла по значению
    public boolean contains(int value) {
        Node current = head;
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value < value) {
                current = current.next[i];
            }
        }
        current = current.next[0];
        return current != null && current.value == value;
    }


    public void insert(int value) {
        Node current = head;
        Node[] update = new Node[MAX_LEVEL + 1];
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value < value) {
                current = current.next[i];
            }
            update[i] = current;
        }
        current = current.next[0];
        if (current == null || current.value != value) {
            int newLevel = randomLevel();
            if (newLevel > level) {
                for (int i = level + 1; i <= newLevel; i++) {
                    update[i] = head;
                }
                level = newLevel;
            }
            Node newNode = new Node(value, newLevel);
            for (int i = 0; i <= newLevel; i++) {
                newNode.next[i] = update[i].next[i];
                update[i].next[i] = newNode;
            }
        }
    }


    public void delete(int value) {
        Node current = head;
        Node[] update = new Node[MAX_LEVEL + 1];
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value < value) {
                current = current.next[i];
            }
            update[i] = current;
        }
        current = current.next[0];
        if (current != null && current.value == value) {
            for (int i = 0; i <= level; i++) {
                if (update[i].next[i] != current) {
                    break;
                }
                update[i].next[i] = current.next[i];
            }
            while (level > 0 && head.next[level] == null) {
                level--;
            }
        }
    }


    public void display() {
        System.out.println("SkipList contents:");
        Node current = head.next[0];
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next[0];
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(3);
        skipList.insert(6);
        skipList.insert(2);
        skipList.insert(7);
        skipList.insert(5);

        skipList.display();

        System.out.println("Contains 6: " + skipList.contains(6));
        System.out.println("Contains 4: " + skipList.contains(4));

        skipList.delete(6);
        skipList.display();
    }
}
