public class Record {
    public int id;
    public Seminar S;

    public Record(Seminar s) {
           S = s;
          id = s.getID();
    }

    public int getID() {
        // TODO Auto-generated method stub
        return this.id;
    }
    
    public Seminar getSeminar() {
        return S;
    }
    
    public void setID(int idToSet) {
        id = idToSet;
    }
    
    //getSeminar
    //getiD 
    
}