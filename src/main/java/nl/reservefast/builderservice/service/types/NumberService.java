package nl.reservefast.builderservice.service.types;

import nl.reservefast.builderservice.entity.types.Number;
import nl.reservefast.builderservice.repository.types.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberService {
    private final NumberRepository numberRepository;

    @Autowired
    public NumberService(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    public Number createOrUpdate(Number email) {
        return this.numberRepository.save(email);
    }
}
