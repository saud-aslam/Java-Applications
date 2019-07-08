package ca.jrvs.apps.twitter.dao;

public interface CrdRepo<T, ID> {
    T create(T entity);

    T findbyId(ID id);

    T deletebyId(ID id);
}
