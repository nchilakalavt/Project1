
public class MemManager {
 // Constructor. poolsize defines the size of the memory pool in bytes
    private int size;
    private MemPool pool;
    private FreeBlock free;
    
    public MemManager(int poolsize){
        free = new FreeBlock(poolsize);
        pool = new MemPool(poolsize);
    }
    // Insert a record and return its position handle.
    // space contains the record to be inserted, of length size.
    public Record insert(byte[] space, int size) {
        pool.insert(size, space);
        free.append(size, space);
    }
    // Return the length of the record associated with theHandle
    public int length(Record theRecord) {
        theRecord.getLength();
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
}
