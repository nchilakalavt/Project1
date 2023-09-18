public class Record {
    public int id;
   public Handle H;
 
    /**
     * constructor
     * @param h handle object
     * @param ID key value
     */
    public Record(Handle h,int ID) {
     
          id = ID;
          H = h;
    }
/**
 * get method for id
 * @return id/key value
 */
    public int getID() {
        return this.id;
    }
    
  /**
   * setter method to set a new key value to a record
   * @param idToSet new key value to set the record
   */
    public void setID(int idToSet) {
        id = idToSet;
    }
    /**
     * getter method `
     * @return
     */
    public Handle getHandle() {
        return H;
    }
    //getSeminar
    //getiD 
   

}