package fr.uga.l3miage.example.repository;

import java.util.List;

/**
 * Base interface to having consistent naming.
 * @param <I>
 * @param <E>
 */
public interface CRUDRepository<I,E> {
    E save(E entity);

    E get(I id);

    void delete(E entity);

    List<E> all();
}