package com.example.vaccinibiologici

//Intervallo di età con estremi opzionali.
//Un estremo a null vuol dire "nessun limite da quel lato", così è possibile
//rappresentare i 4 casi possibili: nessun vincolo, età minima,
//età massima, età compresa tra 2 valori.

data class FasciaEta(
    val minima: Int? = null,
    val massima: Int? = null
)

{
    fun contiene(eta: Int): Boolean {
        val sopraLaMinima = minima == null || eta >= minima
        val sottoLaMassima = massima == null || eta <= massima
        return sopraLaMinima && sottoLaMassima
    }
}