package com.example.vaccinibiologici

//Catalogo dei vaccini gestiti dall'applicazione.
//La lista è basata sui vaccini citati dalle linee guida EULAR, CDC e
//Piano Nazionale di Prevenzione Vaccinale 2023-2025.
//Un vaccino senza regole riceve POSSIBILE come esito di default: la
//valutazione finale rimane al medico.

object DatabaseVaccini {

    val vaccini: List<Vaccino> = listOf(

        Vaccino(
            nome = "Antinfluenzale inattivato",
            vivoAttenuato = false,
            regoleEta = listOf(
                RegolaEta(
                    fascia = FasciaEta(),
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato nei pazienti in terapia immunosoppressiva"
                )
            ),
            regolePatologia = listOf(
                RegolaPatologia(
                    patologia = Patologia.CARDIOPATIA_CRONICA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in cardiopatia cronica"
                ),
                RegolaPatologia(
                    patologia = Patologia.MALATTIA_POLMONARE_CRONICA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in pneumopatia cronica"
                ),
                RegolaPatologia(
                    patologia = Patologia.DIABETE,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in diabete mellito"
                ),
                RegolaPatologia(
                    patologia = Patologia.GRAVIDANZA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in gravidanza"
                )
            )
        ),

        Vaccino(
            nome = "Pneumococcico coniugato (PCV20)",
            vivoAttenuato = false,
            regoleEta = listOf(
                RegolaEta(
                    fascia = FasciaEta(minima = 18),
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato negli adulti immunocompromessi"
                )
            ),
            regolePatologia = listOf(
                RegolaPatologia(
                    patologia = Patologia.ASPLENIA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in asplenia/iposplenia"
                ),
                RegolaPatologia(
                    patologia = Patologia.INSUFFICIENZA_RENALE_CRONICA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in insufficienza renale cronica"
                ),
                RegolaPatologia(
                    patologia = Patologia.EPATOPATIA_CRONICA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in epatopatia cronica"
                ),
                RegolaPatologia(
                    patologia = Patologia.FISTOLA_LIQUORALE_O_IMPIANTO_COCLEARE,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in fistola liquorale o impianto cocleare"
                ),
                RegolaPatologia(
                    patologia = Patologia.TRAPIANTO_ORGANO_SOLIDO,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato nel trapianto d'organo solido"
                )
            )
        ),

        Vaccino(
            nome = "Pneumococcico polisaccaridico (PPSV23)",
            vivoAttenuato = false,
            regoleEta = listOf(
                RegolaEta(
                    fascia = FasciaEta(minima = 65),
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato dai 65 anni"
                )
            ),
            regolePatologia = listOf(
                RegolaPatologia(
                    patologia = Patologia.ASPLENIA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in asplenia/iposplenia"
                ),
                RegolaPatologia(
                    patologia = Patologia.HIV,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in infezione da HIV"
                )
            )
        ),

        Vaccino(
            nome = "COVID-19",
            vivoAttenuato = false,
            regoleEta = listOf(
                RegolaEta(
                    fascia = FasciaEta(),
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato nei pazienti in terapia immunosoppressiva"
                )
            )
        ),

        Vaccino(
            nome = "Herpes Zoster ricombinante (Shingrix)",
            vivoAttenuato = false,
            regoleEta = listOf(
                RegolaEta(
                    fascia = FasciaEta(minima = 18),
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato dai 18 anni nei pazienti immunocompromessi"
                )
            ),
            regolePatologia = listOf(
                RegolaPatologia(
                    patologia = Patologia.GRAVIDANZA,
                    classificazione = Classificazione.POSSIBILE,
                    motivazione = "Vaccinazione differibile in gravidanza"
                )
            ),
            regoleTerapia = listOf(
                RegolaTerapia(
                    terapia = Terapia.ANTI_TNF,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Rischio di herpes zoster aumentato in terapia anti-TNF"
                )
            )
        ),

        Vaccino(
            nome = "Epatite B",
            vivoAttenuato = false,
            regolePatologia = listOf(
                RegolaPatologia(
                    patologia = Patologia.INSUFFICIENZA_RENALE_CRONICA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in insufficienza renale cronica"
                ),
                RegolaPatologia(
                    patologia = Patologia.EPATOPATIA_CRONICA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in epatopatia cronica"
                ),
                RegolaPatologia(
                    patologia = Patologia.DIABETE,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in diabete mellito"
                ),
                RegolaPatologia(
                    patologia = Patologia.HIV,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in infezione da HIV"
                )
            ),
            regoleTerapia = listOf(
                RegolaTerapia(
                    terapia = Terapia.ANTI_TNF,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato nei pazienti HBV-negativi"
                )
            )
        ),

        Vaccino(
            nome = "Epatite A",
            vivoAttenuato = false,
            regolePatologia = listOf(
                RegolaPatologia(
                    patologia = Patologia.EPATOPATIA_CRONICA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in epatopatia cronica"
                ),
                RegolaPatologia(
                    patologia = Patologia.HIV,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in infezione da HIV"
                )
            )
        ),

        Vaccino(
            nome = "Difterite-Tetano-Pertosse (dTpa)",
            vivoAttenuato = false,
            regoleEta = listOf(
                RegolaEta(
                    fascia = FasciaEta(),
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato per la popolazione generale"
                )
            ),
            regolePatologia = listOf(
                RegolaPatologia(
                    patologia = Patologia.GRAVIDANZA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in gravidanza"
                )
            )
        ),

        Vaccino(
            nome = "HPV (Papillomavirus)",
            vivoAttenuato = false,
            regoleEta = listOf(
                RegolaEta(
                    fascia = FasciaEta(massima = 26),
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato fino a 26 anni"
                ),
                RegolaEta(
                    fascia = FasciaEta(minima = 27, massima = 45),
                    classificazione = Classificazione.POSSIBILE,
                    motivazione = "Valutabile tra 27 e 45 anni"
                )
            ),
            regolePatologia = listOf(
                RegolaPatologia(
                    patologia = Patologia.HIV,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in infezione da HIV"
                ),
                RegolaPatologia(
                    patologia = Patologia.TRAPIANTO_ORGANO_SOLIDO,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato nel trapianto d'organo solido"
                ),
                RegolaPatologia(
                    patologia = Patologia.GRAVIDANZA,
                    classificazione = Classificazione.POSSIBILE,
                    motivazione = "Vaccinazione rimandabile al termine della gravidanza"
                )
            )
        ),

        Vaccino(
            nome = "Meningococco ACWY",
            vivoAttenuato = false,
            regolePatologia = listOf(
                RegolaPatologia(
                    patologia = Patologia.ASPLENIA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in asplenia/iposplenia"
                ),
                RegolaPatologia(
                    patologia = Patologia.DEFICIT_COMPLEMENTO,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in deficit del complemento"
                ),
                RegolaPatologia(
                    patologia = Patologia.HIV,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in infezione da HIV"
                )
            )
        ),

        Vaccino(
            nome = "Meningococco B",
            vivoAttenuato = false,
            regolePatologia = listOf(
                RegolaPatologia(
                    patologia = Patologia.ASPLENIA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in asplenia/iposplenia"
                ),
                RegolaPatologia(
                    patologia = Patologia.DEFICIT_COMPLEMENTO,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in deficit del complemento"
                ),
                RegolaPatologia(
                    patologia = Patologia.GRAVIDANZA,
                    classificazione = Classificazione.POSSIBILE,
                    motivazione = "Differibile al termine della gravidanza"
                )
            )
        ),

        Vaccino(
            nome = "Haemophilus influenzae tipo b",
            vivoAttenuato = false,
            regolePatologia = listOf(
                RegolaPatologia(
                    patologia = Patologia.ASPLENIA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in asplenia/iposplenia"
                )
            )
        ),

        Vaccino(
            nome = "Poliomielite inattivato (IPV)",
            vivoAttenuato = false
        ),

        Vaccino(
            nome = "Virus Respiratorio Sinciziale (RSV)",
            vivoAttenuato = false,
            regoleEta = listOf(
                RegolaEta(
                    fascia = FasciaEta(minima = 50),
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato dai 50 anni nei soggetti a rischio aumentato di malattia grave"
                )
            ),
            regolePatologia = listOf(
                RegolaPatologia(
                    patologia = Patologia.CARDIOPATIA_CRONICA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in cardiopatia cronica"
                ),
                RegolaPatologia(
                    patologia = Patologia.MALATTIA_POLMONARE_CRONICA,
                    classificazione = Classificazione.RACCOMANDATO,
                    motivazione = "Raccomandato in pneumopatia cronica"
                )
            )
        ),

        Vaccino(
            nome = "Encefalite da zecche (TBE)",
            vivoAttenuato = false
        ),

        Vaccino(
            nome = "Rabbia",
            vivoAttenuato = false
        ),

        Vaccino(
            nome = "Febbre tifoide parenterale (Vi)",
            vivoAttenuato = false
        ),

        Vaccino(
            nome = "Encefalite giapponese",
            vivoAttenuato = false
        ),

        Vaccino(
            nome = "Mpox (MVA-BN)",
            vivoAttenuato = false
        ),

    )
}