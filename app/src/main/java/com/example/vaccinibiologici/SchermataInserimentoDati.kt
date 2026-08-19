package com.example.vaccinibiologici

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon

/*L'annotazione @OptIn serve per dichiarare che sono consapevole che sto utilizzando una funzionalità
* che è ancora in via sperimentale e potrebbe quindi ricevere nuove versioni.
* Qui la funzionalità sperimentale è ExposedDropdownMenuBox, API di Google*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchermataInserimentoDati() {
    var eta by remember { mutableStateOf("") }
    val patologieSelezionate = remember {mutableStateListOf<Patologia>() }
    var sezionePatologieAperta by remember {mutableStateOf(false) }
    var terapiaSelezionata by remember { mutableStateOf<Terapia?>(null) }
    val vacciniEffettuati = remember { mutableStateListOf<String>() }
    var sezioneVacciniAperta by remember { mutableStateOf(false)}
    var menuAperto by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text(text = "Inserimento dati paziente")
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = eta,
            onValueChange = { nuovoValore -> eta = nuovoValore },
            label = { Text("Età") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        /*Menù a tendina per la selezione della terapia.
        * Poichè i valori mostrati sono generati automaticamente dalle entries dell'enum Terapia, se si volessero
        * aggiungere nuove terapie non è necessario modificare questa schermata*/
        ExposedDropdownMenuBox(
            expanded = menuAperto,
            onExpandedChange = { menuAperto = it }
        ) {
            OutlinedTextField(
                value = terapiaSelezionata?.etichetta ?: "",
                onValueChange = { },
                readOnly = true,
                label = { Text("Terapia biologica") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = menuAperto) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            androidx.compose.material3.DropdownMenu(
                expanded = menuAperto,
                onDismissRequest = { menuAperto = false }
            ) {
                Terapia.entries.forEach { terapia ->
                    DropdownMenuItem(
                        text = { Text(terapia.etichetta) },
                        onClick = {
                            terapiaSelezionata = terapia
                            menuAperto = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Selezione multipla delle patologie concomitanti, in una sezione espandibile.
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { sezionePatologieAperta = !sezionePatologieAperta}
        ) {
            Text(text = "Patologie concomitanti (${patologieSelezionate.size})")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Apri o chiudi l'elenco delle patologie"
            )
        }

        if (sezionePatologieAperta) {
            for (patologia in Patologia.entries) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Checkbox(
                        checked = patologia in patologieSelezionate,
                        onCheckedChange = { selezionata ->
                            if(selezionata) {
                                patologieSelezionate.add(patologia)
                            } else{
                                patologieSelezionate.remove(patologia)
                            }
                        }
                    )
                    Text(text = patologia.etichetta)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        //Selezione multipla dei vaccini già effettuati, in una sezione espandibile.
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { sezioneVacciniAperta = !sezioneVacciniAperta}
        ){
            Text(text = "Vaccinazioni già effettuate (${vacciniEffettuati.size})")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Apri o chiudi l'elenco delle vaccinazioni"
            )
        }

        if (sezioneVacciniAperta){
            for (vaccino in DatabaseVaccini.vaccini) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Checkbox(
                        checked = vaccino.nome in vacciniEffettuati,
                        onCheckedChange = { selezionato ->
                            if (selezionato) {
                                vacciniEffettuati.add(vaccino.nome)
                            } else {
                                vacciniEffettuati.remove(vaccino.nome)
                            }
                        }
                    )
                    Text(text = vaccino.nome)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* logica di calcolo da inserire  */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calcola raccomandazioni")
        }
    }
}