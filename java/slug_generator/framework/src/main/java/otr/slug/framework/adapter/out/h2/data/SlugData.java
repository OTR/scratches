package otr.slug.framework.adapter.out.h2.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

// AllArgsConstructor
// NoArgsConstructor
// Getter
@Entity
@Table(name = "slugs")
public class SlugData {

    @Id
    @Column
    private UUID slugId;

}
