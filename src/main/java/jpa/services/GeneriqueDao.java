package jpa.services;


import java.util.List;

public interface GeneriqueDao<T, ID> {
    T create(T entite);

    T update(ID id, T entite);

    T getById(ID id);

    List<T> getAll();

    void delete(ID id);

    long count();
}
