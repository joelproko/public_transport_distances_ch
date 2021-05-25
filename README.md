# public_transport_distances_ch

# Deutsch/German (English below)

 Android App: Wann muss ich von einer Adresse los, um per ÖV/SBB zu gegebenen Zeiten/Daten an gegebenen Adressen/Orten anzukommen?
 
Soll mit der API von fahrplan.search.ch funktionieren.

Als "Destination" ist hier eine Adresse oder eine ÖV-Haltestelle zusammen mit gewünschter Ankunfszeit und -datum definiert.

Bei normalem öffnen der App kann man die Liste der Zieladressen und der respektiven gewünschten Ankunftsdaten sehen und bearbeiten. Hinzufügen und Löschen von Adressen funktioniert, aber direktes Bearbeiten einer Destination funktioniert noch nicht: stattdessen die Destination löschen und neu erstellen.

Datumsauswahl beim erstellen einer Destination hat zwei Zwecke:
    - Wahl zwischen Werktagen und Wochenenden/Feiertagen
    - Identifikation von Destinationen: Da nicht garantiert ist, dass die Bezeichnung der Zieladresse identisch zurückkommt, und weil man vielleicht die selbe Adresse zweimal zu verschiedenen Zeiten sehen möchte, bleibt nur das Datum als Identifikator übrig.
    
Jede Destination muss deshalb ein anderes Datum haben.

Wenn eine Adresse mit der App geteilt wird, sollte für jede Destination angezeigt werden, wann der späteste Abfahrtszeitpunkt (inkl. Fussweg) von der geteilten Adresse ist, wann der entsprechende tatsächliche Ankunftszeitpunkt (auch inkl. Fussweg) ist, und was die Zieladresse ist.

# English
 Android App: When do I need to depart from a given address, using public transport, to arrive at pre-stored addresses at their respective arrival times/dates?
 
Designed to work with the Route searches API described on https://timetable.search.ch/api/help

A "Destination" is hereby defined as the combination of an Address or public transport stop, and a desired arrival date and arrival time.

Normal opening of app shows the current list of Destinations and allows deletion of Destinations as well as adding new ones. Editing existing ones doesn't work yet.
    
When adding a new Destination, the date has two functions:
    - Choosing between workdays and weekends/holidays.
    - Identification of destinations: Because it cannot be guaranteed that the target address is returned exactly the same way by the API, and because one might want to have the same target address at different days/times, the date remains as the only identifier left.
    
Because of the second reason, no Destinations may share exactly the same calendar date.

Opening the app by sharing an address with it should, for each Destination, show the latest possible departure time (incl. walking to the first public transport stop), the actual arrival time (incl. walking from the last stop to the target address), and the target address itself.

(There are very few Strings so far: basically just the app name, a text field hint, an error message, two frankly unnecessary labels and two invisible content descriptions for buttons.)

(The tag "housing" is because I wrote this so as to make hunting for housing close enough in terms of public transport to both work and friends easier.)
