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
public class Input extends BaseInput implements Serializable {

    @OneToOne
    private Row row;

    private String label;

    private String text;

    public Input() {}

    public Input(Row row, String text) {
        this.row = row;
        this.text = text;
    }

}