package libraryportal.model;

public interface Command<T> {

    boolean insertEntity(T entity);

    boolean updateEntity(T entity);

    boolean deleteEntity(T entity);

}
