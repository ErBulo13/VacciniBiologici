package com.example.vaccinibiologici

//Catalogo dei vaccini gestiti dall'applicazione.
//La lista è basata sui vaccini citati dalle linee guida EULAR, CDC e
//Piano Nazionale di Prevenzione Vaccinale 2023-2025
object DatabaseVaccini {

    val vaccini: List<Vaccino> = listOf(
        //Vaccini non-vivi: generalmente somministrabili in terapia biologica
        Vaccino(nome = "Antinfluenzale (inattivato)", vivoAttenuato = false),
        Vaccino(nome = "Pneumococcico", vivoAttenuato = false),
        Vaccino(nome = "Epatite A", vivoAttenuato = false),
        Vaccino(nome = "Epatite B", vivoAttenuato = false),
        Vaccino(nome = "Tetano-Difterite-Pertosse (dTpa)", vivoAttenuato = false),
        Vaccino(nome = "Meningococco ACWY", vivoAttenuato = false),
        Vaccino(nome = "Herpes Zoster ricombinante (Shingrix)", vivoAttenuato = false),
        Vaccino(nome = "COVID-19", vivoAttenuato = false),

        //Vaccini vivi attenuati: generalmente controindicati in terapia biologica
        Vaccino(nome = "Morbillo-Parotite-Rosolia (MPR)", vivoAttenuato = true),
        Vaccino(nome = "Varicella", vivoAttenuato = true),
        Vaccino(nome = "Herpes Zoster vivo (Zostavax)", vivoAttenuato = true),
        Vaccino(nome = "BCG (antitubercolare)", vivoAttenuato = true),
        Vaccino(nome = "Tifo orale (Ty21a)", vivoAttenuato = true),
    )
}