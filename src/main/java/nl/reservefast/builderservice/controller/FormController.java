package nl.reservefast.builderservice.controller;

import nl.reservefast.builderservice.client.CompanyClient;
import nl.reservefast.builderservice.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/form")
public class FormController {
    private final CompanyClient companyClient;

    @Autowired
    public FormController(CompanyClient companyClient) {
        this.companyClient = companyClient;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getForm(@PathVariable String id) {
        Company company = this.companyClient.getCompany(id);

        return ResponseEntity.ok(company);
    }
}
