package nl.reservefast.builderservice.service;

import nl.reservefast.builderservice.entity.Row;
import nl.reservefast.builderservice.repository.RowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RowService {
    private final RowRepository rowRepository;

    @Autowired
    public RowService(RowRepository rowRepository) {
        this.rowRepository = rowRepository;
    }

    public Row createOrUpdate(Row row) {
        return this.rowRepository.save(row);
    }

    public void delete(Row row) {
        this.rowRepository.delete(row);
    }
}
