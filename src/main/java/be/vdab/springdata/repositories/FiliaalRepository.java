package be.vdab.springdata.repositories;

import be.vdab.springdata.domain.Filiaal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface FiliaalRepository extends JpaRepository<Filiaal,Long> {

    List<Filiaal> findByGemeente(String gemeente);
    List<Filiaal> findByOmzetGreaterThanEqual(BigDecimal vanaf);
    int countByGemeente(String gemeente);
List<Filiaal> findMetHoogsteOmzet();


    @Query("select avg(f.omzet)from Filiaal f")
    BigDecimal findGemiddeldeOmzet();

}
