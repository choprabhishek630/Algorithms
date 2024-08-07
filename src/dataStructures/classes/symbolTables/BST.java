/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package symbolTables;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;
import util.Pair;
import util.Arrays;
import stack.LLStack;
import stack.Stack;
import queue.LLQueue;
import queue.Queue;

/**
 *
 * @author abhishekchopra
 */
public class BST<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {
    protected BST.Node<Key, Value> root;
    protected final Comparator<Key> cmp;
    
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
    
    public static final class BSTIterator<Key, Value> implements Iterator<Pair<Key, Value>> {
        private final Stack<BST.Node<Key, Value>> nodes;
        private boolean reversed;
        
        public BSTIterator(BST.Node<Key, Value> root) {
            this(root, false);
        }
        
        public BSTIterator(BST.Node<Key, Value> root, boolean reversed) {
            this.nodes = new LLStack();
            this.reversed = reversed;
            this.pushItems(root);
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
        
        private BST.Node<Key, Value> popItems() {
            return this.nodes.pop();
        }
        
        @Override
        public boolean hasNext() {
            return !this.nodes.isEmpty();
        }
        
        @Override
        public Pair<Key, Value> next() {
            BST.Node<Key, Value> x = this.popItems();
            
            if (this.reversed)
                this.pushItems(x.left);
            else
                this.pushItems(x.right);
            return new Pair<>(x.key, x.value);
        }
    }
    
    
    
    public static final class BSTPreOrderIterator<Key, Value> implements Iterator<Pair<Key, Value>> {
        private final Stack<BST.Node<Key, Value>> nodes;
        
        public BSTPreOrderIterator(BST.Node<Key, Value> root) {
            this.nodes = new LLStack();
            this.pushItems(root);
        }
        
        private void pushItems(BST.Node<Key, Value> root) {
            if (root != null)
                this.nodes.push(root);
        }
        
        private BST.Node<Key, Value> popItems() {
            BST.Node<Key, Value> curr = null;
            while (!this.nodes.isEmpty() && curr == null)
                curr = this.nodes.pop().right;
            return curr;
        }
        
        @Override
        public boolean hasNext() {
            return !this.nodes.isEmpty();
        }
        
        @Override
        public Pair<Key, Value> next() {
            BST.Node<Key, Value> curr = this.nodes.top();
            Pair<Key, Value> kv = new Pair<>(curr.key, curr.value);
            
            curr = curr.left;
            if (curr == null) {
                curr = this.popItems();
            }
            this.pushItems(curr);
            
            return kv;
        }
    }
    
    public static final class BSTPostOrderIterator<Key, Value> implements Iterator<Pair<Key, Value>> {
        private final Stack<BST.Node<Key, Value>> nodes;
        
        public BSTPostOrderIterator(BST.Node<Key, Value> root) {
            this.nodes = new LLStack();
            this.pushItems(root);
        }
        
        @Override
        public boolean hasNext() {
            return !this.nodes.isEmpty();
        }
        
        private void pushItems(BST.Node<Key, Value> curr) {
            while (curr != null) {
                this.nodes.push(curr);
                if (curr.left != null)
                    curr = curr.left;
                else
                    curr = curr.right;
            }
        }
        
        private BST.Node<Key, Value> popItems() {
            if (this.nodes.isEmpty())   return null;
            
            BST.Node<Key, Value> child = this.nodes.pop();
            
            if (this.nodes.isEmpty())   return null;
            
            BST.Node<Key, Value> parent = this.nodes.top();
            if (parent.right == child || parent.right == null)
                return null;
            return parent.right;
        }
        
        @Override
        public Pair<Key, Value> next() {
            BST.Node<Key, Value> x = this.nodes.top();
            Pair<Key, Value> kv = new Pair<>(x.key, x.value);
            
            this.pushItems(this.popItems());
            
            return kv;
        }
    }
                    
    
    public static class Node<K, V> {
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
    
    protected int size(BST.Node<Key, Value> x) {
        if (x == null) return 0;
        return x.count;
    }
    
    @Override
    public int size() {
        return this.size(this.root);
    }
    
    protected BST.Node<Key, Value> get(BST.Node<Key, Value> root, Key key) {
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
    
    protected BST.Node<Key, Value> select(BST.Node<Key, Value> root, int rank) {
        if (root == null) return null;
        
        int leftSize = this.size(root.left);
        
        if (rank == this.size(root.left) + 1)
            return root;
        
        else if (rank <= leftSize)
            return this.select(root.left, rank);
        
        return this.select(root.right, rank - leftSize - 1);
    }
    
    protected BST.Node<Key, Value> min(BST.Node<Key, Value> root) {
        if (root == null || root.left == null) return root;
        return this.min(root.left);
    }
    
    protected BST.Node<Key, Value> max(BST.Node<Key, Value> root) {
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
            this.inorder(root.left, queue, lo, hi);
        
        if (cmpLo >= 0 && cmpHi <= 0)
            queue.enqueue(root.key);
        
        if (cmpHi < 0)
            this.inorder(root.right, queue, lo, hi);
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
    
    public static Pair<Boolean, Integer> isBST(Node<Integer, Integer> root, int min, int max) {
        if (root == null) return new Pair<>(true, 0);
        if (root.key <= min || root.key >= max) return new Pair<>(false, root.count);
        Pair<Boolean, Integer> left = isBST(root.left, min, root.key);
        
        if (!left.first) return new Pair<>(false, root.count);
        Pair<Boolean, Integer> right = isBST(root.right, root.key, max);
        int count = left.second + right.second + 1;
        return new Pair<>(count == root.count && left.first && right.first, count);
    }
    
    public static void test(BST<Integer, Integer> bst) {
        OrderedST.test(bst);
        while (!bst.isEmpty()) {
            bst.deleteMin();
        }
        int N = 255;
        Integer[] arrAdd = Arrays.generateRandomArr(N);
        Integer[] arrDelete = Arrays.generateRandomArr(N);
        
        for (int i = 0 ; i < N ; i++) {
            assert bst.size() == i;
            bst.put(arrAdd[i], i);
            assert isBST(bst.root, Integer.MIN_VALUE, Integer.MAX_VALUE).first;
            assert bst.contains(arrAdd[i]);
            assert !bst.isEmpty();
        }
        assert bst.size() == N;
        
        for (int i = 0 ; i < N ; i++) {
            assert bst.size() == N - i;
            assert bst.contains(arrDelete[i]);
            assert !bst.isEmpty();
            bst.delete(arrDelete[i]);
            assert isBST(bst.root, Integer.MIN_VALUE, Integer.MAX_VALUE).first;
            if (i < N - 1)
                assert !bst.contains(arrDelete[i]);
        }
        assert bst.size() == 0;
        
        System.out.println("Valid BST");
    }
    
    public static void main(String[] args) {
        BST.test(new BST<>());
    }
}
