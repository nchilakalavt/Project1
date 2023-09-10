
public class HashTable {
    private Record[] lister;
    private Record tombstone;
    public HashTable(int size) {
        lister = new Record[size];
    }
    public int getSize() {
        return lister.length;
    }
    
    public void Hashinsert(Record r) {
        int homeSlot = 0;

        int id = r.getID();
            //((k/M ) mod (M/2)) ∗ 2) + 1
        homeSlot = id % lister.length;
        if(lister[homeSlot] == null || lister[homeSlot] == tombstone) {
        lister[homeSlot] = r;
        }
        else {
            while(lister[homeSlot] != null) {
                int hash2 = (((id / lister.length ) % (lister.length / 2)) * 2) + 1;
                if(lister[(homeSlot + hash2) % lister.length] == null) {
                    lister[(homeSlot + hash2) % lister.length] = r; 
<<<<<<< Updated upstream
                    break;
=======
>>>>>>> Stashed changes
                }
            }
        }
    }
    public Record search(int id) {
        int homeSlot = 0;

        homeSlot = id % lister.length;
        if(lister[homeSlot] == null) {
            return null;
        }
        if(lister[homeSlot].getID() == id ) {
            return lister[homeSlot];
            
        }
        
        while(lister[homeSlot] == tombstone) {
            int hash2 = (((id / lister.length ) % (lister.length / 2)) * 2) + 1;
            if(lister[(homeSlot + hash2) % lister.length].getID() == id) {
                return lister[(homeSlot + hash2) % lister.length];
            }
            else if(lister[(homeSlot + hash2) % lister.length] == null) {
                return null;
            }
            
        }
        return null;
        
    }
    public void delete(Record r) {
        int id = r.getID();
        int homeSlot = 0;

        homeSlot = id % lister.length;
        while(lister[homeSlot] == tombstone) {
            int hash2 = (((id / lister.length ) % (lister.length / 2)) * 2) + 1;
            if(lister[(homeSlot + hash2) % lister.length].getID() == id) {
               lister[(homeSlot + hash2) % lister.length] = tombstone;
            }
        
            
        }
        
    }

}
    