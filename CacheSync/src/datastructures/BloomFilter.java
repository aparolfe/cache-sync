package datastructures;

import java.util.BitSet;
/**
 * @author Aparna
 * Implementation of a Bloom filter with false positive rate 0.05%
 */
public class BloomFilter {
    private final int n;              // number of items that can be inserted
    private final int m;              // number of bits in bloom filter (16 bits per item)
    private final int k;              // number of hash functions
    private final double errorRate = 0.00046;   // false positive rate: Math.pow(0.619, m/n)
    private final BitSet b;
    
    /** 
     * Creates a Bloom filter optimized for input of ~150000 strings
     */
    public BloomFilter() {
        this.n = 150000; // approximate number of strings in representative input file
        this.m = 16 * n; // each entry alloted 16 bits to maintain low error rate
        this.k = 13;     // (int) Math.floor(m/n*Math.log(2.0))=11, but 13 worked better 
        this.b = new BitSet(m);
        System.out.println("Bloom filter created of size in bits: " + m);
    }
    
    /** 
     * Creates a Bloom filter to contain a given number of strings
     * @param n number of strings expected to be inserted into the Bloom filter	
     */
    public BloomFilter(int n) {
        this.n = n;
        this.m = 16 * n; // each entry alloted 16 bits to maintain low error rate
        this.k = (int) Math.floor(m/n*Math.log(2.0)); 
        this.b = new BitSet(m);
        System.out.println("Bloom filter created of size in bits: " + m);
    }
    
    /** 
     * Creates a Bloom filter from a byte array
     * @param byteArray byte array of the bits in the bloom filter
     */
    public BloomFilter(byte[] byteArray) {
        this.m = 8 * byteArray.length; // number of bits
        this.n = m/16; 
        this.k = (n == 150000)? 13: (int) Math.floor(m/n*Math.log(2.0)); 
        this.b = BitSet.valueOf(byteArray);
        System.out.println("Bloom filter created of size in bits: " + m);
    }
    
    /** 
     * Adds a string to the Bloom filter
     * @param s string to be inserted into the Bloom filter	
     */
    public void add(String s) {
        int[] hashValues = hash(s); 
        // set bit at each hashvalue to 1
        for (int i : hashValues) {
            b.set(i);
        }
    }
  
    /** 
     * Returns whether string was added to the Bloom filter
     * @param s string to be checked for presence the Bloom filter
     * @return false if s was not added; true if s might have been added
     */
    public boolean contains(String s) {
        int[] hashValues = hash(s);
        // for each hashvalue, if bit is 0 return false
        for (int i : hashValues) {
            if (!b.get(i))
                return false;
        }
        return true;
    }      
       
    /** 
     * Returns size of Bloom Filter
     * @return int number of bits stored in the Bloom filter
     */
    public int size() {
        return m;
    }
    
    /** 
     * Returns a byte array representation of the Bloom filter
     * @return a byte array of all the bits in the bloom filter
     */
    public byte[] toByteArray() {
        return b.toByteArray();
    }   
           
    /** 
     * Generates array of hash values for a given string
     * @param s string to be hashed
     * @return int[] array of hash values
     */
    private int[] hash(String s) {
        int[] values = new int[k];
        // hash the string k times and get k integers %mn
            for (int i = 0; i < k; i++) {
                values[i] = Math.abs(murmur(s, 0x9747b28c+i*850349)) % m;
            }
        return values;
    }
    
    /** 
     * Generates 32 bit hash from byte array of the given length and seed 
     * Adapted from public domain murmur hash 2.0 code 
     * by Viliam Holub from http://murmurhash.googlepages.com/ 
     *
     * @param s string to hash
     * @param seed initial seed value
     * @return 32 bit hash of the given array
     */
    private int murmur(String s, int seed) {
        final byte[] data = s.getBytes();
        int length = s.length();
        final int mix1 = 0x5bd1e995;    // mixing constant generated offline
        final int mix2 = 24;            // mixing constant generated offline
        // Initialize the hash to a random value
        int hash = seed^length;
        int length4 = length/4;
        for (int i=0; i<length4; i++) {
            final int i4 = i*4;
            int temp = (data[i4+0]&0xff) +((data[i4+1]&0xff)<<8)
                    +((data[i4+2]&0xff)<<16) +((data[i4+3]&0xff)<<24);
            temp *= mix1;
            temp ^= temp >>> mix2;
            temp *= mix1;
            hash *= mix1;
            hash ^= temp;
        }	
        // Handle the last few bytes of the input array
        switch (length%4) {
        case 3: hash ^= (data[(length&~3) +2]&0xff) << 16;
        case 2: hash ^= (data[(length&~3) +1]&0xff) << 8;
        case 1: hash ^= (data[length&~3]&0xff);
                hash *= mix1;
        }
        hash ^= hash >>> 13;
        hash *= mix1;
        hash ^= hash >>> 15;

        return hash;
    }
        
}