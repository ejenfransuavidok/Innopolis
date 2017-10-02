package libraryportal.repository;

import libraryportal.request.Request;

import java.util.List;

public class RepositoryCommon implements Repository {
    @Override
    public List findEntities(Request request) {
        return null;
    }

    @Override
    public Object findEntity(Request request) {
        return null;
    }

    @Override
    public boolean writeEntities(List entities) {
        return false;
    }

    @Override
    public boolean writeEntity(Object entity) {
        return false;
    }
}
