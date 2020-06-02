package nl.reservefast.builderservice.entity.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import nl.reservefast.builderservice.entity.Row;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Input extends BaseInput implements Serializable {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="row_id", nullable=false)
    private Row row;

    private String label;

    private String placeholder;

    public Input() {}

    public Input(Row row) {
        this.row = row;
    }
}