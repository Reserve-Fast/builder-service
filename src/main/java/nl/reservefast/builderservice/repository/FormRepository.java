package nl.reservefast.builderservice.repository;

import nl.reservefast.builderservice.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<Form, UUID> {
}
