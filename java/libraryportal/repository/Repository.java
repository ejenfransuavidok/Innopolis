package libraryportal.repository;

import libraryportal.controller.Request;

import java.util.List;

public interface Repository<T> {

    List<T> findEntities(Request request);

    T findEntity(Request request);

    boolean writeEntities(List<T> entities);

    boolean writeEntity(T entity);
}
