package nl.reservefast.builderservice.repository;

import nl.reservefast.builderservice.entity.Row;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RowRepository extends JpaRepository<Row, UUID> {
}
