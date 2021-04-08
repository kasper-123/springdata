package be.vdab.springdata.repositories;

import be.vdab.springdata.domain.Filiaal;
import be.vdab.springdata.domain.Werknemer;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface WerknemerRepository extends JpaRepository<Werknemer,Long> {
    List<Werknemer> findByFiliaalGemeente(String gemeente);
Optional<Werknemer> findById(long id);

List<Werknemer>vindmefiliaalgemeentetest(String gemeente);
List<Werknemer>findByVoornaamStartingWith(String woord);

    @Query ("select x.id, x.voornaam,x.familienaam, x.filiaal from Werknemer x where x.filiaal.id= (select f.id  from Filiaal f where f.gemeente= 'Antwerpen')")
    List<String>zoekviagemeente2(String gemeente);
}
