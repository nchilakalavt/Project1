
public class MemManager {
    // Constructor. poolsize defines the size of the memory pool in bytes
    private byte[] pool;
    private DLList<Block> free;

    public MemManager(int poolsize) {
        free = new DLList<Block>();
        pool = new byte[poolsize];
        free.add(new Block(0, poolsize));
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
        Block current = free.get(freeIndex);
        while (current.getLength()/2 >= blockSize) {
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
        return retHand;
    }


    // Return the length of the record associated with theHandle
    public int length(Handle theHandle) {
        
        return 
    }


    // Free a block at the position specified by theHandle.
    // Merge adjacent free blocks.
    public void remove(Handle theHandle) {
        
        int blockSize = 1;
        while (blockSize < theHandle.getLength()) {
            blockSize *= 2;
        }
        Block remBlock = new Block(theHandle.getStartPos(), blockSize);
        int index;
        for(int i = 0; i < free.size(); i++) {
            if (i == free.size() - 1){
                free.add(free.size()-1, remBlock);
            }
            else if (Math.pow(2, i)<= theHandle.getStartPos() && 
                theHandle.getStartPos() < Math.pow(2, i+1)) {
                free.add(i, remBlock);
            }
        }
        merge();
    }


    // Return the record with handle posHandle, up to size bytes, by
    // copying it into space.
    // Return the number of bytes actually copied into space.
    public int get(byte[] space, Handle theHandle, int size) {
        return ;
    }


    // Dump a printout of the freeblock list
    public void dump() {
        for (int i = 0; i < free.size(); i++) {
            System.out.println(Math.pow(2, i) + ": " + );
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
            
            // if the blocks length is bigger than the size and if the blocks
            if (tempBlock.getLength() > size && tempBlock.getEnd() < min) {
                min = tempBlock.getLength();
                index = tempBlock.getStart();
            }
            // end value is less than the min
            if (index == -1) {
                byte[] newPool = new byte[pool.length*2];
                System.arraycopy(pool, 0, newPool, 0, pool.length);
                free.add(new Block(pool.length, newPool.length));
                pool = newPool;
                merge();
            }
            
        }
        // if index is -1 then resize
        return index;
    }
}
