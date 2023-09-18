/**
 * Memory Manager class
 * handles the memory pool and the free lists
 * 
 * @author keshr xavier0ne
 * @version 55.0
 * 
 */
public class MemManager {
    private int poolSize;
    private LinkedList[] freeLists;
    private byte[] pool;

    /**
     * Constructor
     * 
     * @param poolSize
     *            -the size of the pool
     */
    public MemManager(int poolSize) {
        this.poolSize = poolSize;

        pool = new byte[poolSize];

        int numLists = (int)Math.round(log2(poolSize));

        freeLists = new LinkedList[numLists + 1];

        for (int i = 0; i < numLists + 1; i++) {
            freeLists[i] = new LinkedList();
        }

        freeLists[numLists].add(0);

        // my nodes are only holding the indexes
        // it should start with the last index of the freelists as 0
        // as a new request to add a byte[] is should use the buddy system
        // split up the orginal freelists into smaller ones at different
        // indexes representing the space that was left
        // Handles are used to start pos and the space taken in the pool
    }


    /**
     *
     * Calculates the minimum power of two
     * 
     * @param size
     *            -size
     * @return int -power
     */
    private int nextPower(int size) {
        int n = 0;
        while ((1 << n) < size) {
            n++;
        }
        return n;
    }


    /**
     * insert method
     * 
     * @param space
     *            -serialized byte array
     * @param size
     *            -size of the byte array
     * @return Handle -the handle
     */
    public Handle insert(byte[] space, int size) {
        // find the smallest index at which fit works
        Handle result = null;
        while (result == null) {
            int listIndex = nextPower(size);

            for (int i = listIndex; i < freeLists.length; i++) {
                if (!freeLists[i].isEmpty()) {
                    if (i == listIndex) {
                        int index = freeLists[i].removeFirst();
                        System.arraycopy(space, 0, pool, index, size);
                        return new Handle(index, size);

                    }
                    else // splitting blocks here
                    {
                        int index = freeLists[i].removeFirst();
                        // work down from listIndex, the
                        for (int j = i; j > listIndex; j--) {
                            int budLoc = mergeIndex(index, 1 << (j - 1));
                            freeLists[j - 1].add(budLoc);
                        }
                        // does the copied size need to be bump up?
                        System.arraycopy(space, 0, pool, index, size);
                        return new Handle(index, size);
                    }

                }
            }
            expandPool();
        }
        return result;
    }


    /**
     * 
     * @param index
     *            starting index of orginial block
     * @param size
     *            length of the block
     * @return int -buddy location
     */
    private int mergeIndex(int index, int size) {
        int buddyLoc = 0;

        if ((index / size) % 2 == 1) {
            buddyLoc = index - size;
        }
        else {
            buddyLoc = index + size;
        }

        return buddyLoc;
    }


    /**
     * remove method
     * 
     * @param theHandle
     *            -the handle
     */
    public void remove(Handle theHandle) {
        int start = theHandle.getStartPos();
        int hSize = theHandle.getLength();

        int listIndex = nextPower(hSize);
        while (listIndex < freeLists.length - 1) {
            // the if statement can break prematruely on the remove
            // the case where I removed 3
            // the buddyIndex is calculated as 3 making it so it doesn't enter
            // the if statment and then adds the start node making it not work
            hSize = (int)(Math.pow(hSize, 2));

            int buddyIndex = mergeIndex(start, hSize);

            if (freeLists[listIndex].contains(buddyIndex)) {
                // removing and preparing for merge
                freeLists[listIndex].remove(buddyIndex);

                // find the smaller of the buddies
                start = Math.min(start, buddyIndex);

                // double size of existing node for merge
                hSize = hSize * 2;

                // go to next level to check for more buddies
                listIndex++;

            }
            else {
                break;
            }
        }
        freeLists[listIndex].add(start);

    }


    /**
     * length method
     * 
     * @param theHandle
     *            -the handle
     * @return int -length
     */
    public int length(Handle theHandle) {
        return theHandle.getLength();
    }


    /**
     * log2 method
     * 
     * @param value
     *            -the value
     * @return log -the base 2 log
     */
    private double log2(int value) {
        return Math.log(value) / Math.log(2);
    }



    /**
     * expandPool method
     */
    public void expandPool() {
        int newSize = poolSize * 2;
        byte[] newPool = new byte[newSize];

        for (int i = 0; i < poolSize; i++) {
            newPool[i] = pool[i];
        }
        this.pool = newPool;
        int numLists = (int)(Math.log(newSize)/Math.log(2));

        LinkedList[] newFreeLists = new LinkedList[numLists + 1];

        for (int i = 0; i < freeLists.length; i++) {
            newFreeLists[i] = freeLists[i];
        }
        newFreeLists[newFreeLists.length - 1] = new LinkedList();

        if (newFreeLists[newFreeLists.length - 2].contains(0)) {
            newFreeLists[newFreeLists.length - 2].remove(0);
            newFreeLists[newFreeLists.length - 1].add(0);
        }
        else {
            newFreeLists[newFreeLists.length - 2].add(pool.length / 2);
        }

        this.freeLists = newFreeLists;
        this.poolSize = newSize;

        System.out.println("Memory pool expanded to " + newSize + " bytes");
    }


    /**
     * printPool method
     */
    public void printPool() {
        for (int i = 0; i < pool.length; i++) {
            if (pool[i] == 0) {
                System.out.print('-');
            }
            else {
                System.out.print("X");
            }

            if ((i + 1) % 64 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }


    /**
     * printFreeBlocks method
     */
    public void dump() {
        System.out.println("Freeblock List:");
        boolean noBlocks = true;
        for (int i = 0; i < freeLists.length; i++) {
            // Calculate the size of the block based on its index in freeLists
            int blockSize = (int)Math.pow(2, i);

            // Only print out lists that are not empty
            if (!freeLists[i].isEmpty()) {
                System.out.print(blockSize + ":");
                noBlocks = false;

                // Use an inner node to traverse the linked list
                LinkedList.Node currentNode = freeLists[i].getHead();
                while (currentNode != null) {
                    System.out.print(" " + currentNode.getData());
                    currentNode = currentNode.getNext();
                }
                System.out.println();
            }

        }
        if (noBlocks) {
            System.out.println("There are no freeblocks in the memory pool");
        }
    }


    /**
     * getFreeLists method
     * 
     * @return int -the free lists
     */
    public int getPoolSize() {
        return poolSize;
    }


    /**
     * Return the next integer of 2^n
     * 
     * @param n
     *            -input
     * @return int-the next power of 2
     *
    public int nextPowerOfTwo(int n) {
        if (Integer.highestOneBit(n) == n) {
            return n;
        }
        return Integer.highestOneBit(n) * 2;
    }*/


    /**
     * getByte
     * 
     * @param theHandle
     *            -the handle
     * @return byte[] -the byte array
     */
    public byte[] get(Handle theHandle) {
        byte[] space = new byte[theHandle.getLength()];
        System.arraycopy(pool, theHandle.getStartPos(), space, 0, theHandle
            .getLength());
        return space;
    }
    
    public byte[] getPool(){
        return this.pool;
    }

}
