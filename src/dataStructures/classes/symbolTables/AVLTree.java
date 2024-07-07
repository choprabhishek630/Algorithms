/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package symbolTables;

import java.util.EmptyStackException;
import util.Pair;
import util.Arrays;

// for revision
// https://www.youtube.com/watch?v=TbvhGcf6UJU&t=2033s

/**
 *
 * @author abhishekchopra
 */
public class AVLTree<Key extends Comparable<Key>, Value> extends BST<Key, Value> {
    public static class AVLNode<K, V> extends BST.Node<K, V> {
        public int height;
        
        public AVLNode(K key, V value) {
            super(key, value);
            this.height = 1;
        }
    }
    
    private int height(AVLNode<Key, Value> node) {
        if (node == null) return 0;
        return node.height;
    }
    
    private boolean isBalancedNode(AVLNode<Key, Value> node) {
        if (node == null) return true;
        return Math.abs(
            this.height((AVLNode<Key, Value>) (node.left)) -
            this.height((AVLNode<Key, Value>) (node.right))
        ) <= 1;
    }
    
    
    /*
                 b node
               /  \
          (< b)    c x
                  / \
        (> b & < c)  (> c)
    
        After rotating left
                c
              /  \
             b    (> c)
            / \
       (< b)   (> b & < c)
             
    */
    private AVLNode<Key, Value> rotateLeft(AVLNode<Key, Value> node) {
        if (node == null) return null;
        
        AVLNode<Key, Value> x = (AVLNode<Key, Value>) (node.right);
        node.right = x.left;
        x.left = node;
        
        return x;
    }
    
    
    /*
           node b
              // \
           x a    (> b)
            / \
        (< a)  (> a & < b)
    
        After rotating right
                a
               / \\
          (< a)    b
                  / \
       (> a & < b)   (> b)
    */
    private AVLNode<Key, Value> rotateRight(AVLNode<Key, Value> node) {
        if (node == null) return null;
        
        AVLNode<Key, Value> x = (AVLNode<Key, Value>) (node.left);
        node.left = x.right;
        x.right = node;
        
        return x;
    }
    
    private void updateSize(AVLNode<Key, Value> root) {
        if (root == null) return;
        
        root.count = 1 + super.size(root.left) + super.size(root.right);
    }
    
    private void updateHeight(AVLNode<Key, Value> root) {
        if (root == null) return;
        
        root.height = Math.max(
                this.height((AVLNode<Key, Value>) (root.left)),
                this.height((AVLNode<Key, Value>) (root.right))
        ) + 1;
    }
    
    private void updateNode(AVLNode<Key, Value> root) {
        this.updateSize(root);
        this.updateHeight(root);
    }
    
    private Pair<AVLNode<Key, Value>, AVLNode<Key, Value>> put(AVLNode<Key, Value> root, Key key, Value value) {
        if (root == null) return new Pair<>(new AVLNode<>(key, value), null);
        
        int c = super.cmp.compare(key, root.key);
        Pair<AVLNode<Key, Value>, AVLNode<Key, Value>> smaller;
        if (c < 0) {
            smaller = this.put((AVLNode<Key, Value>) (root.left), key, value);
            root.left = smaller.first;
        }
        else if (c > 0) {
            smaller = this.put((AVLNode<Key, Value>) (root.right), key, value);
            root.right = smaller.first;
        } else {
            root.value = value;
            return new Pair<>(root, null);
        }
        
        if (!this.isBalancedNode(root)) {
            if (smaller.first == root.left && smaller.second == root.left.left) {
                root = this.rotateRight(root);
                this.updateNode((AVLNode<Key, Value>) (root.right));
            } else if (smaller.first == root.right && smaller.second == root.right.right) {
                root = this.rotateLeft(root);
                this.updateNode((AVLNode<Key, Value>) (root.left));
            } else if (smaller.first == root.left && smaller.second == root.left.right) {
                root.left = this.rotateLeft((AVLNode<Key, Value>) (root.left));
                root = this.rotateRight(root);
                this.updateNode((AVLNode<Key, Value>) (root.left));
                this.updateNode((AVLNode<Key, Value>) (root.right));
            } else {
                root.right = this.rotateRight((AVLNode<Key, Value>) (root.right));
                root = this.rotateLeft(root);
                this.updateNode((AVLNode<Key, Value>) (root.left));
                this.updateNode((AVLNode<Key, Value>) (root.right));
            }
        }
        
        this.updateNode(root);
        return new Pair<>(root, smaller.first);
    }
    
