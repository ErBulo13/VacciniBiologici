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

//La terapia null indica una regola valida per tutte le terapie. Implementato per
//evitare di inserire la controindicazione 4 volte per ogni vaccino vivo attenuato.
data class RegolaTerapia(
    val terapia: Terapia?,
    val classificazione: Classificazione,
    val motivazione: String
)