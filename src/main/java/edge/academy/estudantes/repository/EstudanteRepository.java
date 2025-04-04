package edge.academy.estudantes.repository;

import edge.academy.estudantes.domain.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Integer>, JpaSpecificationExecutor<Estudante> {
}
