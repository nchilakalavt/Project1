
public class SemDatabase {
    private HashTable hash;
    private MemManager m;

    public SemDatabase(int hashTableSize, int poolSize) {
        hash = new HashTable(hashTableSize);
        m = new MemManager(poolSize);
        
    }
    public void delete(int key) {
        Record r = hash.search(key);
        hash.delete(key);
        m.remove(r.getHandle());
    }
    public void insert(Seminar s) throws Exception {
        if(hash.search(s.getID())==null) {
            Handle h = m.insert(s.serialize(), s.serialize().length);
            hash.hashInsert(new Record(h, s.getID()));
        }
        else {
            System.out.println("Insert Failed");
        }

    }
    public void searcher(int ID) {
//        Handle h = new Handle(3, 20);// 3 , 20 r dummy values
//        Record r = new Record(h, ID);
        Record r = hash.search(ID);
        if (r != null) {
            byte[] arr = new byte[r.getHandle().getLength()];
            m.get(arr, r.getHandle(), ID);
            try {
                Seminar s = Seminar.deserialize(arr);
                System.out.println(s.toString());
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        else {
            System.out.println("search failed");
        }
        
    }
    public void printHash() {
        System.out.println(hash.toString());
    }
    public void printBlock() {
        m.dump();
    }
    
}
