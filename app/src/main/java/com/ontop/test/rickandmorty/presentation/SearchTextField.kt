package com.ontop.test.rickandmorty.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ontop.test.R

@Composable
fun SearchTextField(
    query: String,
    onQueryChanged: (newQuery: String) -> Unit,
) {
    var isClearIconVisible by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(query) {
        if (query.isEmpty()) {
            isClearIconVisible = false
        } else if (query.isNotEmpty()) {
            isClearIconVisible = true
        }
    }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        value = query,
        onValueChange = onQueryChanged,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "search icon"
            )
        },
        trailingIcon = {
            if (isClearIconVisible) {
                IconButton(onClick = {
                    onQueryChanged.invoke("")
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "clear icon"
                    )
                }
            }
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        shape = CircleShape,
        maxLines = 1,
        placeholder = { Text(text = stringResource(R.string.hint_search_query)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    )
}