Scenario:  Kunde bestellt ein Gericht
 
Given eine Bestellung bestehend aus 0 identischen Gerichten
When der Nutzer 1 identisches Gericht zu der Bestellung hinzufügt
Then soll die Bestellung aus 1 identischen Gericht/en bestehen


Scenario:  Kunde bestellt drei identische Gerichte
 
Given eine Bestellung bestehend aus 2 identischen Gerichten
When der Nutzer 1 identisches Gericht zu der Bestellung hinzufügt
Then soll die Bestellung aus 4 identischen Gericht/en bestehen