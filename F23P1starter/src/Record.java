public class Record {
    public int id;
   public Handle H;
 
    
    public Record(Handle h,int ID) {
     
          id = ID;
          H = h;
    }

    public int getID() {
        return this.id;
    }
    
    /**public Seminar getSeminar() {
        return S;
    }**/
    
    public void setID(int idToSet) {
        id = idToSet;
    }
    
    public Handle getHandle() {
        return H;
    }
    //getSeminar
    //getiD 
   

}