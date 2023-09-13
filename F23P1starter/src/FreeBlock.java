
public class FreeBlock {
    private DLList<Integer> list; 
    int listMaxSize;
    int currentSize;
    
    public FreeBlock(int size) {
        list = new DLList<Integer>();
        int listSize = size;
    }
    
    public void append(int blockSize) {
        int insertIndex = (int)(Math.log(blockSize)/Math.log(2));
        
        if (list.getNodeAtIndex(insertIndex) != null) { //checks if current array spot is empty
            list.add(insertIndex+1, blockSize); 
            if ((list.get(insertIndex) | insertIndex) 
                == (list.get(insertIndex+1) | insertIndex)) {
                int temp = list.get(insertIndex) + list.get(insertIndex+1);
                list.remove(insertIndex);
                list.remove(insertIndex+1);
                append(temp);
                
            }
        }
        else {
            list.add(insertIndex);
        }
    }
    
    public void remove() {
        
    }
    //public sizeOfBlock() {
        
    //}
    
}
