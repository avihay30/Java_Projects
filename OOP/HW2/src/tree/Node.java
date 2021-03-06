package tree;

/**
 * Node represents a Node in a tree of strings (lower-case english alphabet),
 * that each node has 26 children Nodes, and count that holds how many times
 * the appropriate string has been added.
 */
public class Node {
    private int count;
    // children length is 26 stands for lower-case letters in english alphabet.
    private Node[] children = new Node['z' - 'a' + 1];

    public int num(String s) {
        if (s.isEmpty()) return count;

        int index = getIndex(s.charAt(0));
        if (children[index] == null) return 0;

        return children[index].num(s.substring(1));
    }

    public void add(String s) {
        if (s.isEmpty()) {
            count++;
            return;
        }
        int index = getIndex(s.charAt(0));
        // if node isn't exist, init new node.
        if (children[index] == null) children[index] = new Node();

        children[index].add(s.substring(1));
    }

    /**
     * @param c - lower-case letter in english alphabet
     * @return corresponding index in children array if exists, else -1
     */
    private int getIndex(char c) {
        if (c > 'z' || c < 'a') return -1;

        return c - 'a';
    }
}
