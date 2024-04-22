# data1700-oblig3
=================

Oslomet brukernavn: erska3276

Github brukernavn: erska3276

Github-repo URL: https://github.com/erska3276/data1700-oblig1

Fullt navn: Erik Skålhegg

Kort beskrivelse av applikasjonen (5-10 setninger):
----------------------------------------------------
Oversikt over bestilliger av kinobilletter. Brukeren kan velge film, antall
billetter, og legge inn personopplysninger. Ved å trykke knappen **kjøp** legges
opplysningene i oversikten over bestillinger. **Slett** knappen fjerner alle
bestillinger registert. Input feltene valideres etter kriterier satt av regular
expressions i javascript filen. Ved feil input vil appen gi en tilbakemelding på
feltet om hva som må gjøres.


Utvidet web-funksjonalitet (Server/tjener og kontroller i mvc-modellen):
----------------------------
Bruker Spring Boot og Java for å lagre data på webserver i stedet med ArrayList. Klient kan altså nå
både sende/lagre og hente filmbestillings data fra server/tjener. Valgalternativer er også
nå lagret og hentet fra server/tjener.