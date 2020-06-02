package nl.reservefast.builderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import nl.reservefast.builderservice.entity.types.Date;
import nl.reservefast.builderservice.entity.types.Email;
import nl.reservefast.builderservice.entity.types.Input;
import nl.reservefast.builderservice.entity.types.Number;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Row extends BaseEntity implements Serializable {

    @NotNull
    private int priority;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="form_id", nullable=false)
    private Form form;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="row", orphanRemoval = true)
    private Set<Date> dates = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="row", orphanRemoval = true)
    private Set<Email> emails = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="row", orphanRemoval = true)
    private Set<Input> inputs = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="row", orphanRemoval = true)
    private Set<Number> numbers = new HashSet<>();

    public Row() {}

    public Row(int priority, Form form) {
        this.priority = priority;
        this.form = form;
    }

    public void addDate(Date date) {
        this.dates.add(date);
    }

    public void addEmail(Email email) {
        this.emails.add(email);
    }

    public void addInput(Input input) {
        this.inputs.add(input);
    }

    public void addNumber(Number number) {
        this.numbers.add(number);
    }
}
