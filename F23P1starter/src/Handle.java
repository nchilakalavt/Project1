
public class Handle {
    private int startPos;
    private int endPos;


    public Handle(int startPos, int endPos) {
       /** try {
            length = s.
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        this.startPos = startPos;
        this.endPos = endPos;
    }
    public int getStartPos() {
        return this.startPos;
    }

    
    public int getEndPos() {
        return this.endPos;
    }
    
    public int getLength() {
        return startPos - endPos;
    }

    

}
