package com.example.vaccinibiologici

//Indicazioni delle linee guida vaccinali riguardo la somministrazione di un vaccino
//in base ai 3 criteri: fascia d'età, patologie e terapia seguita.

data class RegolaEta(
    val fascia: FasciaEta,
    val classificazione: Classificazione,
    val motivazione: String
)

data class RegolaPatologia(
    val patologia: Patologia,
    val classificazione: Classificazione,
    val motivazione: String
)

data class RegolaTerapia(
    val terapia: Terapia,
    val classificazione: Classificazione,
    val motivazione: String
)