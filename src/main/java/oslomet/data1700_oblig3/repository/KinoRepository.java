package oslomet.data1700_oblig3.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import oslomet.data1700_oblig3.pojo.Bestilling;
import oslomet.data1700_oblig3.pojo.Film;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/* Repository klasse som inneholder metoder for å jobbe mot databasen for
 * "domene"-modellen Bestilling(POJO) og Film(POJO).
 * */
@Repository
public class KinoRepository {

    @Autowired
    private JdbcTemplate db;    //aksess til database
    final private Logger logger = LoggerFactory.getLogger(KinoRepository.class);

    /* Hjelpeklasse for aa oversette Bestilling-tabell i DB til java-objekt
     * "Bestilling". Mapper da kolonner i rader til Bestilling-tabell til
     * attributter i Bestilling java-objekt.
     * */
    class BestillingRowMapper implements RowMapper<Bestilling> {

        @Override
        public Bestilling mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bestilling b = new Bestilling();
            b.setId(rs.getLong("ID"));
            b.setFilm(rs.getString("FILM"));
            b.setAntall(rs.getInt("ANTALL"));
            b.setFornavn(rs.getString("FORNAVN"));
            b.setEtternavn(rs.getString("ETTERNAVN"));
            b.setTelefon(rs.getString("TELEFON"));
            b.setEpost(rs.getString("EPOST"));

            return b;
        }
    }

    /* Hjelpeklasse for aa oversette Film-tabell i DB til java-objekt
     * "Film". Mapper da kolonner i rader til Film-tabell til
     * attributter i Film java-objekt.
     * */
    class FilmRowMapper implements RowMapper<Film> {

        @Override
        public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
            Film f = new Film();
            f.setId(rs.getLong("ID"));
            f.setTittel(rs.getString("TITTEL"));

            return f;
        }
    }

    //Returnerer 0 eller 1 ved vanlig eksekvering, -1 ved feil
    public int lagreBestilling(Bestilling b) {
        String sql = "INSERT INTO BESTILLING (film, antall, fornavn, " +
                "etternavn, telefon, epost) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            return db.update(sql, b.getFilm(), b.getAntall(), b.getFornavn(),
                    b.getEtternavn(), b.getTelefon(), b.getEpost());
        } catch (Exception e) {
            logger.error("Feil i lagreBestilling(Bestilling bestilling): " + e);
            return -1;
        }
    }

    //Returnerer Bestilling-objekt ved vanlig eksekvering, null ved feil
    public Bestilling hentBestilling(Long id) {
        String sql = "SELECT * FROM BESTILLING WHERE ID = ?";

        try {
            return db.queryForObject(sql, new BestillingRowMapper(), id);
        } catch (Exception e) {
            logger.error("Feil i hentBestilling(Bestilling bestilling): " + e);
            return null;
        }
    }

    //Returnerer 0 eller 1 ved vanlig eksekvering, -1 ved feil
    public int endreBestilling(Bestilling b) {
        String sql = "UPDATE BESTILLING SET film=?, antall=?, fornavn=?,"
                + " etternavn=?, telefon=?, epost=? WHERE id=?";

        try {
            return db.update(sql, b.getFilm(), b.getAntall(), b.getFornavn(),
                    b.getEtternavn(), b.getTelefon(), b.getEpost(), b.getId());
        } catch (Exception e) {
            logger.error("Feil i endreBestilling(Bestilling b): " + e);
            return -1;
        }
    }

    //Returnerer 0 eller 1 ved vanlig eksekvering, -1 ved feil
    public int slettBestilling(Long id) {
        String sql = "DELETE FROM BESTILLING WHERE ID = ?";

        try {
            return db.update(sql, id);
        } catch (Exception e) {
            logger.error("Feil i slettBestilling(id): " + e);
            return -1;
        }
    }

    //Returnerer Bestillings-liste ved vanlig eksekvering, null ved feil
    public List<Bestilling> hentAlleBestillinger() {
        String sql = "SELECT * FROM BESTILLING ORDER BY ETTERNAVN, FORNAVN";

        try {
            return db.query(sql, new BestillingRowMapper());
        }
        catch (Exception e) {
            logger.error("Feil i hentAlleBestillinger(): " + e);
            return null;
        }
    }

    //Returnerer 0 eller 1 ved vanlig eksekvering, -1 ved feil
    public int slettAlleBestillinger() {
        String sql = "DELETE FROM BESTILLING";

        try {
            return db.update(sql);
        } catch (Exception e) {
            logger.error("Feil i slettAlleBestillinger(): " + e);
            return -1;
        }
    }

    //Returnerer Film-liste ved vanlig eksekvering, null ved feil
    public List<Film> hentAlleFilmer() {
        String sql = "SELECT * FROM FILM ORDER BY TITTEL";

        try {
            return db.query(sql, new FilmRowMapper());
        } catch (Exception e) {
            logger.error("Feil i hentAlleFilmer(): " + e);
            return null;
        }
    }

}
