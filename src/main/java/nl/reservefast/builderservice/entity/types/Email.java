package nl.reservefast.builderservice.entity.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import nl.reservefast.builderservice.entity.Row;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Email extends BaseInput implements Serializable {

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Row row;

    private String label;

    private String placeholder;

    public Email() {}

    public Email(Row row) {
        this.row = row;
    }
}