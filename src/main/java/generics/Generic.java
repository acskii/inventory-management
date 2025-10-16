package generics;

// Andrew :)

/*
    Any object class, other than roles and databases, is considered a generic
    Therefore it should implement the methods in this interface

    Use this interface as such:
        public class Product implements Generic {
            // other code here

            @Override
            public String lineRepresentation() { ... }

            @Override
            public String getSearchKey() { ... }
        }
*/

public interface Generic {
    /* Method to return all data of implementation comma-separated */
    String lineRepresentation();

    /* Method to return a string that represents implementation ID */
    String getSearchKey();
}
