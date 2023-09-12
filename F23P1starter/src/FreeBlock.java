
public class FreeBlock {
    private DLList<Byte> list; 
    int blocksize;
    
    public FreeBlock(int size) {
        list = new DLL<Byte>();
        int blocksize = size;
    }
    
    public void append(int pos) {
        int position = pos;
        
    }
}
