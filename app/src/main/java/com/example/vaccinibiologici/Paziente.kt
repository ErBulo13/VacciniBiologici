package com.example.vaccinibiologici

//Rappresenta il paziente da valutare.
//Contiene i dati clinici necessari all'applicazione delle linee guida vaccinali:
//età, terapia biologica in corso, patologie concomitanti e storia vaccinale.

data class Paziente(
    val eta: Int,
    val terapia: Terapia,
    val patologie: Set<Patologia> = emptySet(),
    val vacciniGiaEffettuati: Set<String> = emptySet()
)