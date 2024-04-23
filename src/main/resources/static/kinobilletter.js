/* Ready-funksjon venter til html-dokumentet er lastet
* ferdig for scriptet kjorer.
* */
$(function() {
    hentAlleFilmer();
    let synligUtskrift = false;

    //Lager regular expressions for å validere input's fra html-formen #kinoForm
    const filmValidate = /.+/;
    const antallValidate = /^\d{1,3}$/;
    const fnavnValidate = /.+/;
    const enavnValidate = /.+/;
    const tlfValidate = /^(\+47)?\d{8}$/;
    //Mange varianter, denne er hentet fra https://regexr.com/3e48o
    const epostValidate = /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/;

    /* Event. Trykk paa #kjopKnapp tar all informasjon fra input-feltene i
    * #kinoForm og prover aa legge inn en ny bestilling til DB paa server, og
    * skriver ut resultatet hos klient. Ved feil skrives feilmelding fra
    * server i stedet.
    * */
    $("#kjopKnapp").click(function() {

        const film = $("#film");
        const antall = $("#antall");
        const fnavn = $("#fornavn");
        const enavn = $("#etternavn");
        const tlf = $("#telefonnr");
        const epost =$("#epost");

        //Fjerner feilmeldinger slik at vi ikke får duplikater
        $("span").remove(".validate-error");

        //Sjekker input felt og avbryter ved feil
        let input1 = inputValidering(film, filmValidate, "Må velge film");
        let input2 = inputValidering(antall, antallValidate, "Må være tall");
        let input3 = inputValidering(fnavn, fnavnValidate, "Må skrive noe");
        let input4 = inputValidering(enavn, enavnValidate, "Må skrive noe");
        let input5 = inputValidering(tlf, tlfValidate, "Må være norsk telefon");
        let input6 = inputValidering(epost, epostValidate, "Epost ugyldig");

        if (!input1 || !input2 || !input3 || !input4 || !input5 || !input6) {
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

    /* Event. Trykk paa #slettKnapp sletter alle bestillinger i DB paa server
    * og fjerner #utskrift hos klient. Ved feil skrives feilmelding fra
    * server i stedet.
    * */
    $("#slettKnapp").click(function() {
        //Tømmer bestillings-listen ved tomme arraylist paa server
        $.post("/slettAlleBestillinger", function() {

        }).fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            skrivUtskrift(json.message);
        });

        $("#utskrift").remove();
        synligUtskrift = false;
    });

    /* Henter liste over alle bestillinger fra DB paa server, oppretter et
    * <table>-element, og skriver ut via "skrivUtskrift". Ved feil skrives
    * feilmelding fra server i stedet.
    * */
    function skrivAlleBestillinger() {
        $.get("/hentAlleBestillinger", function(bData) {
            let ut = "<table id='table-result' class='table table-striped'><tr>";
            ut += "<th>Film</th><th>Antall</th><th>Fornavn</th>";
            ut += "<th>Etternavn</th><th>Telefonnr</th><th>Epost</th><th></th><th></th></tr>";

            for (let b of bData) {
                ut += "<tr>";
                ut += "<td>" + b.film + "</td><td>" + b.antall + "</td>";
                ut += "<td>" + b.fornavn + "</td><td>" + b.etternavn + "</td>";
                ut += "<td>" + b.telefon + "</td><td>" + b.epost + "</td>";
                ut += "<td> <button class='btn btn-primary'>Endre</td>";
                ut += "<td> <button class='btn btn-danger'>Slett</td>";
                ut += "</tr>";
            }
            ut += "</table>";
            skrivUtskrift(ut);

        }).fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            skrivUtskrift(json.message);
        });
    }

    /* Henter en film-liste fra DB paa server og oppretter et
    * <select>-element i #filmliste. Ved feil skrives feilmelding fra
    * server i stedet.
    * */
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

    /* Skriver utskrift ved aa sette inn nytt <div>-element etter
    * #billettoversikt. Skriver i <div>-elementet ellers.
    */
    function skrivUtskrift(ut) {
        if (synligUtskrift) {
            $("#utskrift").html(ut);
        } else {
            $("#billettoversikt").after("<div id='utskrift'>"+ut+"</div>");
            synligUtskrift = true;
        }
    }

    /* Sjekker om input i et objekt er riktig i forhold til en regular
    * expression. Hvis ikke legger vi inn en <span class="validate-error"> bak
    * med feilmelding.
    * */
    function inputValidering(objekt, regEx, feilmelding) {
        if (objekt.val() === null || !regEx.test(objekt.val())) {
            objekt.after("<span class='validate-error'>"+ feilmelding +"</span>");
            return false;
        }
        return true;
    }
});