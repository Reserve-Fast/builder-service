package nl.reservefast.builderservice.entity.types;

import lombok.Getter;
import lombok.Setter;
import nl.reservefast.builderservice.entity.Row;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Email extends BaseInput implements Serializable {

    @OneToOne
    private Row row;

    private String label;

    @javax.validation.constraints.Email
    private String email;

    public Email() {}

    public Email(Row row, String email) {
        this.row = row;
        this.email = email;
    }

}