/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package symbolTables;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;
import util.Pair;
import stack.LLStack;
import stack.Stack;
import queue.LLQueue;
import queue.Queue;

/**
 *
 * @author abhishekchopra
 */
public class BST<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {
    private BST.Node<Key, Value> root;
    private final Comparator<Key> cmp;
    
    public static final class BSTLevelOrderIterator<Key extends Comparable<Key>, Value> implements Iterator<Key> {
        private final Queue<BST.Node<Key, Value>> nodes;
        
        public BSTLevelOrderIterator(BST.Node<Key, Value> root) {
            this.nodes = new LLQueue<>();
            this.nodes.enqueue(root);
        }
        
        @Override
        public boolean hasNext() {
            return !this.nodes.isEmpty();
        }
        
        @Override
        public Key next() {
            BST.Node<Key, Value> peek = this.nodes.dequeue();
            if (peek.left != null)
                this.nodes.enqueue(peek.left);
            if (peek.right != null)
                this.nodes.enqueue(peek.right);
            return peek.key;
        }
    }
    
    public static final class BSTIterator<Key extends Comparable<Key>, Value> implements Iterator<Pair<Key, Value>> {
        private final Stack<BST.Node<Key, Value>> nodes;
        private BST.Node<Key, Value> curr;
        private boolean reversed;
        
        public BSTIterator(BST.Node<Key, Value> root) {
            this(root, false);
        }
        
        public BSTIterator(BST.Node<Key, Value> root, boolean reversed) {
            this.nodes = new LLStack();
            this.curr = root;
            this.reversed = reversed;
        }
        
        private void pushItems(BST.Node<Key, Value> curr) {
            while (curr != null) {
                this.nodes.push(curr);
                if (reversed)
                    curr = curr.right;
                else
                    curr = curr.left;
            }
        }
        
        @Override
        public boolean hasNext() {
            return this.curr != null || !this.nodes.isEmpty();
        }
        
        @Override
        public Pair<Key, Value> next() {
            this.pushItems(this.curr);
            BST.Node<Key, Value> x = this.nodes.pop();
            
            if (this.reversed)
                this.curr = x.left;
            else
                this.curr = x.right;
            return new Pair<>(x.key, x.value);
        }
    }
    
