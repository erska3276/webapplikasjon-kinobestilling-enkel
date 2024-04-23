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

@Repository
public class KinoRepository {

    @Autowired
    private JdbcTemplate db;
    final private Logger logger = LoggerFactory.getLogger(KinoRepository.class);

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

    class FilmRowMapper implements RowMapper<Film> {

        @Override
        public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
            Film f = new Film();
            f.setId(rs.getLong("ID"));
            f.setTittel(rs.getString("TITTEL"));

            return f;
        }
    }

    public int lagreBestilling(Bestilling bestilling) {
        String sql = "INSERT INTO BESTILLING (film, antall, fornavn, " +
                "etternavn, telefon, epost) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            return db.update(sql, bestilling.getFilm(), bestilling.getAntall(),
                    bestilling.getFornavn(), bestilling.getEtternavn(),
                    bestilling.getTelefon(), bestilling.getEpost());
        } catch (Exception e) {
            logger.error("Feil i lagreBestilling(Bestilling bestilling): " + e);
            return -1;
        }
    }


    public int slettBestilling(Long id) {
        String sql = "DELETE FROM BESTILLING WHERE ID = ?";

        try {
            return db.update(sql, id);
        } catch (Exception e) {
            logger.error("Feil i slettBestilling(id): " + e);
            return -1;
        }
    }

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

    public int slettAlleBestillinger() {
        String sql = "DELETE FROM BESTILLING";

        try {
            return db.update(sql);
        } catch (Exception e) {
            logger.error("Feil i slettAlleBestillinger(): " + e);
            return -1;
        }
    }

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
