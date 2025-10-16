package roles;

// Andrew :)

/*
    Any role should implement the methods in this interface

    Use this interface as such:
        public class AdminRole implements Role {
            // other code here

            @Override
            public void logout() { ... }
        }
*/

public interface Role {

    /* Method to write all unsaved data to file */
    void logout();
}
