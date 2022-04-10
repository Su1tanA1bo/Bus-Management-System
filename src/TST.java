import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import edu.princeton.cs.algs4.Queue;

public class TST {
    private int n;              // size
    private Node root;   // root of TST

    private static class Node<String> {
        private char c;                        // character
        private Node left, mid, right;  // left, middle, and right subtries
        private String val;                     // value associated with string
    }

    /**
     * Initializes an empty string symbol table.
     */
    public TST() {
        int stopID;
        int stopCode;
        String stopName;
        String line;
        File fileName = new File("stops.txt");
    	Scanner scanner = null;
        try {
       
        	scanner = new Scanner(fileName);
            Scanner nextLineScanner = null;
            scanner.nextLine();

            while(scanner.hasNext()) {
                line = scanner.nextLine();
                nextLineScanner = new Scanner(line);
                nextLineScanner.useDelimiter(",");
                StringBuilder stringBuilder = new StringBuilder();
                stopID = nextLineScanner.nextInt();
                stopCode= nextLineScanner.nextInt();
                stopName= nextLineScanner.next();

                //moving keywords
                
                if (stopName.substring(0, 8).equals("FLAGSTOP")) {
                    stringBuilder.append(stopName.substring(10));
                    stringBuilder.append(" FLAGSTOP");
                    stopName = stringBuilder.toString();
                }
                else if (stopName.substring(0,2).equals("NB")){
                    stringBuilder.append(stopName.substring(3));
                    stringBuilder.append(" NB");
                    stopName = stringBuilder.toString();
                }
                else if (stopName.substring(0,2).equals("EB")){
                    stringBuilder.append(stopName.substring(3));
                    stringBuilder.append(" EB");
                    stopName = stringBuilder.toString();
                }
                else if (stopName.substring(0,2).equals("SB")){
                    stringBuilder.append(stopName.substring(3));
                    stringBuilder.append(" SB");
                    stopName = stringBuilder.toString();
                }
                else if (stopName.substring(0,2).equals("WB")){
                    stringBuilder.append(stopName.substring(3));
                    stringBuilder.append(" WB");
                    stopName = stringBuilder.toString();
                }
          
                StringBuilder info = new StringBuilder();
                info.append("  stop_id: " + stopID + "\n");
                info.append("  stop_code: " + stopCode + "\n");
                info.append("  stopName: " + stopName + "\n");
                info.append("  stop_desc: " + nextLineScanner.next() + "\n");
                info.append("  stop_lat: " + nextLineScanner.next() + "\n");
                info.append("  stop_lon: " + nextLineScanner.next() + "\n");
                info.append("  zone_id: " + nextLineScanner.next() + "\n");
                info.append("  location_type: " + nextLineScanner.next() + "\n");
                String infoString = info.toString();

                put(stopName, infoString);
                nextLineScanner.close();
            }
            scanner.close();
        } catch(Exception e) {
          // System.out.println("error");
        }
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     * {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     * and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public String get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with null argument");
        }
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        Node<String> x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

    // return subtrie corresponding to given key
    private Node<String> get(Node<String> x, String key, int d) {
        if (x == null) return null;
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid, key, d + 1);
        else return x;
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is {@code null}, this effectively deletes the key from the symbol table.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(String key, String val) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with null key");
        }
        if (!contains(key)) n++;
        else if (val == null) n--;       // delete existing key
        root = put(root, key, val, 0);
    }

    private Node<String> put(Node<String> x, String key, String val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<String>();
            x.c = c;
        }
        if (c < x.c) x.left = put(x.left, key, val, d);
        else if (c > x.c) x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1) x.mid = put(x.mid, key, val, d + 1);
        else x.val = val;
        return x;
    }

    /**
     * Returns all of the keys in the set that start with {@code prefix}.
     *
     * @param prefix the prefix
     * @return all of the keys in the set that start with {@code prefix},
     * as an iterable
     * @throws IllegalArgumentException if {@code prefix} is {@code null}
     */
    public Iterable<String> keysWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
        }
        Queue<String> queue = new Queue<String>();
        Node<String> x = get(root, prefix, 0);
        if (x == null) return queue;
        if (x.val != null) queue.enqueue(prefix);
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }

    // all keys in subtrie rooted at x with given prefix
    private void collect(Node<String> x, StringBuilder prefix, Queue<String> queue) {
        if (x == null) return;
        collect(x.left, prefix, queue);
        if (x.val != null) queue.enqueue(prefix.toString() + x.c);
        collect(x.mid, prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

}