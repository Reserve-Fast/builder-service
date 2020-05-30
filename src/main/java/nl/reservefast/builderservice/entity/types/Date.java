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
public class Date extends BaseInput implements Serializable {

    @OneToOne
    private Row row;

    private String label;

    private Date inputDate;

    public Date() {}

    public Date(Row row, Date inputDate) {
        this.row = row;
        this.inputDate = inputDate;
    }

}