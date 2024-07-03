/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package priorityQueues;

import collection.MyCollection;

/**
 *
 * @author abhishekchopra
 */
public interface PriorityQueue<T> extends MyCollection<T> {
    T peek();
    int sample();
    T remove(int idx);
    T delRandom();
}
