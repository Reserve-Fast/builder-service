package nl.reservefast.builderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter @Setter
public class Form extends BaseEntity implements Serializable {

    @JsonIgnore
    @Type(type="uuid-char")
    @Column(nullable = false)
    private UUID companyId;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="form", orphanRemoval = true)
    private Set<Row> rows = new HashSet<>();

    public Form() {}

    public Form(UUID companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public void addRow(Row row) {
        this.rows.add(row);
    }

}
