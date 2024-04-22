package oslomet.data1700_oblig3.repository;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import oslomet.data1700_oblig3.pojo.Bestilling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class KinoRepository {

    @Autowired
    private JdbcTemplate db;

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


    public int lagreBestilling(Bestilling bestilling) {
        String sql = "INSERT INTO BESTILLING (film, antall, fornavn, " +
                "etternavn, telefon, epost) VALUES (?, ?, ?, ?, ?, ?)";

        return db.update(sql, bestilling.getFilm(), bestilling.getAntall(),
                bestilling.getFornavn(), bestilling.getEtternavn(),
                bestilling.getTelefon(), bestilling.getEpost());
    }

    public List<Bestilling> hentAlleBestillinger() {
        String sql = "SELECT * FROM BESTILLING";
        return db.query(sql, new BestillingRowMapper());
    }


    public int slettAlleBestillinger() {
        String sql = "DELETE FROM BESTILLING";
        return db.update(sql);
    }

}
