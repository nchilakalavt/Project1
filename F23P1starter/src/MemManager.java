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


    public MemManager(int poolSize) {
        this.poolSize = poolSize;

        pool = new byte[poolSize];

        int numLists = (int)(Math.log(poolSize) / Math.log(2));;

        freeListArr = new LinkedList[numLists + 1];

        for (int i = 0; i < numLists + 1; i++) {
            freeListArr[i] = new LinkedList();
        }

        freeListArr[numLists].add(0);

    }



    private int minPowerTwo(int size) {
        int blockSize = 0;
        while ((1 << blockSize) <= size) {
            blockSize++;
        }
        return blockSize;
    }



    public Handle insert(byte[] space, int size) {
        // find the smallest index at which fit works
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
                    else // splitting blocks here
                    {
                        int index = freeListArr[i].removeFirst();
                        // work down from listIndex, the
                        for (int j = i; j > listIndex; j--) {
                            int budLoc = mergeIndex(index, 1 << (j - 1));
                            freeListArr[j - 1].add(budLoc);
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



    public void remove(Handle theHandle) {
        int startPos = theHandle.getStartPos();
        int handleSize = theHandle.getLength();

        int listIndex = minPowerTwo(handleSize);
        while (listIndex < freeListArr.length - 1) {

            handleSize = (int)(Math.pow(handleSize, 2));

            int buddyIndex = mergeIndex(startPos, handleSize);

            if (freeListArr[listIndex].contains(buddyIndex)) {
                // removing and preparing for merge
                freeListArr[listIndex].remove(buddyIndex);

                // find the smaller of the buddies
                startPos = Math.min(startPos, buddyIndex);

                // double size of existing node for merge
                handleSize *= 2;

                // go to next level to check for more buddies
                listIndex++;

            }
            else {
                break;
            }
            listIndex++;
        }
        freeListArr[listIndex].add(startPos);

    }



    public int length(Handle theHandle) {
        return theHandle.getLength();
    }


    public void expandPool() {
        int newSize = poolSize * 2;
        byte[] newPool = new byte[newSize];

        for (int i = 0; i < poolSize; i++) {
            newPool[i] = pool[i];
        }
        this.pool = newPool;
        int numLists = (int)(Math.log(newSize)/Math.log(2));

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


    public void dump() {
        System.out.println("Freeblock List:");
        boolean noBlocks = true;
        for (int i = 0; i < freeListArr.length; i++) {
            // Calculate the size of the block based on its index in freeListArr
            int blockSize = (int)Math.pow(2, i);

            // Only print out lists that are not empty
            if (!freeListArr[i].isEmpty()) {
                System.out.print(blockSize + ":");
                noBlocks = false;

                // Use an inner node to traverse the linked list
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



    public int getPoolSize() {
        return poolSize;
    }



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
