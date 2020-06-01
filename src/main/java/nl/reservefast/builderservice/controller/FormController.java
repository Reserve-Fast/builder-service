package nl.reservefast.builderservice.controller;

import nl.reservefast.builderservice.client.CompanyClient;
import nl.reservefast.builderservice.controller.enums.FormResponse;
import nl.reservefast.builderservice.entity.Form;
import nl.reservefast.builderservice.logic.FormLogic;
import nl.reservefast.builderservice.model.Company;
import nl.reservefast.builderservice.model.dto.CreateFormDTO;
import nl.reservefast.builderservice.model.dto.UpdateFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(value = "/form")
public class FormController {
    private final FormLogic formLogic;
    private final CompanyClient companyClient;

    @Autowired
    public FormController(CompanyClient companyClient, FormLogic formLogic) {
        this.companyClient = companyClient;
        this.formLogic = formLogic;
    }

    @PostMapping
    public ResponseEntity createForm(@RequestBody CreateFormDTO dto) {
        Company company = this.companyClient.getCompany(dto.getCompanyId());

        if(company != null) {
            Form form = this.formLogic.createForm(UUID.fromString(dto.getCompanyId()), dto.getFormName());

            if(form != null) {
                return ok(form);
            }

            return status(HttpStatus.BAD_REQUEST).body(FormResponse.UNEXPECTED_ERROR.toString());
        }

        return status(HttpStatus.BAD_REQUEST).body(FormResponse.NO_COMPANY.toString());
    }

    @GetMapping(value = "/{id}/company")
    public ResponseEntity getForms(@PathVariable String id) {
        Company company = this.companyClient.getCompany(id);

        if(company != null) {
            List<Form> forms = this.formLogic.getForms(UUID.fromString(id));

            if(forms.isEmpty()) {
                return status(HttpStatus.BAD_REQUEST).body(FormResponse.NO_FORMS.toString());
            }

            return ok(forms);
        }

        return status(HttpStatus.BAD_REQUEST).body(FormResponse.NO_COMPANY.toString());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getForm(@PathVariable String id) {
        Optional<Form> form = this.formLogic.getForm(UUID.fromString(id));

        if(form.isPresent()) {
            return ok(form.get());
        }

        return status(HttpStatus.BAD_REQUEST).body(FormResponse.NO_FORM.toString());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateForm(@PathVariable String id, @RequestBody @Valid UpdateFormDTO dto) {
        Form form = this.formLogic.updateForm(UUID.fromString(id), dto);

        if(form != null) {
            return ok(form);
        }

        return status(HttpStatus.BAD_REQUEST).body(FormResponse.UNEXPECTED_ERROR.toString());
    }
}
