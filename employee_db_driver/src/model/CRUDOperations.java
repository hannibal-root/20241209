package model;

public interface CRUDOperations {
    // Create a new entry
    void create(String id, String fullName, String email, String userName, String mac);

    // Read all entries
   void  readAll();

    // Update an entry by id
    void update(String id, String fullName, String email, String userName, String mac);

    // Delete an entry by id
    void delete(String id);

    // Find an entry by id_
    int findById(String id_);
}
