package nl.reservefast.builderservice;

import com.netflix.ribbon.proxy.annotation.Http;
import nl.reservefast.builderservice.client.CompanyClient;
import nl.reservefast.builderservice.controller.FormController;
import nl.reservefast.builderservice.entity.Form;
import nl.reservefast.builderservice.entity.types.Date;
import nl.reservefast.builderservice.logic.FormLogic;
import nl.reservefast.builderservice.model.Company;
import nl.reservefast.builderservice.model.dto.CreateFormDTO;
import nl.reservefast.builderservice.model.dto.TypeDTO;
import nl.reservefast.builderservice.model.dto.UpdateFormDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class BuilderTest {

    @InjectMocks
    private FormController formController;

    @Mock
    private FormLogic formLogic;

    @Mock
    private CompanyClient companyClient;

    private UpdateFormDTO dto = new UpdateFormDTO();

    @BeforeEach
    void setUp() {
        UUID companyId = UUID.fromString("123e4567-e89b-42d3-a456-556642440010");
        UUID formId = UUID.fromString("123e4567-e89b-42d3-a456-556642440020");

        Company company = new Company(companyId, UUID.fromString("123e4567-e89b-42d3-a456-556642440050"), "RensManders", "De gaffel 1", "5384HG", "Heesch", "Netherlands", "info@rensmanders.nl");
        Form form = new Form(companyId, "Rens's form");

        List<Form> forms = new ArrayList<>();
        forms.add(form);

        TypeDTO typeDTO = new TypeDTO();

        Date date = new Date();
        date.setLabel("Reserve date");

        List<Date> dates = new ArrayList<>();
        dates.add(date);
        typeDTO.setDates(dates);

        List<TypeDTO> rows = new ArrayList<>();
        rows.add(typeDTO);

        dto.setRows(rows);

        lenient().when(companyClient.getCompany(companyId.toString())).thenReturn(company);
        lenient().when(formLogic.createForm(companyId, "Rens's form")).thenReturn(form);
        lenient().when(formLogic.deleteForm(formId)).thenReturn(true);
        lenient().when(formLogic.getForms(companyId)).thenReturn(forms);
        lenient().when(formLogic.getForm(formId)).thenReturn(Optional.of(form));
        lenient().when(formLogic.updateForm(formId, dto)).thenReturn(form);
    }

    @Test
    void createFormRightData() {
        CreateFormDTO dto = new CreateFormDTO();
        dto.setCompanyId("123e4567-e89b-42d3-a456-556642440010");
        dto.setFormName("Rens's form");

        HttpStatus status = this.formController.createForm(dto).getStatusCode();
        assertEquals(200, status.value());
    }

    @Test
    void CreateFormWrongData() {
        CreateFormDTO dto = new CreateFormDTO();
        dto.setCompanyId("123e4567-e89b-42d3-a456-556642440060");
        dto.setFormName("test");

        HttpStatus status = this.formController.createForm(dto).getStatusCode();
        assertEquals(400, status.value());
    }

    @Test
    void deleteFormCorrectId() {
        HttpStatus status = this.formController.deleteForm("123e4567-e89b-42d3-a456-556642440020").getStatusCode();
        assertEquals(200, status.value());
    }

    @Test
    void deleteFormIncorrectId() {
        HttpStatus status = this.formController.deleteForm("123e4567-e89b-42d3-a456-556642440030").getStatusCode();
        assertEquals(400, status.value());
    }

    @Test
    void getAllFormsCorrectId() {
        HttpStatus status = this.formController.getForms("123e4567-e89b-42d3-a456-556642440010").getStatusCode();
        assertEquals(200, status.value());
    }

    @Test
    void getAllFormsIncorrectId() {
        HttpStatus status = this.formController.getForms("123e4567-e89b-42d3-a456-556642440060").getStatusCode();
        assertEquals(400, status.value());
    }

    @Test
    void getFormCorrectId() {
        HttpStatus status = this.formController.getForm("123e4567-e89b-42d3-a456-556642440020").getStatusCode();
        assertEquals(200, status.value());
    }

    @Test
    void getFormIncorrectId() {
        HttpStatus status = this.formController.getForm("123e4567-e89b-42d3-a456-556642440060").getStatusCode();
        assertEquals(400, status.value());
    }

    @Test
    void updateFormCorrectData() {
        HttpStatus status = this.formController.updateForm("123e4567-e89b-42d3-a456-556642440020", this.dto).getStatusCode();
        assertEquals(200, status.value());
    }

    @Test
    void updateFormIncorrectData() {
        HttpStatus status = this.formController.updateForm("123e4567-e89b-42d3-a456-556642440050", this.dto).getStatusCode();
        assertEquals(400, status.value());
    }
}
