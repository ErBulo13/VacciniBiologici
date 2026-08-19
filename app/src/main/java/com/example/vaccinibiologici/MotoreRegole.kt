package com.example.vaccinibiologici

//Applica le regole del catalogo a un paziente e produce la classificazione
//di ogni vaccino.
object MotoreRegole {

    //Valuta l'intero catalogo per il paziente indicato.
    fun valuta(paziente: Paziente): List<RisultatoValutazione> {
        val risultati = mutableListOf<RisultatoValutazione>()
        for (vaccino in DatabaseVaccini.vaccini) {
            risultati.add(valutaVaccino(vaccino, paziente))
        }
        return risultati
    }

    //Valuta un singolo vaccino raccogliendo gli esiti di tutti i criteri
    //applicabili e risolvendo gli eventuali conflitti per severità.
    private fun valutaVaccino(vaccino: Vaccino, paziente: Paziente): RisultatoValutazione {
        val esiti = raccogliEsiti(vaccino, paziente)
        val giaEffettuato = vaccino.nome in paziente.vacciniGiaEffettuati

        //Caso in cui non ci sono indicazioni esplicite per un vaccino.
        if (esiti.isEmpty()) {
            return RisultatoValutazione(
                vaccino = vaccino,
                classificazione = Classificazione.POSSIBILE,
                giaEffettuato = giaEffettuato
            )
        }

        //Cerca la severità più alta tra gli esiti raccolti.
        var severitaMassima = 0
        for (esito in esiti) {
            if (esito.classificazione.severita > severitaMassima){
                severitaMassima = esito.classificazione.severita
            }
        }

        //Separa le motivazioni degli esiti vincenti da quelle degli esiti superati,
        //che vengono aggiunti alle note.
        val motivazioni = mutableListOf<String>()
        val note = mutableListOf<String>()
        var classificazioneFinale = Classificazione.POSSIBILE

        for (esito in esiti) {
            if (esito.classificazione.severita == severitaMassima) {
                motivazioni.add(esito.motivazione)
                classificazioneFinale = esito.classificazione
            } else{
                note.add(esito.motivazione)
            }
        }

        return RisultatoValutazione(
            vaccino = vaccino,
            classificazione = classificazioneFinale,
            motivazioni = motivazioni,
            note = note,
            giaEffettuato = giaEffettuato
        )
    }

    //Raccoglie gli esiti di tutte le regole che si attivano per questo paziente.
    private fun raccogliEsiti(vaccino: Vaccino, paziente: Paziente): List<Esito> {
        val esiti = mutableListOf<Esito>()

        for (regola in vaccino.regoleEta){
            if (regola.fascia.contiene(paziente.eta)) {
                esiti.add(Esito(regola.classificazione, regola.motivazione))
            }
        }

        for (regola in vaccino.regolePatologia) {
            if (regola.patologia in paziente.patologie){
                esiti.add(Esito(regola.classificazione, regola.motivazione))
            }
        }

        for (regola in vaccino.regoleTerapia) {
            if (regola.terapia == null || regola.terapia == paziente.terapia) {
                esiti.add(Esito(regola.classificazione, regola.motivazione))
            }
        }

        return esiti
    }
}