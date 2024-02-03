package hexagonal.architecture.framework.adapter.out.h2.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@Table(name = "routers")
@SecondaryTable(name = "switches")
@MappedSuperclass
@Converter(name="uuidConverter", converterClass= UUIDTypeConverter.class)
public class RouterData implements Serializable {

    @Id
    @Column(
        name = "router_id",
        columnDefinition = "uuid",
        updatable = false
    )
    @Convert("uuidConverter")
    private UUID routerId;

    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(name = "router_type")
    private RouterTypeData routerType;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(
        table = "switches",
        name = "router_id",
        referencedColumnName = "router_id"
    )
    private SwitchData networkSwitch;

}
