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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle

/*L'annotazione @OptIn serve per dichiarare che sono consapevole che sto utilizzando una funzionalità
* che è ancora in via sperimentale e potrebbe quindi ricevere nuove versioni.
* Qui la funzionalità sperimentale è ExposedDropdownMenuBox, API di Google*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchermataInserimentoDati(
    eta: String,
    onEtaChange: (String) -> Unit,
    terapiaSelezionata: Terapia?,
    onTerapiaChange: (Terapia) -> Unit,
    patologieSelezionate: MutableList<Patologia>,
    vacciniEffettuati: MutableList<String>,
    onResetClick: () -> Unit,
    onCalcolaClick: () -> Unit
) {
    var sezionePatologieAperta by remember {mutableStateOf(false) }
    var ricercaPatologie by remember { mutableStateOf("")}
    var sezioneVacciniAperta by remember { mutableStateOf(false)}
    var ricercaVaccini by remember { mutableStateOf("") }
    var menuAperto by remember { mutableStateOf(false) }
    val etaNumerica = eta.toIntOrNull()
    //L'errore compare solo se l'utente ha digitato qualcosa.
    val etaNonValida = eta.isNotEmpty() && (etaNumerica == null || etaNumerica < 0 || etaNumerica > 120)
    val datiValidi = etaNumerica != null && etaNumerica >= 0 && etaNumerica <= 120 && terapiaSelezionata != null
    val gestoreFocus = LocalFocusManager.current
    //Controlla se l'utente ha aperto il menù a tendina: se è stato aperto e non è stata selezionata
    //una terapia, compare il messaggio d'errore.
    var menuTerapiaVisitato by remember {mutableStateOf(false) }
    val terapiaNonValida = menuTerapiaVisitato && terapiaSelezionata == null

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "DATI PAZIENTE",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { spazioInterno ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spazioInterno)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            OutlinedTextField(
                value = eta,
                onValueChange = {nuovoValore -> onEtaChange(nuovoValore) },
                label = { Text("Età") },
                textStyle = TextStyle(fontSize = 16.sp),
                isError = etaNonValida,
                supportingText = {
                    if (etaNonValida) {
                        Text( "Inserire un'età valida")
                    }
                },
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
                    textStyle = TextStyle(fontSize = 16.sp),
                    isError = terapiaNonValida,
                    supportingText = {
                        if (terapiaNonValida) {
                            Text("Selezionare una terapia" )
                        }
                    },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = menuAperto) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                androidx.compose.material3.DropdownMenu(
                    expanded = menuAperto,
                    onDismissRequest = {
                        menuAperto = false
                        menuTerapiaVisitato = true
                    }
                ) {
                    Terapia.entries.forEach { terapia ->
                        DropdownMenuItem(
                            text = { Text(terapia.etichetta) },
                            onClick = {
                                onTerapiaChange(terapia)
                                menuAperto = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            //Selezione multipla delle patologie concomitanti, in una sezione espandibile,
            //con barra di ricerca aggiuntiva.
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        gestoreFocus.clearFocus()
                        sezionePatologieAperta = !sezionePatologieAperta
                    }
            ) {
                Text(
                    text = "Patologie concomitanti (${patologieSelezionate.size})",
                    fontSize = 16.sp
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Apri o chiudi l'elenco delle patologie"
                )
            }

            if (sezionePatologieAperta) {
                OutlinedTextField(
                    value = ricercaPatologie,
                    onValueChange = { nuovoValore -> ricercaPatologie = nuovoValore },
                    label = { Text("Cerca")},
                    modifier = Modifier.fillMaxWidth()
                )

                for (patologia in Patologia.entries) {
                    if (patologia.etichetta.contains(ricercaPatologie, ignoreCase = true)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Checkbox(
                                checked = patologia in patologieSelezionate,
                                onCheckedChange = { selezionata ->
                                    gestoreFocus.clearFocus()
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
            }

            Spacer(modifier = Modifier.height(24.dp))

            //Selezione multipla dei vaccini già effettuati, in una sezione espandibile,
            //con barra di ricerca aggiuntiva.
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        gestoreFocus.clearFocus()
                        sezioneVacciniAperta = !sezioneVacciniAperta
                    }
            ){
                Text(
                    text = "Vaccinazioni già effettuate (${vacciniEffettuati.size})",
                    fontSize = 16.sp
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Apri o chiudi l'elenco delle vaccinazioni"
                )
            }

            if (sezioneVacciniAperta){
                OutlinedTextField(
                    value = ricercaVaccini,
                    onValueChange = { nuovoValore -> ricercaVaccini = nuovoValore },
                    label = { Text("Cerca") },
                    modifier = Modifier.fillMaxWidth()
                )

                for (vaccino in DatabaseVaccini.vaccini){
                    if (vaccino.nome.contains(ricercaVaccini, ignoreCase = true)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Checkbox(
                                checked = vaccino.nome in vacciniEffettuati,
                                onCheckedChange = { selezionato ->
                                    gestoreFocus.clearFocus()
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
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    gestoreFocus.clearFocus()
                    onCalcolaClick()
                },
                enabled = datiValidi,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Calcola raccomandazioni",
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = {
                    gestoreFocus.clearFocus()
                    menuTerapiaVisitato = false
                    ricercaPatologie = ""
                    ricercaVaccini= ""
                    onResetClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Azzera campi",
                    fontSize = 18.sp
                )
            }
        }
    }
}