Sample story

Narrative:
Der Benutzer soll mit einer Meldung daraufhingewiesen werden,
dass nur Zahlen beim Anlegen oder Aktualisieren des Preises eines Gerichts eingebene werden können.
					 
Scenario:  Abfangen wenn beim Preis keine Zahl eingegeben wird
Given eine Eingabe für einen Preis 3,9o
When die ungültige Eingabe in eine Zahl umgewandelt wird
Then soll eine Fehlermeldung aufgerufen werden
