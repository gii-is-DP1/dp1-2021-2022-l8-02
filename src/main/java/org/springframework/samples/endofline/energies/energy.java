package org.springframework.samples.endofline.energies;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.samples.endofline.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="energies")
public class energy extends BaseEntity{
    @NotNull
    Integer counter;

    @NotNull
    Power power;

}
