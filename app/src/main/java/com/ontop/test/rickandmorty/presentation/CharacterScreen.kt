package com.ontop.test.rickandmorty.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ontop.test.rickandmorty.domain.model.Character
import com.ontop.test.ui.component.LoadingScreen

@Composable
fun CharacterScreen(
    viewModel: CharacterViewModel,
    onCharacterSelected: () -> Unit,
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()
    val query by viewModel.name.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchTextField(
                query = query,
                onQueryChanged = viewModel::setQuery
            )
        }
    ) { innerPadding ->
        CharacterContent(
            modifier = Modifier.padding(innerPadding),
            characters = characters,
            onCharacterSelected = { character ->
                viewModel.selectCharacter(character)
                onCharacterSelected.invoke()
            },
        )
    }

}

@Composable
fun CharacterContent(
    modifier: Modifier = Modifier,
    characters: LazyPagingItems<Character>,
    onCharacterSelected: (character: Character) -> Unit,
) {
    var isLoadingScreenVisible by rememberSaveable { mutableStateOf(true) }
    if (isLoadingScreenVisible) {
        LoadingScreen()
    } else {
        LazyVerticalGrid(
            modifier = modifier.then(Modifier.fillMaxSize()),
            columns = GridCells.Fixed(2),
        ) {
            items(
                count = characters.itemCount,
            ) { index ->
                characters[index]?.let { character ->
                    CharacterItem(
                        modifier = Modifier.padding(6.dp),
                        imageUrl = character.image,
                        name = character.name,
                        lastLocation = character.location.name,
                        onClick = {
                            onCharacterSelected.invoke(character)
                        },
                    )
                }
            }
        }
    }

    characters.apply {
        when {
            loadState.refresh is LoadState.NotLoading -> {
                isLoadingScreenVisible = false
            }

            loadState.append is LoadState.Error -> {
                // show error
            }
        }
    }
}

