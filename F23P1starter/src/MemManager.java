
public class MemManager {
    // Constructor. poolsize defines the size of the memory pool in bytes
    private byte[] pool;
    private DLList<Block> free;
    private Seminar sem;
    private int counter;

    public MemManager(int poolsize) {
        free = new DLList<Block>();
        pool = new byte[poolsize];
        free.add(new Block(0, poolsize));
        counter = 0;
    }


    // Insert a record and return its position handle.
    // space contains the record to be inserted, of length size.
    public Handle insert(byte[] space, int size) {
        int temp = bestFit(size);
        int blockSize = 1;
        System.arraycopy(space, 0, pool, free.get(temp).getStart(), size);
        
        while (blockSize < size) {
            blockSize *= 2;
        }
        int freeIndex = bestFit(blockSize);
        
        while (free.get(freeIndex).getLength()/2 >= blockSize) {
            Block current = free.get(freeIndex);
            int halfLength = current.getLength()/2;
            Block halfBlock = new Block(current.getStart(), halfLength);
            Block halfBlock2 = new Block(halfLength, current.getEnd());
            free.remove(current);
            free.add(freeIndex, halfBlock);
            free.add(freeIndex+1, halfBlock2);
            
        }
        Handle retHand = new 
        Handle(free.get(temp).getStart(), free.get(temp).getStart()+size);
        free.remove(free.get(temp));
        merge();
        counter++;
        
        return retHand;
    }


    // Return the length of the record associated with theHandle
    public int length(Handle theHandle) {
        
        return theHandle.getLength();
    }


    // Free a block at the position specified by theHandle.
    // Merge adjacent free blocks.
    public void remove(Handle theHandle) {
        if (counter == 0) {
            System.out.println("remove failed");
        }
        int blockSize = 1;
        while (blockSize < theHandle.getLength()) {
            blockSize *= 2;
        }
        Block remBlock = new Block(theHandle.getStartPos(), blockSize);
        if (theHandle.getStartPos() == 0){
            free.add(0, remBlock);
        }
        else {
            for(int i = 0; i < free.size(); i++) {
                if (Math.pow(2, i)<= theHandle.getStartPos() && 
                    theHandle.getStartPos() < Math.pow(2, i+1)) {
                    free.add(i, remBlock);
                 }
            }
        }
        for (int i = theHandle.getStartPos(); i < theHandle.getEndPos(); i++) {
            pool[i] = 0;
        }
        merge();
        counter--;
    }


    // Return the record with handle posHandle, up to size bytes, by
    // copying it into space.
    // Return the number of bytes actually copied into space.
    public int get(byte[] space, Handle theHandle, int size) {
        System.arraycopy(pool, theHandle.getStartPos(), space, 0, size);
        return space.length;
    }


    // Dump a printout of the freeblock list
    public void dump() {
        System.out.println("Freeblock list: ");
        for (int i = 0; i < free.size(); i++) {
            
            System.out.println(free.get(i).getLength()/(i+1) + ": " + 
            free.get(i).getLength());
        }
    }
    
    private void merge() {
        for (int i = 0; i < free.size()-1; i++) {
                if((free.get(i).getStart() | free.get(i).getLength()) 
                    == (free.get(i+1).getStart() | free.get(i+1).getLength())) {
                    Block addBlock = new Block(free.get(i).getStart(), free.get(i).getEnd());
                    free.remove(free.get(i));
                    free.remove(free.get(i+1));
                    merge();
                }
        }    
    }

    private int bestFit(int size) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < free.size(); i++) {
            // block at index i
            Block tempBlock = free.get(i);
            
            System.out.println("tempblock size: " + tempBlock.getLength());
            if (tempBlock.getLength() > size && tempBlock.getEnd() < min) {
                min = tempBlock.getLength();
                index = tempBlock.getStart();
            }

            if (index == -1) {
                byte[] newPool = new byte[pool.length*2];
                System.arraycopy(pool, 0, newPool, 0, pool.length);
                free.add(new Block(pool.length, newPool.length));
                pool = newPool;
                merge();
            }
            
        }
        // if index is -1 then resize
        System.out.println("this is index " + index);
        return index;
    }
}
