package com.example.vaccinibiologici

//Patologie concomitanti considerate dalle regole logiche.
//L'etichetta è la forma leggibile del valore, usata nell'interfaccia.

enum class Patologia(val etichetta: String) {
    ASPLENIA("Asplenia/iposplenia"),
    DEFICIT_COMPLEMENTO("Deficit del complemento"),
    INSUFFICIENZA_RENALE_CRONICA("Insufficienza renale cronica o Dialisi"),
    EPATOPATIA_CRONICA("Epatopatia cronica"),
    CARDIOPATIA_CRONICA("Cardiopatia cronica"),
    MALATTIA_POLMONARE_CRONICA("Pneumopatia cronica"),
    DIABETE("Diabete mellito"),
    HIV("Infezione da HIV"),
    GRAVIDANZA("Gravidanza"),
    FISTOLA_LIQUORALE_O_IMPIANTO_COCLEARE("Fistola liquorale o impianto cocleare"),
    NEOPLASIA("Neoplasia"),
    TUBERCOLOSI_LATENTE("Tubercolosi latente"),
    TRAPIANTO_ORGANO_SOLIDO("Trapianto d'organo solido")
}