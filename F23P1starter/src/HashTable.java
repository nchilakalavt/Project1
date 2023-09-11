public class HashTable {
    private Record[] lister;
    private Record tombstone = null;
    private int counter = 0;
    public HashTable(int size) {
        lister = new Record[size];
    }

    public int getSize() {
        return lister.length;
    }
    
    public void hashInsert(Record r) {
        if (counter == lister.length/2) {
            lister = halfFull();
        }
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
                    break;
                }
            }
        }
        counter += 1;
        
    }
    public Record search(int id) {
        int homeSlot = 0;

        homeSlot = id % lister.length;
        if(lister[homeSlot] == null) {
            return null;
        }
        if(lister[homeSlot].getID()== id ) {
            return lister[homeSlot];
            
        }
        
        while(lister[homeSlot] != tombstone) {
            int hash2 = (((id / lister.length ) % (lister.length / 2)) * 2) + 1;
            
            if(lister[(homeSlot + hash2) % lister.length] == null) {
                return null;
            }
            else if(lister[(homeSlot + hash2) % lister.length].getID() == id) {
                return lister[(homeSlot + hash2) % lister.length];
            }
           
        }
        return null;
        
    }
    public String delete(Record r) {
        int id = r.getID();
        tombstone = this.search(id);
        int homeSlot = id % lister.length;
        if (tombstone != null) {
            tombstone.setID(-1);
            lister[homeSlot] = tombstone;
            return "Record with ID " + id + " has been deleted";
        }
        else {
            return "Record with ID " + id + " not found.";
        }
        /*while(lister[homeSlot] != tombstone) {
            int hash2 = (((id / lister.length ) % (lister.length / 2)) * 2) + 1;
            if(lister[(homeSlot + hash2) % lister.length].getID() == id) {
               lister[(homeSlot + hash2) % lister.length] = tombstone;
            }
        
            
        }*/
        
    }
    public Record[] halfFull() {
        Record[] newList = new Record[lister.length*2];
        for (int i = 0; i < lister.length; i++) {
            newList[i] = lister[i];
        }
        return newList;
    }

}
    