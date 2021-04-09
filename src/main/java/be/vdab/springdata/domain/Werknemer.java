package be.vdab.springdata.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "werknemers")
@NamedEntityGraph(name = "Werknemer.metFiliaal",attributeNodes = @NamedAttributeNode("filiaal"))
public class Werknemer {

@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private String voornaam;
private String familienaam;
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "filiaalid")
private Filiaal filiaal;



    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }
   public Long getFiliaalId() {return filiaal.getId();}
    public Filiaal getFiliaal() {return filiaal; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Werknemer)) return false;
        Werknemer werknemer = (Werknemer) o;
        return getId() == werknemer.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id+voornaam+familienaam+filiaal);
    }
}
