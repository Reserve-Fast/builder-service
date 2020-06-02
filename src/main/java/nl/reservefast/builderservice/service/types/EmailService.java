package nl.reservefast.builderservice.service.types;

import nl.reservefast.builderservice.entity.types.Email;
import nl.reservefast.builderservice.repository.types.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final EmailRepository emailRepository;

    @Autowired
    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public Email createOrUpdate(Email email) {
        return this.emailRepository.save(email);
    }
}
