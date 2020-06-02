package nl.reservefast.builderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter @Setter
public class Row extends BaseEntity implements Serializable {

    @NotNull
    private int priority;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="form_id", nullable=false)
    private Form form;

    public Row() {}

    public Row(int priority, Form form) {
        this.priority = priority;
        this.form = form;
    }
}
