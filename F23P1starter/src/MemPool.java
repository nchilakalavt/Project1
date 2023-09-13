
public class MemPool {
    private byte[] pool;
    private int size;
    
    public MemPool(int size) {
        this.size = size;
        pool = new byte[size];
    }
    
    public void insert(int pos, byte[] insertByte) {
        pool[pos] = (byte)(insertByte.length >> 8);
        
        for (int i = 0; i < insertByte.length; i++) {
            pool[pos+1+i] = insertByte[i];
        }
    }
    
    public byte get(int pos) {
        
        return pool[pos];
    }
    
    public byte[] doubleSize() {
        byte[] newPool = new byte[pool.length+size];
        for(int i = 0; i < newPool.length; i++) {
            newPool[i] = pool[i];
        }
        return newPool;
    }
}
