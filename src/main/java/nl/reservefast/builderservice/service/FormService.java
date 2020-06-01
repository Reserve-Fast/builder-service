package nl.reservefast.builderservice.service;

import com.netflix.discovery.converters.Auto;
import nl.reservefast.builderservice.entity.Form;
import nl.reservefast.builderservice.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FormService {

    private final FormRepository formRepository;

    @Autowired
    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public Form createOrUpdate(Form form) {
        return this.formRepository.save(form);
    }

    public List<Form> findAllByCompanyId(UUID companyId) {
        return this.formRepository.findAllByCompanyId(companyId);
    }

    public Optional<Form> findById(UUID formId) {
        return this.formRepository.findById(formId);
    }
}
