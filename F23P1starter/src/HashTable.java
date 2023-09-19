public class HashTable {
    private Record[] lister;
    private int counter = 0;

    private Record[] halfFull() {
        Record[] newList = new Record[lister.length * 2];
        for (int i = 0; i < lister.length; i++) {
            newList[i] = lister[i];
        }
        return newList;
    }


    /**
     * Initializes the HashTable object
     * 
     * @param size:
     *            size of hash table
     */
    public HashTable(int size) {
        lister = new Record[size];

    }


    /**
     * Gets the size of hash table
     * 
     * @return: size of hash table
     */
    public int getSize() {
        return lister.length;
    }


    /**
     * Insert method for hash table
     * 
     * @param r:
     *            record to be inserted
     */
    public void hashInsert(Record r) {
        if (counter == lister.length / 2) {
            lister = halfFull();
        }
        int homeSlot = 0;

        int id = r.getID();
        // ((k/M ) mod (M/2)) ∗ 2) + 1
        homeSlot = id % lister.length;
        if (lister[homeSlot] == null || lister[homeSlot].getID() == -1) {
            lister[homeSlot] = r;
        }
        else {
            while (lister[homeSlot] != null) {
                int hash2 = (((id / lister.length) % (lister.length / 2)) * 2)
                    + 1;
                if (lister[(homeSlot + hash2) % lister.length] == null
                    || lister[(homeSlot + hash2) % lister.length]
                        .getID() == -1) {
                    lister[(homeSlot + hash2) % lister.length] = r;
                    break;

                }
                else {
                    homeSlot = (homeSlot + hash2) % lister.length;

                }

            }
        }
        counter++;
    }


    /**
     * else {
     * System.out.println("Hash Insert of Record " + r.getID() + " Failed.");
     * }
     * 
     * }
     * //}
     * counter += 1;
     * 
     * }
     * 
     * /**
     * Search method for hash table
     * 
     * @param id:
     *            record's id number
     * @return the record that matches the given ID
     */
    public Record search(int id) {
        int homeSlot = 0;
        // if (counter ==0) {
        // return null;
        // }
        homeSlot = id % lister.length;
        if (lister[homeSlot] == null) {
            return null;
        }
        if (lister[homeSlot].getID() == id) {
            return lister[homeSlot];

        }

        while (lister[homeSlot] != null) {
            int hash2 = (((id / lister.length) % (lister.length / 2)) * 2) + 1;

            if (lister[(homeSlot + hash2) % lister.length] == null) {
                return null;
            }
            else if (lister[(homeSlot + hash2) % lister.length].getID() == id) {
                return lister[(homeSlot + hash2) % lister.length];
            }
            homeSlot = (homeSlot + hash2) % lister.length;

        }
        return null;

    }


    /**
     * Delete method for hash table
     * 
     * @param Id
     *            value to be updated
     * @return the record that matches the given ID
     */
    public String delete(int id) {
        if (counter == 0) {
            return "Hash Table is empty";
        }
        // int id = key.getID();
        if (this.search(id) == null) {
            return "Record with ID " + id + " not found.";
        }
        Record deleter = this.search(id);

        int homeSlot = id % lister.length;
        if (lister[homeSlot].getID() == id) {
            lister[homeSlot].setID(-1);
            ;
            counter--;
            return "Record with ID " + id + " has been deleted";

        }
        while (lister[homeSlot] != null) {
            int hash2 = (((id / lister.length) % (lister.length / 2)) * 2) + 1;

            // if (lister[(homeSlot + hash2) % lister.length] == null) {
            // return "Record with ID " + id + " not found.";
            // }
            if (lister[(homeSlot + hash2) % lister.length].getID() == id) {
                lister[(homeSlot + hash2) % lister.length].setID(-1);
                return "Record with ID " + id + " has been deleted";
            }
            homeSlot = (homeSlot + hash2) % lister.length;

        }

        /**
         * if (deleter != null) {
         * deleter.setID(-1);
         * lister[homeSlot] = deleter;
         * return "Record with ID " + id + " has been deleted";
         * }
         * else {
         * return "Record with ID " + id + " not found.";
         * }
         * /*
         * while(lister[homeSlot] != tombstone) {
         * int hash2 = (((id / lister.length ) % (lister.length / 2)) * 2) + 1;
         * if(lister[(homeSlot + hash2) % lister.length].getID() == id) {
         * lister[(homeSlot + hash2) % lister.length] = tombstone;
         * }
         * 
         * 
         * }
         */
        return null;

    }


    /**
     * Tostring is a function to print out the contents of the hashtable
     * 
     * @return String that represents the function
     */
    public String toString() {
        String retString = "Hashtable:";
        for (int i = 0; i < lister.length; i++) {

            if (lister[i] != null) {
                if (lister[i].getID() != -1) {

                    retString += "\n" + i + ": " + lister[i].getID();
                }
                // else if (lister[i].getID() == -1) {
                else {
                    retString += "\n" + i + ": TOMBSTONE";
                }
            }

        }
        retString += "\n" + "total records: " + counter;
        return retString;
    }

}
