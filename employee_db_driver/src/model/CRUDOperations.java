package model;

public interface CRUDOperations {
    // Create a new entry
    public void create(String id_, String fullName_, String email_, String userName_, String mac_);

    // Read all entries
   void  readAll();

    // Update an entry by id
    void update(String id_, String fullName_, String email_, String userName_, String mac_);

    // Delete an entry by id
    void delete(String id_);

    // Find an entry by id_
    int findById(String id_);

}
