package com.ontop.test.rickandmorty.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
        LazyVerticalStaggeredGrid(
            modifier = modifier.then(Modifier.fillMaxSize()),
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(6.dp),
            verticalItemSpacing = 6.dp,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(
                count = characters.itemCount,
            ) { index ->
                characters[index]?.let { character ->
                    CharacterItem(
                        imageUrl = character.image,
                        name = character.name,
                        lastLocation = character.location.name,
                        onClick = {
                            onCharacterSelected.invoke(character)
                        },
                    )
                }
            }
            characters.apply {
                when {
                    loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                LoadingScreen()
                            }
                        }

                    }

                    else -> Unit
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

