package nl.reservefast.builderservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter @Setter
public class Row extends BaseEntity implements Serializable {

    @NotNull
    private int priority;

    @ManyToOne
    @JoinColumn(name="form_id", nullable=false)
    private Form form;

    public Row() {}

    public Row(int priority) {
        this.priority = priority;
    }
}
