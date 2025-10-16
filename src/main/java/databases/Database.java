package databases;

import java.util.ArrayList;
import java.util.List;

// Andrew :)

/*
    This abstract class uses a generic type T, where each implementation can
    provide its own type to use within the methods, for example:

    A ProductDatabase needs to use Product class in its records, then simply
    declare the class as such:
        public class ProductDatabase extends Database<Product> { ... }

    and implement all the abstract methods using @Override
    for example:
        @Override
        public void readFile() { ... }

    Make sure to include a constructor as such using super:
        public ProductDatabase(String filename) {
            super(filename);
            // any other code place here
        }
*/

public abstract class Database<T> {
    /* Members */
    protected List<T> records = new ArrayList<>();
    protected final String filename;

    /* Constructor */
    public Database(String filename) {
        this.filename = filename;
    }

    public List<T> returnAllRecords() {
        // Simply returns the list
        return this.records;
    }

    public void insertRecord(T record) {
        // Add a given record to the already created list
        this.records.add(record);
    }

    /* Abstract methods below need to be implemented */

    /* Methods for record */
    public abstract void deleteRecord(String key);
    public abstract boolean contains(String key);
    public abstract T getRecord(String key);

    /* Method to translate String representation to record */
    public abstract T createRecordFrom(String line);

    /* Method to extract and write records to file */
    public abstract void readFile();
    public abstract void saveToFile();
}
