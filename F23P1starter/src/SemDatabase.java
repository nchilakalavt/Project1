
public class SemDatabase {
    private HashTable hash;
    private MemManager m;

    public SemDatabase(HashTable hasher, MemManager manager) {
        hash = hasher;
        m = manager;
        
    }
    public void delete(int key ) {
        Handle h = new Handle(3, 20);// 3 , 20 r dummy values
        Record r = new Record(h , key);
        hash.delete(r);
    }
    public void insert(Seminar s) throws Exception {
        Handle h = new Handle(3, s.serialize().length);//3 is dummy value
        Record r2 = new Record(h, s.getID());
        hash.hashInsert(r2);
    }
    public void searcher(int ID ) {
        Handle h = new Handle(3, 20);// 3 , 20 r dummy values
        Record r = new Record(h, ID);
        hash.search(ID);
    }
    public void printHash() {
        hash.toString();
    }
    public void printBlock() {
        
    }
    
}
