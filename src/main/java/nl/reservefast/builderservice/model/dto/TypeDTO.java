package nl.reservefast.builderservice.model.dto;

import lombok.Data;
import nl.reservefast.builderservice.entity.types.Date;
import nl.reservefast.builderservice.entity.types.Email;
import nl.reservefast.builderservice.entity.types.Input;
import nl.reservefast.builderservice.entity.types.Number;

import java.util.List;

@Data
public class TypeDTO {
    private List<Date> dates;
    private List<Email> emails;
    private List<Input> inputs;
    private List<Number> numbers;
}
