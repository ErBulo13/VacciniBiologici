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
fun AppVaccini() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "iniziale"
    ) {
        composable("iniziale") {
            SchermataIniziale(
                onIniziaClick = { navController.navigate("inserimento") }
            )
        }
        composable("inserimento") {
            SchermataInserimentoDati()
        }
    }
}

@Composable
fun SchermataIniziale(onIniziaClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Vaccini in Terapia Biologica")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onIniziaClick) {
            Text(text = "Inizia valutazione")
        }
    }
}