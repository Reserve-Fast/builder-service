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
public class Number extends BaseInput implements Serializable {

    @OneToOne
    private Row row;

    private String label;

    private int max;

    private int min;

    public Number() {}

    public Number(Row row, String text) {
        this.row = row;
    }

}