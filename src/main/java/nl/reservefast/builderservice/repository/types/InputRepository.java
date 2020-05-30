package nl.reservefast.builderservice.repository.types;

import nl.reservefast.builderservice.entity.types.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InputRepository extends JpaRepository<Input, UUID> {
}
