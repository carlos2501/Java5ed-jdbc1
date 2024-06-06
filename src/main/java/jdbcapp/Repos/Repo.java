package jdbcapp.Repos;

import java.util.List;
import java.util.Optional;

public interface Repo<T> {

    List<T> findAll();
    Optional<T> findBy(Integer id);
    Optional<T> findByCadena(String id);
    Optional<T> save(T cli);
    void borrar(Integer id);
}
