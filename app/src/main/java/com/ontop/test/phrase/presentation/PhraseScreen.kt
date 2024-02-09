package com.ontop.test.phrase.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PhraseScreen(
    viewModel: PhraseViewModel = hiltViewModel(),
) {
    val phrase by viewModel.phrase.collectAsState()
    val parsedPhrase by viewModel.parsedPhrase.collectAsState()
    PhraseContent(
        phrase = phrase,
        parsedPhrase = parsedPhrase,
        onPhraseChange = viewModel::inputPhrase,
        onClick = viewModel::parse
    )
}

@Composable
fun PhraseContent(
    modifier: Modifier = Modifier,
    phrase: String,
    parsedPhrase: String,
    onPhraseChange: (phrase: String) -> Unit,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp)
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        TextField(
            value = phrase,
            onValueChange = onPhraseChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
        )

        Button(
            onClick = onClick,
        ) {
            Text(
                text = "Parse",
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = parsedPhrase
        )
    }
}
