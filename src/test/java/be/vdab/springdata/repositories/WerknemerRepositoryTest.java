package be.vdab.springdata.repositories;
import be.vdab.springdata.domain.Filiaal;
import be.vdab.springdata.domain.Werknemer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest(showSql = false)
@Sql({"/insertFilialen.sql","/insertWerknemers.sql"})

class WerknemerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final WerknemerRepository repository;
private final FiliaalRepository filiaalRepository;

    WerknemerRepositoryTest(WerknemerRepository repository, FiliaalRepository filiaalRepository) {
        this.repository = repository;
        this.filiaalRepository = filiaalRepository;
    }


    @Test
    void findAll(){
        System.out.println("allemaal:!! :" + filiaalRepository.findAll());

        System.out.println(repository.count());
        System.out.println("TOSRTING:   " +repository.toString());

        System.out.println(repository.findById(1l));
    }


    @Test
   void zoekviagemeentefindByFiliaalGemeente2(){
        System.out.println(repository.zoekviagemeente2("Antwerpen"));
    }

@Test
void findById(){
    System.out.println(repository.findById(1));
}


@Test
void findByVoornaamStartingWith(){
        var werknemers= repository.findByVoornaamStartingWith("J");
        assertThat(werknemers).hasSize(2)
                .allSatisfy(werknemer ->
                assertThat(werknemer.getVoornaam().startsWith("J")));
    assertThat(werknemers).extracting(werknemer ->
            werknemer.getFiliaal().getNaam());


    }

    @Test
    void vindmefindbyFiliaalGemeenteTest(){
        System.out.println(repository.vindmefiliaalgemeentetest("Antwerpen"));
    }

    @Test
    void findByFiliaalGemeente(){
        var antwerpen="Antwerpen";
        System.out.println(repository);;
List<Werknemer> werknemers = repository.findByFiliaalGemeente(antwerpen);
        assertThat(werknemers).hasSize(1);

        assertThat(werknemers.get(0).getFiliaal().getGemeente()).isEqualTo(antwerpen);
    }
}
