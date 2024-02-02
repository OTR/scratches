package hexagonal.architecture.framework.adapter.out.h2.data;

import hexagonal.architecture.framework.adapter.out.h2.UUIDTypeConverter;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
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
@Table(name = "networks")
@MappedSuperclass
@Converter(name = "uuidConverter", converterClass = UUIDTypeConverter.class)
public class NetworkData implements Serializable {

    @Id
    @Column(name = "network_id")
    private int id;

    @Column(name = "switch_id")
    @Convert("uuidConverter")
    private UUID switchId;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(
                name = "address",
                column = @Column(name = "network_address")
        ),
        @AttributeOverride(
                name = "protocol",
                column = @Column(name = "network_protocol")
        )
    })
    private IPData ip;

    @Column(name = "network_name")
    String name;

    @Column(name = "network_cidr")
    Integer cidr;

    public NetworkData(UUID switchId, IPData ip, String name, Integer cidr) {
        this.switchId = switchId;
        this.ip = ip;
        this.name = name;
        this.cidr = cidr;
    }

}
