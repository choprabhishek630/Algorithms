/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package symbolTables;

// for revision
// https://www.coursera.org/learn/algorithms-part1/lecture/wIUNW/2-3-search-trees
// https://www.coursera.org/learn/algorithms-part1/lecture/GZe13/red-black-bsts

/**
 *
 * @author abhishekchopra
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> extends BST<Key, Value> {
    
    public static class RBNode<K, V> extends BST.Node<K, V> {
        public boolean isRed;
        
        public RBNode(K key, V value) {
            super(key, value);
            this.isRed = true;
        }
    }
    
    private boolean isRed(RBNode<Key, Value> node) {
        if (node == null) return false;
        return node.isRed;
    }
    
    
    /*
                b node
               / \\
          (< b)    c x
                  / \
        (> b & < c)  (> c)
    
        After rotating left
                c
              // \
             b    (> c)
            / \
       (< b)   (> b & < c)
             
    */
    private RBNode<Key, Value> rotateLeft(RBNode<Key, Value> node) {
        if (node == null) return null;
        
        if (
            !this.isRed((RBNode<Key, Value>) (node.right)) ||
            this.isRed((RBNode<Key, Value>) (node.left))
        ) return node;
        
        RBNode<Key, Value> x = (RBNode<Key, Value>) (node.right);
        node.right = x.left;
        x.left = node;
        
        x.isRed = node.isRed;
        node.isRed = true;
        
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
    private RBNode<Key, Value> rotateRight(RBNode<Key, Value> node) {
        if (node == null) return null;
        if (
            !this.isRed((RBNode<Key, Value>) (node.left)) ||
            this.isRed((RBNode<Key, Value>) (node.right))
        ) return node;
        
        RBNode<Key, Value> x = (RBNode<Key, Value>) (node.left);
        node.left = x.right;
        x.right = node;
        
        x.isRed = node.isRed;
        node.isRed = true;
        
        return x;
    }
    
    /*
                b
              // \\
              a   c
    
              After flipping color
              b
             / \
            a   c
    */
    private void flipColor(RBNode<Key, Value> node) {
        if (node == null) return;
        
        if (
            !this.isRed((RBNode<Key, Value>)(node.left)) ||
            !this.isRed((RBNode<Key, Value>)(node.right))
        ) return;
        
        ((RBNode<Key, Value>)(node.left)).isRed = false;
        ((RBNode<Key, Value>)(node.right)).isRed = false;
        
        node.isRed = true;
    }
    
    private void updateSize(RBNode<Key, Value> root) {
        if (root == null) return;
        
        root.count = 1 + super.size(root.left) + super.size(root.right);
    }
    
    private RBNode<Key, Value> put(RBNode<Key, Value> root, Key key, Value value) {
        if (root == null) return new RBNode<>(key, value);
        
        int c = super.cmp.compare(key, root.key);
        
        if (c < 0)      root.left = this.put((RBNode<Key, Value>) (root.left), key, value);
        
        else if (c > 0) root.right = this.put((RBNode<Key, Value>) (root.right), key, value);
        
        else            root.value = value;
        
        root = this.rotateLeft(root);
        
        root = this.rotateRight(root);
        
        flipColor(root);
        
        this.updateSize((RBNode<Key, Value>) (root.left));
        this.updateSize((RBNode<Key, Value>) (root.right));
        
        this.updateSize(root);
        
        return root;
    }
    
    
    @Override
    public void put(Key key, Value value) {
        if (key == null || value == null)
            throw new NullPointerException("Key and Value cannot be null (" + key + ", " + value + ")");
        
        super.root = this.put((RBNode<Key, Value>) (super.root), key, value);
    }
    
    @Override
    public Value deleteMin() {
        // TODO
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Value deleteMax() {
        // TODO
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Value delete(Key key) {
        // TODO
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Value delete(int rank) {
        // TODO
        throw new UnsupportedOperationException();
    }
    
    public static void main(String[] args) {
        // TODO
        // OrderedST.test(new RedBlackBST<>());
    }
}
