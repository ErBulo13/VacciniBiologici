package com.example.vaccinibiologici

//Rappresenta un vaccino presente nel catalogo dell'applicazione.

data class Vaccino(
    val nome: String,
    //utilizzo il flag vivoAttenuato per evidenziare quali vaccini rientrano in questa categoria
    val vivoAttenuato: Boolean,
    //Se il paziente che non ha mai ricevuto questa vaccinazione
    val schedaSomministrazione: String = "",
    //Se il vaccino è presente nella storia vaccinale
    val schedaRichiamo: String = "",
    val regoleEta: List<RegolaEta> = emptyList(),
    val regolePatologia: List<RegolaPatologia> = emptyList(),
    val regoleTerapia: List<RegolaTerapia> = emptyList()
)