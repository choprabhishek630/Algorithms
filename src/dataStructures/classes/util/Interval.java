/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.util.Comparator;

/**
 *
 * @author abhishekchopra
 */
public class Interval<Key extends Comparable<Key>> {
    private Comparator<Key> cmp;
    private Key lo;
    private Key hi;
    
    public Interval(Key lo, Key hi) {
        this(lo, hi, Comparator.naturalOrder());
    }
    
    public Interval(Key lo, Key hi, Comparator<Key> cmp) {
        if (cmp != null) this.cmp = cmp;
        else this.cmp = Comparator.naturalOrder();
        if (lo == null || hi == null)
            throw new NullPointerException("High or Lo cannot be null (" + hi + ", " + lo + ")");
        if (lo.compareTo(hi) > 0) {
            this.lo = hi;
            this.hi = lo;
        } else {
            this.lo = lo;
            this.hi = hi;
        }
    }
    
    public Key low() {
        return this.lo;
    }
    
    public Key high() {
        return this.hi;
    }
    
    public Key setLow(Key lo) {
        if (lo == null)
            throw new NullPointerException("Low cannot be null");
        if (lo.compareTo(this.hi) > 0)
            throw new IllegalArgumentException("Low cannot be greater than High");
        Key prevLo = this.lo;
        this.lo = lo;
        return prevLo;
    }
    
    public Key setHigh(Key hi) {
        if (hi == null)
            throw new NullPointerException("High cannot be null");
        if (hi.compareTo(this.lo) < 0)
            throw new IllegalArgumentException("High cannot be lower than Low");
        Key prevHi = this.hi;
        this.hi = hi;
        return prevHi;
    }
    
    public boolean intersects(Interval b) {
        return b.high().compareTo(this.lo) >= 0 && b.low().compareTo(this.hi) <= 0;
    }
}
