package com.example.vaccinibiologici

data class Paziente(
    val eta: Int,
    val terapia: Terapia,
    val patologie: List<String>
    //da aggiungere la gestione della storia vaccinale
)