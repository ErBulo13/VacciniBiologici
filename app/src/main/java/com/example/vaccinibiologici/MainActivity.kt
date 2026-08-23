package com.example.vaccinibiologici

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vaccinibiologici.ui.theme.VacciniBiologiciTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VacciniBiologiciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppVaccini()
                }
            }
        }
    }
}

@Composable
fun AppVaccini(datiPaziente: DatiPaziente = viewModel()) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "iniziale"
    ) {
        composable("iniziale") {
            SchermataIniziale(
                onIniziaClick = {
                    datiPaziente.azzeraDati()
                    navController.navigate("inserimento")
                }
            )
        }
        composable("inserimento") {
            SchermataInserimentoDati(
                eta = datiPaziente.eta,
                onEtaChange = { nuovoValore -> datiPaziente.eta = nuovoValore },
                terapiaSelezionata = datiPaziente.terapiaSelezionata,
                onTerapiaChange = { terapia -> datiPaziente.terapiaSelezionata = terapia },
                patologieSelezionate = datiPaziente.patologieSelezionate,
                vacciniEffettuati = datiPaziente.vacciniEffettuati,
                onResetClick = { datiPaziente.azzeraDati() },
                onCalcolaClick = {
                    datiPaziente.creaPaziente()
                    navController.navigate("risultati")
                }
            )
        }
        composable("risultati") {
            val pazienteCorrente = datiPaziente.paziente
            if (pazienteCorrente != null) {
                SchermataRisultati(paziente = pazienteCorrente)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchermataIniziale(onIniziaClick: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Vaccini in Terapia Biologica") })
        }
    ) { spazioInterno ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spazioInterno)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onIniziaClick) {
                Text(text = "Inizia valutazione")
            }
        }
    }
}