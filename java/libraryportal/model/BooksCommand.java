package libraryportal.model;

public class BooksCommand implements Command {
    @Override
    public boolean insertEntity(Object entity) {
        return false;
    }

    @Override
    public boolean updateEntity(Object entity) {
        return false;
    }

    @Override
    public boolean deleteEntity(Object entity) {
        return false;
    }
}
