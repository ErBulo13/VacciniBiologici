package com.example.vaccinibiologici

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Elenca le fonti da cui provengono le regole seguite per le
//raccomandazioni vaccinali.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchermataFonti() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title= {
                    Text(
                        text = "FONTI",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors (
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor =MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) {spazioInterno ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spazioInterno)
                .verticalScroll(rememberScrollState() )
                .padding(24.dp)
        ) {
            Text(
                text = "Le indicazioni vaccinali applicate dall'applicazione derivano dai seguenti riferimenti.",
                fontSize = 18.sp
            )
            Spacer(modifier =Modifier.height(24.dp))

            Text(
                text = "Linee guida EULAR",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text= "2019 update of EULAR recommendations for vaccination in adult patients with autoimmune inflammatory rheumatic diseases.",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Linee guida CDC",
                fontSize =18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Adult Immunization Schedule by Age and by Medical Condition, Centers for Disease Control and Prevention.",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Linee guida nazionali",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text ="Piano Nazionale di Prevenzione Vaccinale 2023-2025, Ministero della Salute.",
                fontSize = 16.sp
            )
            Spacer(modifier= Modifier.height(20.dp))

            Text(
                text = "Società scientifiche",
                fontSize = 18.sp,
                fontWeight =FontWeight.Bold
            )
            Text(
                text = "Raccomandazioni della Società Italiana di Reumatologia e dell'American College of Rheumatology per la vaccinazione dei pazienti in terapia immunosoppressiva.",
                fontSize = 16.sp
            )

        }
    }
}