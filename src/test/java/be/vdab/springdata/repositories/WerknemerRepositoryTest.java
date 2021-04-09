package be.vdab.springdata.repositories;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
// enkele andere imports
@DataJpaTest(showSql = false)
@Sql({"/insertFilialen.sql", "/insertWerknemers.sql"})
class WerknemerRepositoryTest
        extends AbstractTransactionalJUnit4SpringContextTests {
    private final WerknemerRepository repository;

    WerknemerRepositoryTest(WerknemerRepository repository) {
        this.repository = repository;
    }

    @Test
    void findByFiliaalGemeente() {
        var antwerpen = "Antwerpen";
        var werknemers = repository.findByFiliaalGemeente(antwerpen);
        assertThat(werknemers).hasSize(1);
        assertThat(werknemers.get(0).getFiliaal().getGemeente()).isEqualTo(antwerpen);
    }
    @Test
    void vindmefindbyFiliaalGemeenteTest(){
        System.out.println(repository.vindmefiliaalgemeentetest("Antwerpen"));
    }
    @Test
    void vindmefiliaal(){
        System.out.println(repository.vindmefiliaal());
    }
@Test
void Zoekalle(){
    System.out.println(repository.zoekalle());
}
    @Test
            void findAll(){
        System.out.println(repository.findAll());
    }

    @Test
    void findByVoornaamStartingWith() {
        var werknemers = repository.findByVoornaamStartingWith("J");
        assertThat(werknemers).hasSize(2)
                .allSatisfy(werknemer ->
                        assertThat(werknemer.getVoornaam().startsWith("J")));
        assertThat(werknemers).extracting(
                werknemer -> werknemer.getFiliaal().getNaam());
    }
}
