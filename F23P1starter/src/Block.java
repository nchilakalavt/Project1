
public class Block {
    private int start;
    private int end;
    
    public Block(int startPos, int endPos) {
        start = startPos;
        end = endPos;
    }
    
    public int getLength() {
        return end - start;
    }
    
    public int getStart() {
        return start;
    }
    
    public int getEnd() {
        return end;
    }
    
    public void setStart(int startPos) {
        start = startPos;
    }
    
    public void setEnd(int endPos) {
        end = endPos;
    }
    
}
