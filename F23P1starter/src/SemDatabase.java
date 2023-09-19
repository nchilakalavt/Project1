public class SemDatabase {
    private HashTable hash;
    private MemManager m;

    public SemDatabase(int hashTableSize, int poolSize) {
        hash = new HashTable(hashTableSize);
        m = new MemManager(poolSize);

    }


    public void delete(int key) {
        if (hash.search(key) != null) {
            Handle h = hash.search(key).getHandle();
            hash.delete(key);

            m.remove(h);
            System.out.println("Record with ID " + key
                + " successfully deleted from the database");
        }
        else {
            System.out.println("Delete FAILED - "
                + "There is no record with ID " + key);
        }
    }


    public void insert(Seminar s) throws Exception {
        if (hash.search(s.getID()) == null) {
            Handle h = m.insert(s.serialize(), s.serialize().length);
            hash.hashInsert(new Record(h, s.getID()));
            System.out.println("Successfully inserted record with ID " + s
                .getID());
            System.out.println(s.toString());
            int length = s.serialize().length;
            System.out.println("Size: " + length);
        }
        else {
            System.out.println("Insert FAILED - "
                + "There is already a record with ID " + s.getID());
        }
        // first check in the hash table

    }


    public void search(int ID) {
        // Record r = hash.search(ID);
        if (hash.search(ID) != null) {
            Record r = hash.search(ID);
            try {
                Seminar s = Seminar.deserialize(m.get(r.getHandle()));
                System.out.println("Found record with ID " + ID + ":");
                System.out.println(s.toString());
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        else {
            System.out.println("Search FAILED -- there is no record with ID "
                + ID);
        }

    }


    public void printHash() {
        System.out.println(hash.toString());
    }


    public void printFreeBlock() {
        m.dump();
    }

}
