package hex.arch.topologyinventory.framework.adapter.out.h2.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Builder;

@Builder
@Entity
@Table(name = "location")
@MappedSuperclass
public class LocationData {

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

    public static LocationDataBuilder builder() {
        return new LocationDataBuilder();
    }

    public static final class LocationDataBuilder {

        private int locationId;
        private String address;
        private String city;
        private String state;
        private int zipcode;
        private String country;
        private float latitude;
        private float longitude;

        public LocationDataBuilder locationId(int locationId) {
            this.locationId = locationId;
            return this;
        }

        public LocationDataBuilder address(String address) {
            this.address = address;
            return this;
        }

        public LocationDataBuilder city(String city) {
            this.city = city;
            return this;
        }

        public LocationDataBuilder state(String state) {
            this.state = state;
            return this;
        }

        public LocationDataBuilder zipcode(int zipcode) {
            this.zipcode = zipcode;
            return this;
        }

        public LocationDataBuilder country(String country) {
            this.country = country;
            return this;
        }

        public LocationDataBuilder latitude(float latitude) {
            this.latitude = latitude;
            return this;
        }
/*
        public LocationDataBuilder longitude(float longitude) {
            this.longitude = longitude;
            return this;
        }

        public LocationData build() {
            if (
                this.locationId != 0
                    && this.address != null
                    && this.city != null
                    && this.state != null
                    && this.zipcode != 0
                    && this.country != null
                    && this.latitude != 0f
                    && this.longitude != 0f
            ) {
                return new LocationData(
                    locationId, address, city, state, zipcode,
                    country, latitude, longitude
                );
            }

            throw new IllegalArgumentException("Some fields are not set");

        }
*/
    }

}
