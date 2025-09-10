> Originalt en obligatorisk oppgave i et webapplikasjonskurs

Kort beskrivelse av applikasjonen (5-10 setninger):
----------------------------------------------------------------------
Oversikt over bestilliger av kinobilletter. Brukeren kan velge film, 
antall billetter og skrive inn personopplysninger. Ved å trykke knappen
**Kjøp** legges opplysningene i oversikten over bestillinger. Knappen
**Slett** fjerner alle registerte bestillinger . Inputfeltene valideres
etter kriterier satt av regular expressions i JavaScript-filen. Ved 
feil input vil applikasjonen gi en tilbakemelding på feltet om hva som
må gjøres.

Utvidet web-funksjonalitet (Server/tjener og kontroller i mvc-modellen):
----------------------------------------------------------------------
Applikasjonen bruker Spring Boot og Java for å lagre data på webserver
i stedet for i en ArrayList. Klienten kan nå både sende/lagre og hente
bestillingsdata fra serveren/tjeneren. Filmvalgalternativer lagres og
hentes også fra serveren/tjeneren. Start webserveren i IntelliJ og 
åpne nettleseren på URL: **localhost:8080**.

Utvidet server-funksonalitet (Database)
----------------------------------------------------------------------
Data lagres nå i en database (H2 Memory) ved kjøring av 
Tomcat-webserveren via Spring Boot. Enkeltrader kan både endres og 
fjernes via knappene **Endre** og **Fjern** i tabellen. Tilgang til 
H2-databasen kan gjøres via nettleserens URL: 
**localhost:8080/h2-console**. Innloggingsinformasjon finnes i 
**application.properties**-filen.

**Video av kjoring:** https://www.youtube.com/watch?v=SbnXVFcDRtE
