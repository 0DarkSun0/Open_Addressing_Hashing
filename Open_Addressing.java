import java.io.*;
import java.util.*;

public class Open_Addressing {
    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public int[] Table;

    protected Open_Addressing(int w, int seed, int A) {

        this.w = w;
        this.r = (int) (w-1)/2 +1;
        this.m = power2(r);
        if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
        }
        else{
            this.A = A;
        }
        this.Table = new int[m];
        for (int i =0; i<m; i++) {
            Table[i] = -1;
        }

    }

    /** Calculate 2^w*/
    public static int power2(int w) {
        return (int) Math.pow(2, w);
    }

    public static int generateRandom(int min, int max, int seed) {
        Random generator = new Random();
        if(seed>=0){
            generator.setSeed(seed);
        }
        int i = generator.nextInt(max-min-1);
        return i+min+1;
    }

    //hash function g(k)
    public int probe(int key, int i) {
        return (((key * A) % power2(w) >> (w - r)) + i) % power2(r);
    }

    //Inserts key k into hash table. Returns the number of collisions encountered
    public int insertKey(int key){
        int i = 0, collisions = 0;

        //loops until a free index is found
        //or until every index has been checked
        while (true) {
            int index = probe(key, i);

            //insert key if index is free
            if (Table[index] == -1 || Table[index] == -2) {
                Table[index] = key;
                break;
            }

            //if not, there was a collision
            collisions++;

            //checking if every index has been searched
            if (i >= m - 1) break;

            i++;
        }
        return collisions;
    }

    /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
    public int insertKeyArray (int[] keyArray){
        int collision = 0;
        for (int key: keyArray) {
            collision += insertKey(key);
        }
        return collision;
    }

    //Removes key k from the hash table. Returns the number of collisions encountered
    public int removeKey(int key){
        int i = 0, collisions = 0;

        //loops until key is found
        //or until every index has been checked
        while (true) {
            int index = probe(key, i);

            //if the key is found, remove it and return
            //(-2: removed)
            if (Table[index] == key) {
                Table[index] = -2;
                return collisions;
            }
            //if the slot is not empty, there was a collision
            else if (Table[index] != -1) collisions++;
            //return once an empty slot is found (include empty slot)
            else if (Table[index] == -1) return collisions + 1;

            //checking if every index has been searched
            if (i >= m - 1) return collisions;

            i++;
        }
    }
}
