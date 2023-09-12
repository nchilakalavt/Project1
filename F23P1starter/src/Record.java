public class Record {
    public int id;
    public Seminar S;
    public int length;
    public int pos;
    
    public Record(Seminar s,int p) {
          try {
            length = s.serialize().length;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
          id = s.getID();
          pos = p;
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
    public int getPos() {
        return this.pos;
    }
   

    public int getLength() {
        return this.length;
    }

}