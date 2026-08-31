# Vaccini in Terapia Biologica

Applicazione Android per il supporto decisionale sulle indicazioni vaccinali nei pazienti in terapia biologica.

## Cosa fa

Dati in ingresso l'età del paziente, la terapia biologica in corso, le eventuali patologie concomitanti e la storia vaccinale, l'applicazione classifica trenta vaccini come **raccomandati**, **possibili** o **controindicati**, motivando ogni esito e indicando lo schema di somministrazione quando il vaccino risulta raccomandato.

L'inserimento della terapia è obbligatorio: l'applicazione valuta esclusivamente pazienti in terapia immunosoppressiva, e tutte le indicazioni del catalogo sono riferite a questa popolazione, non a quella generale.

## Struttura del progetto

Il codice è organizzato in tre livelli indipendenti: il modello dati non conosce l'interfaccia, il motore non conosce come i risultati vengono mostrati.

### Modello dati

| File | Contenuto                                                                                       |
|---|-------------------------------------------------------------------------------------------------|
| `Terapia.kt` | Le quattro classi di terapia: anti-TNF, anti-IL17, anti-IL23, altri immunosoppressori           |
| `Patologia.kt` | Le tredici condizioni cliniche concomitanti considerate                                         |
| `Classificazione.kt` | I tre esiti possibili, con il livello di severità usato per risolvere i conflitti               |
| `FasciaEta.kt` | Intervallo di età con estremi opzionali, per rappresentare tutti i tipi di vincolo              |
| `Vaccino.kt` | Un vaccino con la sua formulazione, le regole che lo governano e gli schemi di somministrazione |
| `Paziente.kt` | Il caso clinico considerato su cui il motore lavora                                             |
| `Regole.kt` | I tre tipi di regola — età, patologia, terapia — e l'esito che producono                        |
| `RisultatoValutazione.kt` | L'esito della valutazione di un singolo vaccino                                                 |

### Logica

| File | Contenuto |
|---|---|
| `DatabaseVaccini.kt` | Il catalogo dei trenta vaccini con le rispettive regole |
| `MotoreRegole.kt` | Applica le regole al paziente e produce le classificazioni |

### Interfaccia

| File | Contenuto |
|---|---|
| `MainActivity.kt` | Punto di ingresso e grafo di navigazione |
| `DatiPaziente.kt` | ViewModel che conserva i dati inseriti |
| `SchermataIniziale.kt` | Avvio della valutazione e accesso alle fonti |
| `SchermataInserimentoDati.kt` | Inserimento di età, terapia, patologie e storia vaccinale |
| `SchermataRisultati.kt` | Elenco dei vaccini classificati |
| `SchermataFonti.kt` | Riferimenti da cui derivano le regole |

## Il motore delle regole

Ogni vaccino del catalogo porta con sé tre liste di regole, una per criterio. Ciascuna regola lega una condizione a una classificazione e alla motivazione da mostrare.

La valutazione procede in due fasi distinte.

**Raccolta.** Si valutano tutti i criteri applicabili e si mette da parte ogni esito prodotto, senza deciderne ancora nessuno.

**Risoluzione.** Fra gli esiti raccolti prevale quello con severità maggiore, secondo l'ordine `CONTROINDICATO > POSSIBILE > RACCOMANDATO`. Ordine scelto seguendo un principio di sicurezza: l'applicazione non deve mai presentare come raccomandato un vaccino che un altro criterio segnala come rischioso.

Le motivazioni degli esiti vincenti spiegano la classificazione; quelle degli esiti superati restano visibili come note, così l'informazione non va persa. Un vaccino su cui nessuna regola si attiva riceve `POSSIBILE` come esito predefinito.

Scelta progettuale: nessuna regola emette `POSSIBILE` per indicare assenza di informazione. L'assenza si esprime non scrivendo la regola. Quando una regola emette `POSSIBILE` sta quindi sempre affermando una cautela clinica attiva, e quindi prevale su una raccomandazione.

## Il catalogo

Trenta vaccini, di cui diciannove non vivi e undici vivi attenuati.

Vaccini che colpiscono la stessa malattia ma hanno formulazioni diverse sono voci distinte, perché ricevono classificazioni opposte: ESEMPIO: il vaccino antinfluenzale inattivato è raccomandato mentre quello intranasale è controindicato.

Le regole specifiche per terapia sono tre, tutte relative agli anti-TNF: l'assenza di ulteriori regole specifiche per terapia è data dall'assenza di differenziazione fra classi di biologici nelle linee guida.

## Fonti

- Raccomandazioni EULAR per la vaccinazione nei pazienti adulti con malattie autoimmuni infiammatorie reumatiche, aggiornamento 2019
- CDC Adult Immunization Schedule, tabelle per fascia di età e per condizione medica, e relative note
- Piano Nazionale di Prevenzione Vaccinale 2023-2025
- Raccomandazioni delle società scientifiche di riferimento

Le fasce di età derivano inoltre dalle indicazioni autorizzate dei singoli vaccini.

## Requisiti tecnici

- Kotlin con Jetpack Compose e Material 3
- Navigation Compose per la navigazione fra schermate
- ViewModel per la persistenza dei dati durante la sessione
- SDK minimo: API 24