/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package collection;
import java.util.Comparator;
import java.util.function.BiFunction;

/**
 *
 * @author abhishekchopra
 */
public interface MyCollection<T> extends Iterable<T> {
    
    void add(T item);
    
    T remove();
    
    int size();
    
    boolean isEmpty();
    
    default boolean contains(T item) {
        for (T i : this) {
            if (i.equals(item)) return true;
        }
        return false;
    }
    
    default boolean contains(T item, Comparator<? super T> comparator) {
        for (T i : this) {
            if (comparator.compare(item, i) == 0) return true;
        }
        return false;
    }
    
    default boolean contains(T item, BiFunction<? super T,? super T, Boolean> comparator) {
        for (T i : this) {
            if (comparator.apply(item, i)) return true;
        }
        return false;
    }
    
    default T[] toArray() {
        int N = this.size(), i = 0;
        T[] result = (T[]) new Object[N];
        for (T item : this) result[i++] = item;
        return result;
    }
}
