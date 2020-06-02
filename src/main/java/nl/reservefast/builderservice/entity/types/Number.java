package nl.reservefast.builderservice.entity.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import nl.reservefast.builderservice.entity.Form;
import nl.reservefast.builderservice.entity.Row;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Number extends BaseInput implements Serializable {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="row_id", nullable=false)
    private Row row;

    private String label;

    private int max;

    private int min;

    private String placeholder;

    public Number() {}

    public Number(Row row) {
        this.row = row;
    }

}