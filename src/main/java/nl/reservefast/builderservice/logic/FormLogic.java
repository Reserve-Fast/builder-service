package nl.reservefast.builderservice.logic;

import nl.reservefast.builderservice.entity.Form;
import nl.reservefast.builderservice.entity.Row;
import nl.reservefast.builderservice.entity.types.Date;
import nl.reservefast.builderservice.entity.types.Email;
import nl.reservefast.builderservice.entity.types.Input;
import nl.reservefast.builderservice.entity.types.Number;
import nl.reservefast.builderservice.model.dto.TypeDTO;
import nl.reservefast.builderservice.model.dto.UpdateFormDTO;
import nl.reservefast.builderservice.service.FormService;
import nl.reservefast.builderservice.service.types.DateService;
import nl.reservefast.builderservice.service.types.EmailService;
import nl.reservefast.builderservice.service.types.InputService;
import nl.reservefast.builderservice.service.types.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class FormLogic {
    private final FormService formService;
    private final DateService dateService;
    private final EmailService emailService;
    private final InputService inputService;
    private final NumberService numberService;

    @Autowired
    public FormLogic(FormService formService, DateService dateService, EmailService emailService, InputService inputService, NumberService numberService) {
        this.formService = formService;
        this.dateService = dateService;
        this.emailService = emailService;
        this.inputService = inputService;
        this.numberService = numberService;
    }

    public Form createForm(UUID companyId, String formName) {
        Form form = new Form();

        form.setCompanyId(companyId);
        form.setName(formName);

        return this.formService.createOrUpdate(form);
    }

    public List<Form> getForms(UUID companyId) {
        return this.formService.findAllByCompanyId(companyId);
    }

    public Optional<Form> getForm(UUID formId) {
        return this.formService.findById(formId);
    }

    public Form updateForm(UUID formId, UpdateFormDTO dto) {
        Optional<Form> form = this.formService.findById(formId);

        if(form.isEmpty()) {
            return null;
        }

        if(!form.get().getName().equals(dto.getFormName())) {
            form.get().setName(dto.getFormName());
        }

        if(!dto.getRows().isEmpty()) {
            form.get().getRows().clear();

            for(Map.Entry<Integer, TypeDTO> entry : dto.getRows().entrySet()) {
                Row row = new Row(entry.getKey());
                form.get().addRow(row);

                if(!updateValues(entry.getValue(), row)) {
                    return null;
                }
            }
        }

        return this.formService.createOrUpdate(form.get());
    }

    private boolean updateValues(TypeDTO dto, Row row) {
        if(!dto.getDates().isEmpty()) {
            return this.updateDates(dto.getDates(), row);
        }

        if(!dto.getEmails().isEmpty()) {
            return this.updateEmails(dto.getEmails(), row);
        }

        if(!dto.getInputs().isEmpty()) {
            return this.updateInputs(dto.getInputs(), row);
        }

        if(!dto.getNumbers().isEmpty()) {
            return this.updateNumbers(dto.getNumbers(), row);
        }

        return true;
    }

    private boolean updateDates(List<Date> dates, Row row) {
        for(Date date : dates) {
            date.setRow(row);

            if(this.dateService.createOrUpdate(date) == null) {
                return false;
            }
        }

        return true;
    }

    private boolean updateEmails(List<Email> emails, Row row) {
        return false;
    }

    private boolean updateInputs(List<Input> inputs, Row row) {
        return false;
    }

    private boolean updateNumbers(List<Number> numbers, Row row) {
        return false;
    }
}
