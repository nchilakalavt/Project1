
/**
 * Memory Manager class
 * handles the memory pool and the free lists
 * 
 * @author nchilakala pratc
 * @version 55.0
 * 
 */
public class MemManager {
    private int poolSize;
    private LinkedList[] freeListArr;
    private byte[] pool;

    /**
     * MemManager constructor
     * 
     * @param poolSize:
     *            size of the memory pool
     *
     */
    public MemManager(int poolSize) {
        this.poolSize = poolSize;

        pool = new byte[poolSize];

        int numLists = (int)(Math.log(poolSize) / Math.log(2));
        ;

        freeListArr = new LinkedList[numLists + 1];

        for (int i = 0; i < numLists + 1; i++) {
            freeListArr[i] = new LinkedList();
        }

        freeListArr[numLists].add(0);

    }


    /**
     * Finds the minimum power of 2 that is bigger than the size
     * 
     * @param size:
     *            size of the block that we want to hold
     * @return int: minimum size that the block can be so that it can fit the
     *         size parameter
     */
    public int minPowerTwo(int size) {
        int blockSize = 0;
        while ((1 << blockSize) < size) {
            blockSize++;
        }
        return blockSize;
    }


    /**
     * Insert method that adds to the memory pool and removes from the free
     * block list
     * 
     * @param space:
     *            serialized byte array that holds the seminar object
     * @param size:
     *            size of the seminar object
     * @return the position handle
     */
    public Handle insert(byte[] space, int size) {
        Handle result = null;
        while (result == null) {
            int listIndex = minPowerTwo(size);

            for (int i = listIndex; i < freeListArr.length; i++) {
                if (!freeListArr[i].isEmpty()) {
                    if (i == listIndex) {
                        int index = freeListArr[i].removeFirst();
                        System.arraycopy(space, 0, pool, index, size);
                        return new Handle(index, size);

                    }
                    else {
                        int index = freeListArr[i].removeFirst();

                        for (int j = i; j > listIndex; j--) {
                            int budLoc = mergeIndex(index, 1 << (j - 1));
                            freeListArr[j - 1].add(budLoc);
                        }

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
     * Method to merge index
     * 
     * @param startIndex
     *            starting index to merge
     * @param size
     *            the size of the pool
     * @return
     */
    private int mergeIndex(int startIndex, int size) {
        int buddyIndex = 0;

        if ((startIndex / size) % 2 == 1) {
            buddyIndex = startIndex - size;
        }
        else {
            buddyIndex = startIndex + size;
        }

        return buddyIndex;
    }


    /**
     * Removes handle from memory pool and adds to free block list
     * 
     * @param theHandle:
     *            Handle that holds position to be removed
     */
    public void remove(Handle theHandle) {
        int startPos = theHandle.getStartPos();
        int handleSize = theHandle.getLength();

        int listIndex = minPowerTwo(handleSize);
        while (listIndex < freeListArr.length - 1) {

            handleSize = (int)(Math.pow(handleSize, 2));

            int buddyIndex = mergeIndex(startPos, handleSize);

            if (freeListArr[listIndex].contains(buddyIndex)) {
                freeListArr[listIndex].remove(buddyIndex);
                startPos = Math.min(startPos, buddyIndex);
                handleSize *= 2;
                listIndex++;
            }
            else {
                break;
            }
            listIndex++;
        }
        freeListArr[listIndex].add(startPos);

    }


    /**
     * Returns length of the handle
     * 
     * @param theHandle:
     *            Handle whose length we are looking for
     * @return int: the length of the handle
     */
    public int length(Handle theHandle) {
        return theHandle.getLength();
    }


    /**
     * Increases the size of the memory pool
     */
    public void expandPool() {
        int newSize = poolSize * 2;
        byte[] newPool = new byte[newSize];

        for (int i = 0; i < poolSize; i++) {
            newPool[i] = pool[i];
        }
        this.pool = newPool;
        int numLists = (int)(Math.log(newSize) / Math.log(2));

        LinkedList[] newfreeListArr = new LinkedList[numLists + 1];

        for (int i = 0; i < freeListArr.length; i++) {
            newfreeListArr[i] = freeListArr[i];
        }
        newfreeListArr[newfreeListArr.length - 1] = new LinkedList();

        if (newfreeListArr[newfreeListArr.length - 2].contains(0)) {
            newfreeListArr[newfreeListArr.length - 2].remove(0);
            newfreeListArr[newfreeListArr.length - 1].add(0);
        }
        else {
            newfreeListArr[newfreeListArr.length - 2].add(pool.length / 2);
        }

        this.freeListArr = newfreeListArr;
        this.poolSize = newSize;

        System.out.println("Memory pool expanded to " + newSize + " bytes");
    }


    /**
     * Prints the memory pool
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
     * Prints a string of the Free Block List
     */
    public void dump() {
        System.out.println("Freeblock List:");
        boolean noBlocks = true;
        for (int i = 0; i < freeListArr.length; i++) {
            int blockSize = (int)Math.pow(2, i);

            if (!freeListArr[i].isEmpty()) {
                System.out.print(blockSize + ":");
                noBlocks = false;

                LinkedList.Node currentNode = freeListArr[i].getHead();
                while (currentNode != null) {
                    System.out.print(" " + currentNode.getData());
                    currentNode = currentNode.getNextNode();
                }
                System.out.println();
            }

        }
        if (noBlocks) {
            System.out.println("There are no freeblocks in the memory pool");
        }
    }


    /**
     * Returns the size of the memory pool
     * 
     * @return int: size of the pool in bytes
     */
    public int getPoolSize() {
        return poolSize;
    }


    /**
     * Gets the byte array that is held within the position handle
     * 
     * @param theHandle:
     *            Handle that holds the position of what should be returned
     * @return byte array with that contains what was held in the postion handle
     */
    public byte[] get(Handle theHandle) {
        byte[] space = new byte[theHandle.getLength()];
        System.arraycopy(pool, theHandle.getStartPos(), space, 0, theHandle
            .getLength());
        return space;
    }


    /**
     * Gets the byte array that is the memory pool
     * 
     * @return byte[] memory pool
     */
    public byte[] getPool() {
        return this.pool;
    }

}
