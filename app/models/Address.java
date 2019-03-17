package models;

import io.ebean.Finder;
import io.ebean.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends Model {

    @Id
    @GeneratedValue
    public Long id;

    public Integer postalCode;
    public String city;
    public String restOfAddress;

    public static final Finder<Long, Address> find = new Finder<>(Address.class);
}
