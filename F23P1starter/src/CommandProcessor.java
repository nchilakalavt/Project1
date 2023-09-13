import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommandProcessor {
    private int id;
    private String title;
    private String date; // Seminar date
    private int length; // Seminar length
    private String[] keywords; // Seminar keywords
    private short x; // Seminar x coord
    private short y; // Seminar y coord
    private String desc; // Seminar description
    private int cost; // Seminar cost
    private String file;
    private HashTable hash;
    private MemManager mem;
    //String filename;
    
    /**
     * Loads the CommandProcessor object 
     *  String for filename
     
    public CommandProcessor(String s) {
        filename = s;
    }
    **/
    
    /**
     * Scans the input file 
     * @param String for filename
     */
    public CommandProcessor(int memManagerSize, int hashTableSize, String inputFile) {
       mem = new MemManager(memManagerSize);
       hash = new HashTable(hashTableSize);
       this.file = inputFile;
    }
    /**
     * Checks if command is insert, search, or delete
     * @param File object for filename
     */
    public void processor() {
  
            String filePath = file;
            try {
                // Read the file into a byte array
                byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

                // Convert the byte array to a String using UTF-8 encoding (or your desired encoding)
                String fileContent = new String(fileBytes, StandardCharsets.UTF_8);

                // Now, 'fileContent' contains the contents of the text file as a String
                
                String[] lines = fileContent.split("\\r?\\n");
                String[] line;
                int i = 0;
                while (i<lines.length) {
                    line = lines[i].split(" ");
                    if (line[0].equals("insert")){
                        String[] insertLines = new String[5];
                        for (int j = i; j < i+5; j++) {
                            insertLines[j] = lines[j];
                        }
                        
                        Record r = new Record(commandProcessorInsert(insertLines), 0);
                        hash.hashInsert(r);
                        
                        i+=5;
                    }
                    else if (line[0].equals("print")) {
                        if(line[1].equals("hashTable")) {
                            
                            i++;
                        }
                        else {
                            i++;
                        }
                    }
                    else if (line[0].equals("delete")) {
                        i++;
                    }
                    else if (line[0].equals("search")) {
                        i++;
                    }
                    else {
                        i++;
                    }
                }
            }
            
            catch (IOException e){
                e.printStackTrace();
            }
        
        /**String lineOne = File.nextLine();
        System.out.println(lineOne);
        String[] command = lineOne.split(" ");
        if (command[0].equals("insert")) {
            return commandProcessorInsert(file);
        }
        else if (command[0].equals("delete")) {
            return commandProcessorDelete(file);
        }
        
        else if (command[0].equals("search")) {
            return commandProcessorSearch(file);
        }
        else {
            System.out.println("Error processing commands");
            return null;
        }**/
    }
    /**
     * Insert command for command processor
     * @param File inputfile
     */
    public Seminar commandProcessorInsert(String[] lines) {
        String lineOne = File.nextLine();
        String[] valueLineOne = lineOne.split(" ");
        this.id = Integer.parseInt(valueLineOne[1]); //Setting ID
        this.title = File.nextLine();
        String lineThree = File.nextLine();
        String[] valuesLineThree = lineThree.split(" ");
        this.date = valuesLineThree[0];
        this.length = Integer.parseInt(valuesLineThree[1]);
        this.x = Short.parseShort(valuesLineThree[2]);
        this.y = Short.parseShort(valuesLineThree[3]);
        this.cost = Integer.parseInt(valuesLineThree[4]);
        String lineFour = File.nextLine();
        this.keywords = lineFour.split(" ");
        this.desc = File.nextLine();
        
        Seminar retSem = new Seminar(this.id, this.title, this.date, this.length, this.x,
        this.y, this.cost, this.keywords, this.desc);
        return retSem;
    }
    
    /**public String commandProcessorInsert(File File) {
        String lineOne = File.nextLine();
        String[] valueLineOne = lineOne.split(" ");
        String id = valueLineOne[1]; //Setting ID
        String title = File.nextLine();
        String lineThree = File.nextLine();
        String[] valuesLineThree = lineThree.split(" ");
        String date = valuesLineThree[0];
        String length = valuesLineThree[1];
        String x = valuesLineThree[2];
        String y = valuesLineThree[3];
        String cost = valuesLineThree[4];
        String lineFour = File.nextLine();
        String[] keywords = lineFour.split(" ");
        String desc = File.nextLine();
        return id + ", " + title + ", " + date + ", " + 
            length + ", " + x + ", " + y + ", " + cost + ", " + keywords + ", " + desc;
    }**/
    
    /**
     * Delete command for command processor
     * @param File inputfile
     */
    public String commandProcessorDelete(int File) {
        return null;
    }
    /**
     * Search command for command processor
     * @param File inputfile
     */
    public String commandProcessorSearch(int File) {
        return null;
    }
    
    public String getFile() {
        return this.file;
    }
    
}

