package be.vdab.springdata.repositories;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.InstanceOfAssertFactories.CLASS;

import be.vdab.springdata.domain.Filiaal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;
import java.util.Set;

@DataJpaTest
@Sql("/insertFilialen.sql")
class FiliaalRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
private static final String FILIALEN="filialen";
private final  FiliaalRepository repository;


    FiliaalRepositoryTest(FiliaalRepository repository) {
        this.repository = repository;
    }

    private long idVanAlpha(){
        return jdbcTemplate.queryForObject("select id from filialen where naam= 'Alfa'",Long.class);
    }

private long idvanBravo(){
        return jdbcTemplate.queryForObject("select id from filialen where naam='Bravo'",Long.class);

}

@Test
    void count(){
        assertThat(repository.count()).isEqualTo(countRowsInTable(FILIALEN));
}
@Test
    void findById(){
        var optionalFiliaal= repository.findById(idVanAlpha());
        assertThat(optionalFiliaal.get().getNaam()).isEqualTo("Alfa");
}
@Test
    void findAll(){
        var filialen =repository.findAll(Sort.by("gemeente"));
        assertThat(filialen).hasSize(countRowsInTable(FILIALEN));
        assertThat(filialen).extracting(filiaal -> filiaal.getGemeente().toLowerCase())
.isSorted();
}

@Test
    void findallById(){
        var idAlfa=idVanAlpha();
        var idBravo=idvanBravo();
        var filialen= repository.findAllById(Set.of(idVanAlpha(),idvanBravo()));
        assertThat(filialen).extracting(Filiaal::getId).containsOnly(idAlfa,idBravo);
}

@Test
    void save(){
        var filiaal= new Filiaal("Delta","Brugge", BigDecimal.TEN);
    System.out.println(repository);
repository.save(filiaal);
var id = filiaal.getId();
assertThat(id).isPositive();
assertThat(countRowsInTableWhere(FILIALEN,"id="+id)).isOne();

    }

    @Test
    void deleteById(){
        var id= idVanAlpha();
        repository.deleteById(id);
        repository.flush();
        assertThat(countRowsInTableWhere(FILIALEN,"id="+id)).isZero();
    }

    @Test
    void deleteOnbestaandeId(){
        assertThatExceptionOfType(EmptyResultDataAccessException.class).isThrownBy(
                ()-> repository.deleteById(-1L));

    }
    @Test
    void findByGemente(){
        var filialen =repository.findByGemeente("Brussel");
        assertThat(filialen).hasSize(2)
                .allSatisfy(filiaal -> assertThat(filiaal.getGemeente()).isEqualTo("Brussel"));
    }


    @Test
    void findByOmzetGreaterThanEqual(){
        var tweeduizend = BigDecimal.valueOf(2_000);
        var filialen= repository.findByOmzetGreaterThanEqual(tweeduizend);
        assertThat((filialen)).hasSize(2)
                .allSatisfy(filiaal -> assertThat(filiaal.getOmzet())
                        .isGreaterThanOrEqualTo(tweeduizend));
    }



    @Test
    void countByGemeente(){
        assertThat(repository.countByGemeente("Brussel")).isEqualTo(2);
    }


    @Test
    void findGemiddeldeOmzet(){
        assertThat(repository.findGemiddeldeOmzet()).isEqualByComparingTo("2000");
    }
    @Test
    void findMetHoogstezOmzet(){
        var filialen = repository.findMetHoogsteOmzet();
        assertThat(filialen).hasSize(1);
        assertThat(filialen.get(0).getNaam()).isEqualTo("Charly");
    }

}