    private AVLNode<Key, Value> delete(AVLNode<Key, Value> root, Key key) {
        if (root == null) return null;
        
        int c = super.cmp.compare(key, root.key);
        if (c < 0) root.left = this.delete((AVLNode<Key, Value>) (root.left), key);
        else if (c > 0) root.right = this.delete((AVLNode<Key, Value>) (root.right), key);
        else {
            if (root.left == null) return (AVLNode<Key, Value> )(root.right);
            else if (root.right == null) return (AVLNode<Key, Value> )(root.left);
            
            AVLNode<Key, Value> t = root;
            root = (AVLNode<Key, Value>)(super.min(root.right));
            root.right = this.delete(((AVLNode<Key, Value>)(t.right)), root.key);
            root.left = t.left;
        }
        
        if (!this.isBalancedNode(root)) {
            if (
                this.height((AVLNode<Key, Value>)(root.left)) > this.height((AVLNode<Key, Value>)(root.right)) &&
                this.height((AVLNode<Key, Value>)(root.left.left)) >= this.height((AVLNode<Key, Value>)(root.left.right))
            ) {
                root = this.rotateRight(root);
                this.updateNode((AVLNode<Key, Value>) (root.right));
            } else if (
                this.height((AVLNode<Key, Value>)(root.right)) > this.height((AVLNode<Key, Value>)(root.left)) &&
                this.height((AVLNode<Key, Value>)(root.right.right)) >= this.height((AVLNode<Key, Value>)(root.right.left))   
            ) {
                root = this.rotateLeft(root);
                this.updateNode((AVLNode<Key, Value>) (root.left));
            } else if (
                this.height((AVLNode<Key, Value>)(root.left)) > this.height((AVLNode<Key, Value>)(root.right)) &&
                this.height((AVLNode<Key, Value>)(root.left.right)) > this.height((AVLNode<Key, Value>)(root.left.left))
            ) {
                root.left = this.rotateLeft((AVLNode<Key, Value>) (root.left));
                root = this.rotateRight(root);
                this.updateNode((AVLNode<Key, Value>) (root.left));
                this.updateNode((AVLNode<Key, Value>) (root.right));
            } else {
                root.right = this.rotateRight((AVLNode<Key, Value>) (root.right));
                root = this.rotateLeft(root);
                this.updateNode((AVLNode<Key, Value>) (root.left));
                this.updateNode((AVLNode<Key, Value>) (root.right));
            }
        }
        
        this.updateNode(root);
        return root;
    }
    
    @Override
    public void put(Key key, Value value) {
        if (key == null || value == null)
            throw new NullPointerException("Key and Value cannot be null (" + key + ", " + value + ")");
        
        
        super.root = this.put((AVLNode<Key, Value>) (super.root), key, value).first;
    }
    
    @Override
    public Value deleteMin() {
        if (super.isEmpty())
            throw new EmptyStackException();
        
        AVLNode<Key, Value> min = (AVLNode<Key, Value>)(super.min(super.root));
        
        if (min == null) return null;
        
        super.root = this.delete((AVLNode<Key, Value>) (super.root), min.key);
        
        return min.value;
    }
    
    @Override
    public Value deleteMax() {
        if (super.isEmpty())
            throw new EmptyStackException();
        
        AVLNode<Key, Value> max = (AVLNode<Key, Value>)(super.max(super.root));
        
        if (max == null) return null;
        
        super.root = this.delete((AVLNode<Key, Value>) (super.root), max.key);
        
        return max.value;
    }
    
    @Override
    public Value delete(Key key) {
        if (super.isEmpty())
            throw new EmptyStackException();
        
        if (key == null)
            throw new NullPointerException("Key cannot be null (" + key + ")");
        
        AVLNode<Key, Value> x = (AVLNode<Key, Value>)(super.get(super.root, key));
        if (x == null) return null;
        
        super.root = this.delete((AVLNode<Key, Value>) (super.root), key);
        
        return x.value;
    }
    
    @Override
    public Value delete(int rank) {
        if (super.isEmpty())
            throw new EmptyStackException();
        
        if (rank < 0) return null;
        
        if (!super.isValidIndex(rank - 1))
            throw new IndexOutOfBoundsException("Rank " + rank + " is out of bounds!");
        
        AVLNode<Key, Value> x = (AVLNode<Key, Value>)(super.select(super.root, rank));
        if (x == null) return null;
        super.root = this.delete((AVLNode<Key, Value>) (super.root), x.key);
        return x.value;
    }
    
    private static Pair<Boolean, Integer> isAVLTree(AVLNode<Integer, Integer> root) {
        if (root == null) return new Pair<>(true, 0);
        Pair<Boolean, Integer> left = AVLTree.isAVLTree((AVLNode<Integer, Integer>) (root.left));
        if (!left.first) return new Pair<>(false, root.height);
        Pair<Boolean, Integer> right = AVLTree.isAVLTree((AVLNode<Integer, Integer>) (root.right));
        int height = Math.max(left.second, right.second) + 1;
        int diff = Math.abs(left.second - right.second);
        return new Pair<>(height == root.height && right.first && diff <= 1, height);
    }
    
    public static void testAVL(AVLTree<Integer, Integer> avl) {
        BST.test(avl);
        while (!avl.isEmpty()) {
            avl.deleteMin();
        }
        int N = 255;
        Integer[] arrAdd = Arrays.generateRandomArr(N);
        Integer[] arrDelete = Arrays.generateRandomArr(N);
        
        for (int i = 0 ; i < N ; i++) {
            assert avl.size() == i;
            avl.put(arrAdd[i], i);
            assert AVLTree.isAVLTree((AVLNode<Integer, Integer>) (avl.root)).first;
            assert avl.contains(arrAdd[i]);
            assert !avl.isEmpty();
        }
        assert avl.size() == N;
        
        for (int i = 0 ; i < N ; i++) {
            assert avl.size() == N - i : i + ", " + N + ", " + avl.size();
            assert avl.contains(arrDelete[i]);
            assert !avl.isEmpty();
            avl.delete(arrDelete[i]);
            assert AVLTree.isAVLTree((AVLNode<Integer, Integer>) (avl.root)).first;
            if (i < N - 1)
                assert !avl.contains(arrDelete[i]);
        }
        assert avl.size() == 0;
        
        System.out.println("Vali AVL Tree");
    }
    
    public static void main(String[] args) {
        AVLTree.testAVL(new AVLTree<>());
    }
}
