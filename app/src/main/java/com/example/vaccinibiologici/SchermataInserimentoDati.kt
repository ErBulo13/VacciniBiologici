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

@Composable
fun SchermataInserimentoDati() {
    var eta by remember { mutableStateOf("") }
    var patologie by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
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

        OutlinedTextField(
            value = patologie,
            onValueChange = { nuovoValore -> patologie = nuovoValore },
            label = { Text("Patologie concomitanti") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /*logica di calcolo da aggiungere*/ } ,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calcola raccomandazioni")
        }
    }
}