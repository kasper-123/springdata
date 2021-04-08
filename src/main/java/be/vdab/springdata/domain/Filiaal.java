package be.vdab.springdata.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name= "filialen")
public class Filiaal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String naam;
    private String gemeente;
    private BigDecimal omzet;

    public Filiaal( String naam, String gemeente, BigDecimal omzet) {

        this.naam = naam;
        this.gemeente = gemeente;
        this.omzet = omzet;
    }
    protected Filiaal(){}


    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getGemeente() {
        return gemeente;
    }

    public BigDecimal getOmzet() {
        return omzet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Filiaal)) return false;
        Filiaal filiaal = (Filiaal) o;
        return id == filiaal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
