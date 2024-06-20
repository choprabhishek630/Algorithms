/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vector;
import collection.MyCollection;
import java.util.function.BiFunction;

/**
 *
 * @author abhishekchopra
 */
public interface VectorInterface<T> extends MyCollection<T> {
    T at(int idx);
    
    boolean swap(int idx1, int idx2);
    
    void sort(BiFunction<? super T, ?super T, Integer> comparator);
}
