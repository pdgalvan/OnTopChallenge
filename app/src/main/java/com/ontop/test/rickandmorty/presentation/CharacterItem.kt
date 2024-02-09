package com.ontop.test.rickandmorty.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ontop.test.R
import com.ontop.test.ui.theme.Typography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String,
    lastLocation: String,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(
            topStart = 12.dp,
            topEnd = 12.dp,
            bottomStart = 4.dp,
            bottomEnd = 4.dp

        ),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = "character image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth(),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                text = name,
                style = Typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

            Text(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 4.dp,
                ),
                style = Typography.titleMedium,
                text = stringResource(R.string.last_location)
            )
            Text(
                text = lastLocation,
                style = Typography.titleMedium,
            )
        }
    }
}





