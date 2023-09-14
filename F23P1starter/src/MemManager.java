
public class MemManager {
    // Constructor. poolsize defines the size of the memory pool in bytes
    private int size;
    private byte[] pool;
    private DLList<Block> free;

    public MemManager(int poolsize) {
        free = new DLList<Block>();
        pool = new byte[poolsize];
        free.add(new Block(0, poolsize));
    }


    // Insert a record and return its position handle.
    // space contains the record to be inserted, of length size.
    public int insert(byte[] space, int size) {
        int temp = bestFit(size);
        
    }


    // Return the length of the record associated with theHandle
    public int length(Record theRecord) {
        return 
    }


    // Free a block at the position specified by theHandle.
    // Merge adjacent free blocks.
    public void remove(Record theRecord) {

    }


    // Return the record with handle posHandle, up to size bytes, by
    // copying it into space.
    // Return the number of bytes actually copied into space.
    public int get(byte[] space, Handle theHandle, int size) {

    }


    // Dump a printout of the freeblock list
    public void dump() {

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
            }
            
        }
        // if index is -1 then resize
        return index;
    }
}
