package com.example.vaccinibiologici

//uso _ perchè - non è ammesso nei nomi delle variabili in Kotlin
//L'etichetta è la forma leggibile che viene usata nell'interfaccia.
enum class Terapia(val etichetta: String) {
    ANTI_TNF("Anti-TNF"),
    ANTI_IL17("Anti-IL17"),
    ANTI_IL23("Anti-IL23"),
    ALTRI_IMMUNOSOPPRESSORI("Altri immunosoppressori")
}