package com.example.vaccinibiologici

//Esito della valutazione di un singolo vaccino per un dato paziente.
//Le motivazioni riportano la classificazione finale: CONTROINDICATO/POSSIBILE/RACCOMANDATO.
//Le note riportano informazioni che sono state superate nel corso della valutazione,
//ma che possono essere utili al medico.

data class RisultatoValutazione(
    val vaccino: Vaccino,
    val classificazione: Classificazione,
    val motivazioni: List<String> = emptyList(),
    val note: List<String> = emptyList(),
    val giaEffettuato: Boolean = false
)