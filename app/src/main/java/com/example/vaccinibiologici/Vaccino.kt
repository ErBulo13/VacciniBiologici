package com.example.vaccinibiologici

data class Vaccino(
    val nome: String,
    //utlizzo il flag vivoAttenuato per evidenziare quali vaccini rientrano in questa categoria
    val vivoAttenuato: Boolean
)