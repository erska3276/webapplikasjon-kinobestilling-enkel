// Ready-funksjon venter til html-dokumentet er lastet
// ferdig for scriptet kjorer.
$(function() {
    //henter liste over filmer fra server og lager en dropdown-liste
    hentAlleFilmer();
    let synligUtskrift = false;

    //Event ved trykk på kjøpe knappen som legger til bestilling av
    //kinobillett til bestillings-arrayet, henter objekter for
    //å ikke query etter samme objekt flere ganger(warning)
    $("#kjopKnapp").click(function() {
        const film = $("#film");
        const antall = $("#antall");
        const fnavn = $("#fornavn");
        const enavn = $("#etternavn");
        const tlf = $("#telefonnr");
        const epost =$("#epost");

        //Her lager vi regular expressions for å validere input's
        //vi får fra html-formen, film sjekker vi for null-verdi
        const antallValidate = /^\d{1,3}$/;
        const fnavnValidate = /.+/;
        const enavnValidate = /.+/;
        const tlfValidate = /^(\+47)?\d{8}$/;
        //Mange varianter, denne er hentet fra https://regexr.com/3e48o
        const epostValidate = /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/;

        let validater = true;
        const errorKlasse = "validate-error";
        const errorString = "class ='"+errorKlasse+"'";
        //Fjerner feilmeldinger slik at vi ikke får duplikater
        $("span").remove("."+errorKlasse);

        //Dersom et input-felt ikke er gyldig legger vi inn en
        //<span class="validate-error"> bak med feilmelding, <label>
        //er mer semantisk, men bruker <span> for enkelhet
        if (!film.val()) {
            film.after("<span "+errorString+">"+" Må velge film"+"</span>");
            validater = false;
        }
        if (!antallValidate.test(antall.val())) {
            antall.after("<span "+errorString+">"+" Må være tall"+"</span>");
            validater = false;
        }
        if (!fnavnValidate.test(fnavn.val())) {
            fnavn.after("<span "+errorString+">"+" Må skrive noe"+"</span>");
            validater = false;
        }
        if (!enavnValidate.test(enavn.val())) {
            enavn.after("<span "+errorString+">"+" Må skrive noe"+"</span>");
            validater = false;
        }
        if (!tlfValidate.test(tlf.val())) {
            tlf.after("<span "+errorString+">"+" Må være norsk telefonnr"+"</span>");
            validater = false;
        }
        if (!epostValidate.test(epost.val())) {
            epost.after("<span "+errorString+">"+" Epost ugyldig"+"</span>");
            validater = false;
        }

        //Avbryter ved feil i et input-felt
        if (!validater) {
            return false;
        }

        //Lag bestillings-objekt og legg i liste
        const bestilling = {
            film : film.val(),
            antall : antall.val(),
            fornavn : fnavn.val(),
            etternavn : enavn.val(),
            telefon : tlf.val(),
            epost : epost.val()
        };

        //Send javaobjekt til lagring paa server og skriv ut paa klient
        $.post("/leggTilBestilling", bestilling, function () {
            skrivAlleBestillinger();

        }).fail(function(jqXHR) {
                const json = $.parseJSON(jqXHR.responseText);
                skrivUtskrift(json.message);
        });

        //Resetter/blanker alle input felt i formen
        $(":input","#kinoForm").val("");
        return true;
    });

    //Event ved trykk på slette knappen som tømmer hele bestillings-arrayet
    //og utskriften av den i div-elementet "utskrift".
    $("#slettKnapp").click(function() {
        $("#utskrift").remove();
        synligUtskrift = false;
        //Tømmer bestillings-listen ved tomme arraylist paa server
        $.post("/slettAlleBestillinger", function() {});
    });

    function skrivAlleBestillinger() {
        $.get("/hentAlleBestillinger", function(bData) {
            let ut = "<table id='table-result' class='table table-striped'><tr>";
            ut += "<th>Film</th><th>Antall</th><th>Fornavn</th>";
            ut += "<th>Etternavn</th><th>Telefonnr</th><th>Epost</th></tr>";

            for (let b of bData) {
                ut += "<tr>";
                ut += "<td>" + b.film + "</td><td>" + b.antall + "</td>";
                ut += "<td>" + b.fornavn + "</td><td>" + b.etternavn + "</td>";
                ut += "<td>" + b.telefon + "</td><td>" + b.epost + "</td>";
                ut += "</tr>";
            }
            ut += "</table>";

            skrivUtskrift(ut);

        }).fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            skrivUtskrift(json.message);
        });
    }

    function hentAlleFilmer() {
        $.get("/hentAlleFilmer", function(fData) {
            let ut = "<label for='film'>Velg film:</label>";
            ut += "<select id='film'>";
            ut += "<option selected disabled></option>";

            for(let film of fData) {
                ut += "<option value='"+film.tittel+"'>"+film.tittel+"</option>";
            }
            ut += "</select>";
            $("#filmListe").html(ut);

        }).fail(function(jqXHR) {
                const json = $.parseJSON(jqXHR.responseText);
                $("#filmListe").html(json.message);
        });
    }

    function skrivUtskrift(ut) {
        //Vis tabell i html-dokumentet ved å sette inn et nytt div element
        //med tabellen. Om div er opprettet skriver vi tabellen inn i stedet.
        if (synligUtskrift) {
            $("#utskrift").html(ut);
        } else {
            $("#billettoversikt").after("<div id='utskrift'>"+ut+"</div>");
            synligUtskrift = true;
        }
    }
});