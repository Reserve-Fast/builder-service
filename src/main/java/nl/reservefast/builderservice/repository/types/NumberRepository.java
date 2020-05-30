package nl.reservefast.builderservice.repository.types;

import nl.reservefast.builderservice.entity.types.Number;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NumberRepository extends JpaRepository<Number, UUID> {
}
