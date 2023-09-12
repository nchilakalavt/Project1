
public class MemManager {
    private int memBytes;
    private DLList<Record> list;
    private int pos;
   //Constructor. poolsize defines the size of the memory pool in bytes
    public MemManager(int poolsize){
        memBytes = poolsize;
    }
    // Insert a record and return its position handle.
    // space contains the record to be inserted, of length size.
    public Record insert(byte[] space, int size) {
        memBytes-=size;
        
        return null;
    }
    // Return the length of the record associated with theHandle
    public int length(Record theRecord) {
        return theRecord.getLength();
    }
    
    // Free a block at the position specified by theHandle.
    // Merge adjacent free blocks.
    public void remove(Record theRecord) {
        
    }
    // Return the record with handle posHandle, up to size bytes, by
    // copying it into space.
    // Return the number of bytes actually copied into space.
    public int get(byte[] space, Record theRecord, int size) {
        
    }
    // Dump a printout of the freeblock list
    public void dump() {
        
    }
}
