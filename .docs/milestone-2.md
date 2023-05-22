# 2. Meilenstein

### Kriterien
- PC-Erfahrungen
- Passive/Active Gameplay
- Multiplayer-Spieler
- Alter
- Sprache
- Anbindung (Latenz)


### Stereotypbeschreibung
- [Personenbeschreibung Horst](https://code.fbi.h-da.de/human-computer-interaction/HCI_SS23_Meyer/HCI_SS23_Meyer_Gruppe5-Arfa_Knupfer_Pototzki_Uhrig/-/tree/main/.docs/horst.pdf)
- [Personenbeschreibung Thorsten](https://code.fbi.h-da.de/human-computer-interaction/HCI_SS23_Meyer/HCI_SS23_Meyer_Gruppe5-Arfa_Knupfer_Pototzki_Uhrig/-/tree/main/.docs/thorsten.pdf)
- [Personenbeschreibung Kevin](https://code.fbi.h-da.de/human-computer-interaction/HCI_SS23_Meyer/HCI_SS23_Meyer_Gruppe5-Arfa_Knupfer_Pototzki_Uhrig/-/tree/main/.docs/kevin.pdf)

### Interview

Wir haben 3 Kommilitonen befragt, diese nennen wir auf Grund der Anonymität A, B und C

**F1:** Welche Apps verwenden Sie am meisten?

    A1: Youtube, Instagram, CoC, Gwent
    B1: Youtube, Spotify
    C1: Youtube, Instagram, Facebook Messenger, WhatsApp, Discord

**Hypothese: ✓** {+ Da die Benutzung von WhatsApp einfach und schnell abläuft, und man stets verbunden sein möchte und sich austauschen will, entscheidet man sich evtl. für Whatsapp +}

**F2:** Gibt es etwas, das Sie bei einigen Apps stört?

    A2: Schlechte Changelogs und zu viel Werbung.
    B2: Zu viel Werbung
    C2: Mangelhafter Datenschutz

**Hypothese: ✗** {- Es werden wahrscheinlich die Menge der Werbung sowie die Notwendigkeit einer Anmeldung kritisiert -}

**F3:** Welche von diesen Apps finden Sie am besten?
    
    A3: Youtube
    B3: Spotify
    C3: Facebook Messenger
    

**F4:** Wie finden Sie dessen Benutzeroberfläche (positives und negatives) ?
    
    A4: Man bleibt immer auf dem neusten Stand, oft gute 
        Videoqualität. Leider sieht man die Dislikes und oft auch 
        die Kommentare nicht. 
    B4: Okay
    C4: Leicht zu bedienen, Intuitiv und hat gute Features. 
        Leider kann man alte Medien in Chats nicht löschen.

**F5:** Haben Sie vielleicht Vorschläge wie man die Benutzeroberfläche optimieren kann?
    
    A5: Dislike Anzeige einfügen und Kommentare besser hervorheben.
    B5: Nein
    C5: Medien löschen

**F6:** Wie wichtig ist es Ihnen andere Spieler kennenzulernen und mit Ihnen zu interagieren?
    
    A6: Es ist schon wichtig, denn so macht es mehr Spaß und man 
        kann dadurch gute Freundschaften aufbauen.
    B6: Die meisten Spiele sind erst in der Gemeinschaft    
        interessant.
    C6: Aufgrund der menschlichen Interkation, ist es mir besonders 
        wichtig mit anderen zu Spielen - es macht einfach Spaß.

**F7:** Wenn Sie neue Mitspieler suchen, wie gehen Sie vor?

    A7: Gucke ob welche da sind (TeamSpeak) oder ich schreibe die 
        persönlich an.
    B7: Ich frage meine Freunde und dann suchen wir uns Games aus.
    C7: Ich suche auf Discord-Server von Streamern und diversen  
        Gemeinschaften, nach Personen mit gemeinsamen Interessen.

**F8** Welche Probleme haben Sie bei der Suche nach Mitspielern, falls vorhanden?

    A8: Viele sind toxisch und manchmal findet man auch gar keinen.
    B8: Man findet nicht direkt Mitspieler wenn man gerade lust hat.
    C8: Manchmal ist es schwer Mitspieler zu finden die meine
        Denkweise teilen und es ist auch schwer Menschen ohne  
        Discord zu finden. Mann muss aktiv suchen.

**F9:** Welche Erfahrungen haben Sie mit Mitspielern gemacht?

    A9: Oft sehr gute, jedoch in manchen Fällen nervige (Trolle). 
    B9: Ich habe League of Legends gespielt, also keine gute.
    C9: Angenehme konversationen über diverse Themen, aber leider 
        auch negative Interaktionen.

**F10:** Was müsste eine App können um ihnen dabei zu helfen?

    A10: Die soll eine Möglichkeit bieten die Menschen besser   
         einschätzen und schneller Mitspieler finden zu können.
    B10: Sie müsste mehr Daten erheben als mir lieb ist, dass sie 
         mich gut einschätzen kann.
    C10: Man sollte Spieler finden, Gruppen finden, Personen nach 
         Interessen gruppieren und Communities erstellen können.

### Szenariobeschreibung

Mein League of Legends Team waren mal wieder nur Noobs, die nur geflamed haben. Also suche ich mir lieber mal ein Team von kompetenten Mitspielern,
die das Spiel auch ernst nehmen und auch aufsteigen wollen. Auf Reddit ist die Werbung zu nervig, darum gehe ich lieber auf +rep.
Im Forum finde ich mehrere Posts, die mit LoL getaggt sind. Zwei davon sind Spielersuchen, bei denen es dem OP darum geht, ein seriöses Team aufzubauen.
Der eine OP hat jedoch eine stark negative Reputation.<br> Die Reputation des zweiten ist zwar auch negativ, aber ich sehe, dass er erst dreimal
bewertet wurde und zwei davon leicht positiv und nur eine stark negativ war. Also entscheide ich, ihm eine Chance zu geben und nehme ihn in
meine Kontakte auf.<br> Während ich darauf warte, dass er meine Steamfreundschaftsanfrage annimmt, erstelle ich selbst einen Forumpost, und schreibe,
dass ich eine kompetente Gruppe aufbauen will. Und weil ich in der Vergangenheit schon die Erfahung gemacht habe, dass es manche Männer stört,
mit einer Frau zu spielen, schreibe ich auch, dass ich eine Frau bin und sich nur Leute melden zu brauchen, die damit keine Probleme haben.<br>
Ich tagge den Post mit dem Spiel LoL und mit #Spielersuche, schicke ihn ab und warte auf Reaktionen.

### Anforderungen

**Funktionale Anforderung**<br>
Die App muss es dem Nutzer erlauben, die öffentlichen Posts nach Tags zu filtern.

**Qualitätsanforderung**<br>
Die App muss den korrekten und aktuellen Steam Nickname anzeigen.

### Transformation/Quantifizierung

Die App muss den Nickname **einmal pro Minute** in der **Steam API abfragen.**
