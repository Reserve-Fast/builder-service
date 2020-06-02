package nl.reservefast.builderservice.service.types;

import nl.reservefast.builderservice.entity.types.Input;
import nl.reservefast.builderservice.repository.types.InputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputService {
    private final InputRepository inputRepository;

    @Autowired
    public InputService(InputRepository inputRepository) {
        this.inputRepository = inputRepository;
    }

    public Input createOrUpdate(Input email) {
        return this.inputRepository.save(email);
    }
}
