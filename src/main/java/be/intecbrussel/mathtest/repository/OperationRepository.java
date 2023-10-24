package be.intecbrussel.mathtest.repository;

import be.intecbrussel.mathtest.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("SELECT o FROM Operation o ORDER BY o.id DESC")
    List<Operation> findTopNOperations(@Param("n") int n);
}
