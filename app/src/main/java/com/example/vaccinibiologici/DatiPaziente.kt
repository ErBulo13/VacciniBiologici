package com.example.vaccinibiologici

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

//Conserva i dati inseriti.
class DatiPaziente : ViewModel() {

    var eta by mutableStateOf("")
    var terapiaSelezionata by mutableStateOf<Terapia?>(null)
    val patologieSelezionate = mutableStateListOf<Patologia>()
    val vacciniEffettuati = mutableStateListOf<String>()
    var paziente by mutableStateOf<Paziente?>(null)

    //Costruisce il paziente con i dati inseriti.
    //I valori di ripiego sono necessari perché Paziente non accetta età e
    //terapia nulle, ma non vengono mai usati poiché il pulsante che chiama questa
    //funzione è attivo solo quando entrambi i dati sono stati inseriti.
    fun creaPaziente() {
        paziente = Paziente(
            eta = eta.toIntOrNull() ?: 0,
            terapia = terapiaSelezionata ?: Terapia.ANTI_TNF,
            patologie = patologieSelezionate.toSet(),
            vacciniGiaEffettuati = vacciniEffettuati.toSet()
        )
    }

    fun azzeraDati() {
        eta = ""
        terapiaSelezionata = null
        patologieSelezionate.clear()
        vacciniEffettuati.clear()
    }
}