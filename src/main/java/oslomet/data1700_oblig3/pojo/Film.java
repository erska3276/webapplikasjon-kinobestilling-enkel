package oslomet.data1700_oblig3.pojo;

/* POJO (Plain Old Java Object) klasse for bruk ved overføring av data
 *  mellom klient og tjener/server. Spring håndterer konverteringen dersom
 *  samme attributt-navn på java-objekt som variabelnavn i javascript-objektet.
 * */
public class Film {

    private String tittel;

    public Film() {}

    public Film(String tittel) {
        this.tittel = tittel;
    }

    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }
}
