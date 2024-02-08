package hex.arch.topologyinventory.framework.adapter.out.h2.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;

import lombok.Builder;

import java.io.Serializable;

@Builder
@Entity
@Table(name = "location")
@MappedSuperclass
public class LocationData implements Serializable {

    @Id
    @Column(name = "location_id")
    private int locationId;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private int zipcode;

    @Column(name = "country")
    private String country;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    public LocationData() {}

    public LocationData(
        int locationId,
        String address,
        String city,
        String state,
        int zipcode,
        String country,
        float latitude,
        float longitude
    ) {
        this.locationId = locationId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getCountry() {
        return country;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

}
