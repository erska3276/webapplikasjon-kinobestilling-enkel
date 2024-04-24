# data1700-oblig3
=================

Oslomet brukernavn: erska3276

Github brukernavn: erska3276

Github-repo URL: https://github.com/erska3276/data1700-oblig1

Fullt navn: Erik Skålhegg

Kort beskrivelse av applikasjonen (5-10 setninger):
-------------------------------------------------------------
Oversikt over bestilliger av kinobilletter. Brukeren kan velge film, antall
billetter og skrive inn personopplysninger. Ved å trykke knappen **kjøp** legges
opplysningene i oversikten over bestillinger. **Slett** knappen fjerner alle
bestillinger registert. Input feltene valideres etter kriterier satt av regular
expressions i javascript filen. Ved feil input vil appen gi en tilbakemelding på
feltet om hva som må gjøres

Utvidet web-funksjonalitet (Server/tjener og kontroller i mvc-modellen):
-------------------------------------------------------------
Bruker Spring Boot og Java for å lagre data på webserver i stedet med ArrayList. Klient kan altså nå
både sende/lagre og hente bestillingsdata fra server/tjener. Film-valgalternativer er også
nå lagret og hentet fra server/tjener. Start webserver i Intellij og se i browser på url:
localhost:8080

Utvidet server-funksonalitet (Database)
-------------------------------------------------------------------
Data lagres nå i en database(h2 memory) ved kjøring av Tomcat webserveren vår via Spring boot. Enkeltrader
kan nå både endres og fjernes via **Endre** og **Fjern** knappene i radene i tabellen.
Tilgang til h2-databasen kan gjøres via browser url: localhost:8080/h2-console. Innlogging informasjon
står i "application.properties" filen