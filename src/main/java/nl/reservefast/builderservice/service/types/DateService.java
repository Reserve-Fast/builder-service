package nl.reservefast.builderservice.service.types;

import com.netflix.discovery.converters.Auto;
import nl.reservefast.builderservice.entity.types.Date;
import nl.reservefast.builderservice.repository.types.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DateService {
    private final DateRepository dateRepository;

    @Autowired
    public DateService(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    public Date createOrUpdate(Date date) {
        return this.dateRepository.save(date);
    }
}
