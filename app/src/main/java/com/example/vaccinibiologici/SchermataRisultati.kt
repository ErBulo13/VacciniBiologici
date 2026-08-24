package com.example.vaccinibiologici

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

//Mostra l'esito della valutazione per ogni vaccino del catalogo.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchermataRisultati(paziente: Paziente) {
    val risultati = MotoreRegole.valuta(paziente)
    val risultatiOrdinati = ordinaPerPriorita(risultati)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "RACCOMANDAZIONI VACCINALI",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            Surface(color = Color(0xFFFFFDE7)) {
                Text(
                    text = "*  =  Vaccinazione già effettuata secondo la storia vaccinale inserita.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    ) { spazioInterno ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spazioInterno)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            for (indice in risultatiOrdinati.indices) {
                SchedaRisultato(risultatiOrdinati[indice])
                if (indice < risultatiOrdinati.size - 1) {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                }
            }
        }
    }
}

//Scheda di un singolo vaccino: nome, classificazione, motivazioni che l'hanno
//determinata ed eventuali note prodotte da criteri superati.
@Composable
private fun SchedaRisultato(risultato: RisultatoValutazione) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = risultato.vaccino.nome,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        val marcatore = if (risultato.giaEffettuato) " *" else ""
        Text(
            text = risultato.classificazione.name + marcatore,
            color = coloreClassificazione(risultato.classificazione),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        if (risultato.motivazioni.isNotEmpty()) {
            Text(
                text = "Motivazioni:",
                fontSize = 16.sp
            )
            for (motivazione in risultato.motivazioni) {
                Text(text = "• $motivazione")
            }
        }

        if (risultato.note.isNotEmpty()) {
            Text(
                text = "Note:",
                fontSize = 16.sp
                )
            for (nota in risultato.note) {
                Text(text = "• $nota")
            }
        }
    }
}

//L'ordine di visualizzazione è: CONTROINDICATI, RACCOMANDATI, POSSIBILI.
private fun ordinaPerPriorita(
    risultati: List<RisultatoValutazione>
): List<RisultatoValutazione> {
    val ordinati = mutableListOf<RisultatoValutazione>()

    for (risultato in risultati){
        if (risultato.classificazione == Classificazione.CONTROINDICATO) {
            ordinati.add(risultato)
        }
    }
    for (risultato in risultati) {
        if (risultato.classificazione == Classificazione.RACCOMANDATO) {
            ordinati.add(risultato)
        }
    }
    for (risultato in risultati) {
        if (risultato.classificazione == Classificazione.POSSIBILE){
            ordinati.add(risultato)
        }
    }

    return ordinati
}

private fun coloreClassificazione(classificazione: Classificazione): Color{
    if (classificazione == Classificazione.CONTROINDICATO) {
        return Color(0xFFC62828)
    }
    if (classificazione == Classificazione.RACCOMANDATO) {
        return Color(0xFF388E3C)
    }
    return Color(0xFFF9A825)
}