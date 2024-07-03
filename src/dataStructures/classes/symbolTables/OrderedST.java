/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package symbolTables;

/**
 *
 * @author abhishekchopra
 */
public interface OrderedST<Key, Value> extends SymbolTable<Key, Value> {
    int rank(Key key);
}
