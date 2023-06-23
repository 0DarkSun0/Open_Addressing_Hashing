# Open_Addressing_Hashing
Java class containing methods to create a hash table that solves collisions using open addressing. The hash table is implemented using the linear probing function $g(k,i)=(h(k)+i) \mod 2^r$, where $h(k)=((A \cdot k) \mod 2^w) >> (w-r)$. $r$ and $w$ are two integers such that $w>r$ and $A$ is a random number such that $2^{w-1} < A < 2^w$. Let $n$ be the number of keys inserted and $m$ be the number of slots in the table. 
  
The helper method `power2` calculates the square of the argument passed to it. `generateRandom` generates a random number within a specified range. 

The method `probe` implements the hash function $g$. It takes a key $k$ and an integer $0 \le i < m$, and returns a hash value in $[0,m[$. 

The `insertKey` method inserts a key $k$ into the hash table and returns the number of collisions encountered before insertion (or the number of collisions encountered before giving up on insertion). We assume that the key is not negative and that the key has not already been inserted into the hash table.

The `removeKey` method takes a key $k$ as input and removes it from the hash table while visiting the minimum number of slots possible. Like `insertKey`, this method returns the number of collisions if the key is found. If the key is not found, the method returns the number of slots visited before giving up.

Empty slots are given a value of $-1$.

This class was created on February 14th, 2023. The helper methods `power2` and `generateRandom` were provided by the COMP 251 course staff at McGill University. The methods `probe`, `insertKey`, and `removeKey` were created by Stefan Wallin.
