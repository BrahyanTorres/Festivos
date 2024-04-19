package comapirest.demo.core.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comapirest.demo.entidades.Festivos;

@Repository
public interface IFestivoRepositorio extends JpaRepository<Festivos, Long> {

}
