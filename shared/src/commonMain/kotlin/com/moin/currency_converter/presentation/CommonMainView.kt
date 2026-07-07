package com.moin.currency_converter.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moin.currency_converter.style.Palette
import com.squareup.sqldelight.db.SqlDriver
import dev.tmapps.konnection.Konnection

@Composable
fun commonView(sqlDriver: SqlDriver, konnection: Konnection) {

    val viewModel = CurrencyViewModel(sqlDriver = sqlDriver, konnection = konnection)

    MaterialTheme(
        MaterialTheme.colors.copy(
            primary = Palette.DarkBlue
        )
    ) {
        if (konnection.isConnected()) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                MainScreen(viewModel)
            }
        } else {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Internet is not available, Please connect with internet, then close and relaunch app"
                    )
                }
            }

        }

    }
}