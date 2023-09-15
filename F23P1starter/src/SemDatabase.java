
public class SemDatabase {
    private HashTable hash;
    private MemManager m;

    public SemDatabase(int hashTableSize, int poolSize) {
        hash = new HashTable(hashTableSize);
        m = new MemManager(poolSize);
        
    }
    public void delete(int key) {
        //Handle h = new Handle(3, 20);// 3 , 20 r dummy values
        //Record r = new Record(h , key);
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
        if (hash.search(ID) != null) {
            
        }
        else {
            System.out.println("search failed");
        }
        
    }
    public void printHash() {
        hash.toString();
    }
    public void printBlock() {
        
    }
    
}
