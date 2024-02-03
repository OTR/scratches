package hexagonal.architecture.framework.adapter.out.h2.data;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@Table(name = "switches")
@SecondaryTable(name = "networks")
@MappedSuperclass
@Converter(name = "uuidConverter", converterClass = UUIDTypeConverter.class)
public class SwitchData implements Serializable {

    @Id
    @Column(
        name = "switch_id",
        columnDefinition = "uuid",
        updatable = false
    )
    @Convert("uuidConverter")
    private UUID switchId;

    @Column(name = "router_id")
    @Convert("uuidConverter")
    private UUID routerId;

    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(name = "switch_type")
    private SwitchTypeData switchType;

    @OneToMany
    @JoinColumn(
        table = "networks",
        name = "switch_id",
        referencedColumnName = "switch_id"
    )
    private List<NetworkData> networks;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(
            name = "address",
            column = @Column(name = "switch_ip_address")
        ),
        @AttributeOverride(
            name = "protocol",
            column = @Column(name = "switch_ip_protocol")
        )
    })
    IPData ip;

}
