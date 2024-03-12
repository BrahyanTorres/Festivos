package comapirest.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comapirest.demo.Entites.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {
}