    public static final class Node<K, V> {
        public final K key;
        public V value;
        public Node<K, V> left, right;
        public int count;
        
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            
            this.left = this.right = null;
            this.count = 1;
        }
    }
    
    public BST() {
        this(Comparator.naturalOrder());
    }
    
    public BST(Comparator<Key> cmp) {
        this.cmp = cmp;
        this.root = null;
    }
    
    private int size(BST.Node<Key, Value> x) {
        if (x == null) return 0;
        return x.count;
    }
    
    @Override
    public int size() {
        return this.size(this.root);
    }
    
    private BST.Node<Key, Value> get(BST.Node<Key, Value> root, Key key) {
        if (root == null) return null;
        
        int c = this.cmp.compare(key, root.key);
        
        if (c < 0) return this.get(root.left, key);
        
        else if (c > 0) return this.get(root.right, key);
        
        return root;
    }
    
    private BST.Node<Key, Value> put(BST.Node<Key, Value> root, Key key, Value value) {
        if (root == null) return new Node<>(key, value);
        
        int c = this.cmp.compare(key, root.key);
        
        if (c < 0)
            root.left = this.put(root.left, key, value);
        
        else if (c > 0)
            root.right = this.put(root.right, key, value);
        
        else
            root.value = value;
        
        root.count = 1 + this.size(root.left) + this.size(root.right);
        
        return root;
    }
    
    private int rank(BST.Node<Key, Value> root, Key key) {
        if (root == null) return -1;
        
        int c = this.cmp.compare(key, root.key);
        
        if (c == 0) return 1 + this.size(root.left);
        
        if (c < 0) return this.rank(root.left, key);
        
        int r = this.rank(root.right, key);
        
        if (r < 0)
            return r - this.size(root.left) - 1;
        
        return 1 + r + this.size(root.left);
    }
    
    private BST.Node<Key, Value> select(BST.Node<Key, Value> root, int rank) {
        if (root == null) return null;
        
        int leftSize = this.size(root.left);
        
        if (rank == this.size(root.left) + 1)
            return root;
        
        else if (rank <= leftSize)
            return this.select(root.left, rank);
        
        return this.select(root.right, rank - leftSize - 1);
    }
    
    private BST.Node<Key, Value> min(BST.Node<Key, Value> root) {
        if (root == null || root.left == null) return root;
        return this.min(root.left);
    }
    
    private BST.Node<Key, Value> max(BST.Node<Key, Value> root) {
        if (root == null || root.right == null) return root;
        return this.max(root.right);
    }
    
    private BST.Node<Key, Value> deleteMin(BST.Node<Key, Value> root) {
        if (root == null) return null;
        
        if (root.left == null)
            return root.right;
        
        root.left = this.deleteMin(root.left);
        
        root.count = 1 + this.size(root.left) + this.size(root.right);
        
        return root;
    }
    
    private BST.Node<Key, Value> deleteMax(BST.Node<Key, Value> root) {
        if (root == null) return null;
        
        if (root.right == null)
            return root.left;
        
        root.right = this.deleteMax(root.right);
        
        root.count = 1 + this.size(root.left) + this.size(root.right);
        
        return root;
    }
    
    private BST.Node<Key, Value> delete(BST.Node<Key, Value> root, Key key) {
        if (root == null) return null;
        
        int c = this.cmp.compare(key, root.key);
        
        if (c < 0) root.left = this.delete(root.left, key);
        
        else if (c > 0) root.right = this.delete(root.right, key);
        
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            BST.Node<Key, Value> t = root;
            root = this.min(t.right);
            root.right = this.deleteMin(t.right);
            root.left = t.left;
        }
        
        root.count = 1 + this.size(root.left) + this.size(root.right);
        
        return root;
    }
    
    private void inorder(BST.Node<Key, Value> root, Queue<Key> queue) {
        if (root == null) return;
        
        this.inorder(root.left, queue);
        queue.enqueue(root.key);
        this.inorder(root.right, queue);
    }
    
    private void inorder(BST.Node<Key, Value> root, Queue<Key> queue, Key lo, Key hi) {
        if (root == null) return;
        
        int cmpLo = this.cmp.compare(root.key, lo);
        int cmpHi = this.cmp.compare(root.key, hi);
        
        if (cmpLo > 0)
            this.inorder(root.left, queue);
        
        if (cmpLo >= 0 && cmpHi <= 0)
            queue.enqueue(root.key);
        
        if (cmpHi < 0)
            this.inorder(root.right, queue);
    }
    
    @Override
    public Value get(Key key) {
        if (this.isEmpty())
            throw new EmptyStackException();
        
        if (key == null)
            throw new NullPointerException("Key cannot be null (" + key + ")");
        
        BST.Node<Key, Value> x = this.get(this.root, key);
        if (x == null) return null;
        return x.value;
    }
    
    @Override
    public Key min() {
        if (this.isEmpty())
            throw new EmptyStackException();
        BST.Node<Key, Value> x = this.min(this.root);
        if (x == null) return null;
        return x.key;
    }
    
    @Override
    public Key max() {
        if (this.isEmpty())
            throw new EmptyStackException();
        BST.Node<Key, Value> x = this.max(this.root);
        if (x == null) return null;
        return x.key;
    }
    
    @Override
    public void put(Key key, Value value) {
        if (key == null || value == null)
            throw new NullPointerException("Key and Value cannot be null (" + key + ", " + value + ")");
        
        this.root = this.put(this.root, key, value);
    }
    
    @Override
    public int rank(Key key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null (" + key + ")");
        
        return this.rank(this.root, key);
    }
    
    @Override
    public Key select(int rank) {
        if (!this.isValidIndex(rank - 1))
            throw new IndexOutOfBoundsException("Rank " + rank + " is out of bounds!");
        
        BST.Node<Key, Value> x = this.select(this.root, rank);
        if (x == null) return null;
        return x.key;
    }
    
    @Override
    public Value deleteMin() {
        if (this.isEmpty())
            throw new EmptyStackException();
        
        BST.Node<Key, Value> min = this.min(this.root);
        
        if (min == null) return null;
        
        this.root = this.deleteMin(this.root);
        
        return min.value;
    }
    
    @Override
    public Value deleteMax() {
        if (this.isEmpty())
            throw new EmptyStackException();
        
        BST.Node<Key, Value> max = this.max(this.root);
        
        if (max == null) return null;
        
        this.root = this.deleteMax(this.root);
        
        return max.value;
    }
    
    @Override
    public Value delete(Key key) {
        if (this.isEmpty())
            throw new EmptyStackException();
        
        if (key == null)
            throw new NullPointerException("Key cannot be null (" + key + ")");
        
        BST.Node<Key, Value> x = this.get(this.root, key);
        if (x == null) return null;
        
        this.root = this.delete(this.root, key);
        
        return x.value;
    }
    
    @Override
    public Value delete(int rank) {
        if (this.isEmpty())
            throw new EmptyStackException();
        
        if (rank < 0) return null;
        
        if (!this.isValidIndex(rank - 1))
            throw new IndexOutOfBoundsException("Rank " + rank + " is out of bounds!");
        
        BST.Node<Key, Value> x = this.select(this.root, rank);
        if (x == null) return null;
        
        this.root = this.delete(this.root, x.key);
        
        return x.value;
    }
    
    @Override
    public Iterator<Pair<Key, Value>> iterator() {
        return new BSTIterator(this.root);
    }
    
    @Override
    public Iterable<Key> keys() {
        System.out.println("Inorder traversal");
        Queue<Key> queue = new LLQueue<>();
        this.inorder(this.root, queue);
        return queue;
    }
    
    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        System.out.println("Inorder traversal range");
        Queue<Key> queue = new LLQueue<>();
        
        assert this.cmp.compare(lo, hi) <= 0;
        this.inorder(this.root, queue, lo, hi);
        return queue;
    }
    
    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();
        OrderedST.test(bst);
    }
}
