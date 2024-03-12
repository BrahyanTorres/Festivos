package comapirest.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comapirest.demo.Entites.Festivo;

@Repository
public interface FestivoRepository extends JpaRepository<Festivo, Long> {
}