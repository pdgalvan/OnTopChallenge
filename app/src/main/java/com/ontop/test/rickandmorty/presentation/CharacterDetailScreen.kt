package com.ontop.test.rickandmorty.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ontop.test.R
import com.ontop.test.rickandmorty.domain.model.Character
import com.ontop.test.ui.theme.Typography

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterViewModel,
) {
    val character by viewModel.characterSelected.collectAsState()

    character?.let { selected ->
        CharacterDetailContent(
            modifier = Modifier.fillMaxSize(),
            character = selected,
        )
    }
}

@Composable
fun CharacterDetailContent(
    modifier: Modifier = Modifier,
    character: Character,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(character.image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth(),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = character.name,
                style = Typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )
            DetailItem(
                label = stringResource(R.string.label_status),
                information = character.status,
            )
            DetailItem(
                modifier = Modifier.padding(vertical = 8.dp),
                label = stringResource(R.string.label_gender),
                information = character.gender,
            )
            DetailItem(
                label = stringResource(R.string.label_species),
                information = character.species,
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(R.string.episodes_count, character.episode.size),
                style = Typography.bodyLarge.copy(
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }
}

@Composable
fun DetailItem(
    modifier: Modifier = Modifier,
    label: String,
    information: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = Typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
            )
        )
        Text(
            text = information,
            style = Typography.bodyLarge
        )
    }
}


