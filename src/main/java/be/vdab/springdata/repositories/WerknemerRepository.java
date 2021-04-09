package be.vdab.springdata.repositories;

import be.vdab.springdata.domain.Filiaal;
import be.vdab.springdata.domain.Werknemer;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface WerknemerRepository extends JpaRepository<Werknemer,Long> {
    List<Werknemer> findByFiliaalGemeente(String Gemeente);
    @EntityGraph(value = "Werknemer.metFiliaal")
    List<Werknemer> findByVoornaamStartingWith(String woord);

    Page<Werknemer>findAll(Pageable pageable);

//    Optional<Werknemer> findById(long id);
List<String>vindmefiliaalgemeentetest(String gemeente);
List<String>vindmefiliaal();
List<Werknemer>findAll();
//List<Werknemer>findByVoornaamStartingWith(String woord);
   @Query ( "select w.id,w.voornaam, w.familienaam,(select id from w.filiaal) from Werknemer w")
    List<String>zoekalle();
}
