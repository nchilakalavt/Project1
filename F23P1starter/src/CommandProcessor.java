import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.util.Scanner;
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
    private SemDatabase semdata;
    //String filename;



       /**
        * @param args
        */
       
       
       public void beginParsing(String filename) {
          try {
             SemDatabase semdata = new SemDatabase(hash, mem);
             Scanner sc = new Scanner(new File(filename));//Create our new scanner
             while(sc.hasNext()) {//While the scanner has information to read
                String cmd = sc.next();//Read the next te
                switch(cmd) {
                   case "insert" :
                       id = sc.nextInt();
                       sc.nextLine();
                       title = sc.nextLine();
                       date = sc.next();
                       length = sc.nextInt();
                       x =sc.nextShort();
                       y = sc.nextShort();
                       cost = sc.nextInt();
                       sc.nextLine();
                       String key = sc.nextLine();
                       keywords = key.split(" "); 
                       desc = sc.nextLine();
                       Seminar sem = new Seminar(id, title, date , length, x, y, cost, keywords, desc);
                       semdata.insert(sem);
                       
                       
                   break;
                   case "print" ://Found an add command
                     if(sc.next().equals("hashtable")) {
                      semdata.printHash();
                     }
                     else if(sc.next().equals("blocks")) {
                         semdata.printBlock();
                     }
                     else {
                         break;
                     }
                   break;
                   case "delete" ://Found a delete command
                      id = sc.nextInt();
                      semdata.delete(id);
                   break;
                   case "search" ://Found a search command
                       id = sc.nextInt();
                       semdata.searcher(id);
                       
                   break;
                   default ://Found an unrecognized command
                      System.out.println("Unrecognized input "+cmd);
                   break;
                }
             }
          } catch (Exception e) {
             e.printStackTrace();
          }
       

    
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
    /*
    public CommandProcessor(int memManagerSize, int hashTableSize, String inputFile) {
       mem = new MemManager(memManagerSize);
       hash = new HashTable(hashTableSize);
       this.file = inputFile;
    }
    /**
     * Checks if command is insert, search, or delete
     * @param File object for filename
     
     *
     */
    /**
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
                        
                        Record r = new 
                            Record(commandProcessorInsert(insertLines), mem.insert(commandProcessorInsert(insertLines).serialize()));
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
   /** public Seminar commandProcessorInsert(String[] lines) {
        String[] valueLineOne = lines[0].split(" ");
        this.id = Integer.parseInt(valueLineOne[1]); //Setting ID
        this.title = lines[1];
        String[] valuesLineThree = lines[2].split(" ");
        this.date = valuesLineThree[0];
        this.length = Integer.parseInt(valuesLineThree[1]);
        this.x = Short.parseShort(valuesLineThree[2]);
        this.y = Short.parseShort(valuesLineThree[3]);
        this.cost = Integer.parseInt(valuesLineThree[4]);
        this.keywords = lines[3].split(" ");
        this.desc = lines[4];
        
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
   // public String commandProcessorDelete(int File) {
   //     return null;
   // }
   // /**
    // * Search command for command processor
   //  * @param File inputfile
    // */
   // public String commandProcessorSearch(int File) {
   //     return null;
  //  }
    
   // public String getFile() {
   //     return this.file;
   // }
    
}
//    
//    public String commandProcessorInsert(File File) {
//        String lineOne = File.nextLine();
//        String[] valueLineOne = lineOne.split(" ");
//        String id = valueLineOne[1]; //Setting ID
//        String title = File.nextLine();
//        String lineThree = File.nextLine();
//        String[] valuesLineThree = lineThree.split(" ");
//        String date = valuesLineThree[0];
//        String length = valuesLineThree[1];
//        String x = valuesLineThree[2];
//        String y = valuesLineThree[3];
//        String cost = valuesLineThree[4];
//        String lineFour = File.nextLine();
//        String[] keywords = lineFour.split(" ");
//        String desc = File.nextLine();
//        return id + ", " + title + ", " + date + ", " + 
//            length + ", " + x + ", " + y + ", " + cost + ", " + keywords + ", " + desc;
//    }
//    
//    /**
//     * Delete command for command processor
//     * @param File inputfile
//     */
//    public String commandProcessorDelete(File File) {
//        return null;
//    }
//    /**
//     * Search command for command processor
//     * @param File inputfile
//     */
//    public String commandProcessorSearch(File File) {
//        return null;
//    }
//    
//    public String getFile() {
//        return this.file;
//    }*/
//    

//}

