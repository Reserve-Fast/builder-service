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
import nl.reservefast.builderservice.service.RowService;
import nl.reservefast.builderservice.service.types.DateService;
import nl.reservefast.builderservice.service.types.EmailService;
import nl.reservefast.builderservice.service.types.InputService;
import nl.reservefast.builderservice.service.types.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FormLogic {
    private final FormService formService;
    private final RowService rowService;
    private final DateService dateService;
    private final EmailService emailService;
    private final InputService inputService;
    private final NumberService numberService;

    @Autowired
    public FormLogic(FormService formService, RowService rowService, DateService dateService, EmailService emailService, InputService inputService, NumberService numberService) {
        this.formService = formService;
        this.rowService = rowService;
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

    public boolean deleteForm(UUID formId) {
        Optional<Form> form = this.formService.findById(formId);

        if(!form.isPresent()) {
            return false;
        }

        this.formService.delete(form.get());
        return true;
    }

    public Form updateForm(UUID formId, UpdateFormDTO dto) {
        Optional<Form> form = this.formService.findById(formId);

        if(!form.isPresent()) {
            return null;
        }

        if(!form.get().getName().equals(dto.getFormName())) {
            form.get().setName(dto.getFormName());
        }

        if(!dto.getRows().isEmpty()) {
            form.get().getRows().clear();

            for(int i = 0; i < dto.getRows().size(); i++) {
                for(Map.Entry<Integer, TypeDTO> entry : dto.getRows().get(i).entrySet()) {

                    // Create row
                    Row row = this.rowService.createOrUpdate(new Row(entry.getKey(), form.get()));

                    form.get().addRow(row);

                    this.updateValues(entry.getValue(), row);

                }
            }
        }

        return this.formService.createOrUpdate(form.get());
    }

    private void updateValues(TypeDTO dto, Row row) {
        if(dto.getDates() != null) {
            this.updateDates(dto.getDates(), row);
        }

        if(dto.getEmails() != null) {
            this.updateEmails(dto.getEmails(), row);
        }

        if(dto.getInputs() != null) {
            this.updateInputs(dto.getInputs(), row);
        }

        if(dto.getNumbers() != null) {
            this.updateNumbers(dto.getNumbers(), row);
        }
    }

    private void updateDates(List<Date> dates, Row row) {
        for(Date date : dates) {
            date.setRow(row);

            Date createdDate = this.dateService.createOrUpdate(date);
            row.addDate(createdDate);
        }
    }

    private void updateEmails(List<Email> emails, Row row) {
        for(Email email : emails) {
            email.setRow(row);

            Email createdEmail = this.emailService.createOrUpdate(email);
            row.addEmail(createdEmail);
        }
    }

    private void updateInputs(List<Input> inputs, Row row) {
        for(Input input : inputs) {
            input.setRow(row);

            Input createdInput = this.inputService.createOrUpdate(input);
            row.addInput(createdInput);
        }
    }

    private void updateNumbers(List<Number> numbers, Row row) {
        for(Number number : numbers) {
            number.setRow(row);

            Number createdNumber = this.numberService.createOrUpdate(number);
            row.addNumber(createdNumber);
        }
    }
}
