package nl.reservefast.builderservice.model.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class UpdateFormDTO {

    private String formName;

    @SuppressWarnings("unchecked")
    private List<TypeDTO> rows;

}
