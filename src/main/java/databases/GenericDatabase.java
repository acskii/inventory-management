
package databases;

import java.io.*;
import java.util.*;
import generics.Generic;
/*
  writers:
   Abdoalrahman khedr
   Andrew Sameh
*/
public abstract class GenericDatabase<T extends Generic> extends Database<T> {
    
    public GenericDatabase(String filename) {
        super(filename);
    }
    
    @Override
    public void readFile() {
        File file = new File(this.filename);

        if (!file.exists() || !file.getName().endsWith(".txt")) {
            System.out.println(getClass().getName() +": Unable to read file!");
            return;
        }
        
        try (Scanner reader = new Scanner(file)) {
            // Create Scanner resource and cleanup

            // Reset records before reading
            this.records.clear();
            // As long as the file has lines,
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                T user = createRecordFrom(line);
                insertRecord(user);
            }
            System.out.println(getClass().getName() +": successfully read all records!");
        } catch (FileNotFoundException err) {
            System.out.println(getClass().getName() +": File was not found");
        }
    }
    @Override
    public void saveToFile(){
        File file=new File(this.filename);
        try {
            if (!file.exists() && file.getName().endsWith(".txt")) {
                if (file.createNewFile()) System.out.println(getClass().getName() +": Successfully created new file!");
                else {
                    System.out.println(getClass().getName() +": Failed to create new file");
                    return;
                }
            }
        } 
        catch (IOException err) {
            System.out.println(getClass().getName() +": Failed to create new file");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename))) {
            // Create BufferedWriter resource and cleanup

            StringBuilder sb = new StringBuilder();
            // Build a string from all record line representations
            // and write that to file
            for (T record : this.records) {
                sb.append(record.lineRepresentation()).append("\n");
            }
            writer.write(sb.toString());
            System.out.println(getClass().getName() +":  Successfully wrote to file!");
        } catch (IOException err) {
            System.out.println(getClass().getName() +":  File can not be accessed, perhaps it is being used by another program..");
        }
    }
    @Override
    public boolean contains(String key){
        //iterates over records and returns true if any record have this key value
        for(T record:records){
            if(record.getSearchKey().equals(key)){
                return true;
            }
        }
        return false;
    }
    @Override
    public T getRecord(String key){
        //iterates over records and return the record of this key value aotherwise return null
        for(T record:records){
            if(record.getSearchKey().equals(key)){
                return record;
            }
        }
        return null;
    }
    @Override
    public void deleteRecord(String key){
        //iterates over records and delete the record who has this key
        this.records.removeIf((record) -> record.getSearchKey().equals(key));
    }
}