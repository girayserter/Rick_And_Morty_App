package com.girayserter.rickandmortyvlmediatask.ui.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.girayserter.rickandmortyvlmediatask.data.api.model.Result

@Composable
fun CharactersPage(
    navController: NavController
){
    val charactersPageViewModel: CharactersPageViewModel= hiltViewModel()
    val state by charactersPageViewModel.state.collectAsState()
    val lastPageLoaded by charactersPageViewModel.lastPageLoaded.collectAsState()

    LazyColumn(contentPadding = PaddingValues(16.dp)){
        if (!lastPageLoaded||state.isEmpty()){
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }

        val itemCount = if(state.size % 2 == 0) {
            state.size / 2
        } else {
            state.size / 2 + 1
        }
        if(lastPageLoaded) {
            items(itemCount) {
                CharactersRow(rowIndex = it, entries = state, navController = navController)
            }
        }
    }
}

@Composable
fun CharacterImageCard(character: Result, navController: NavController) {
    val imagePainter = rememberImagePainter(data = character.image)
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .clickable {
                navController.navigate(
                    "character_detail_page/${character.id}"
                )
            }
    ) {
        Box {

            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.FillBounds
            )

            Surface(
                color = MaterialTheme.colors.onSurface.copy(alpha = .9f),
                modifier = Modifier.align(Alignment.BottomCenter),
                contentColor = MaterialTheme.colors.surface
            ) {
                Column(
                    modifier = Modifier
                        .width(150.dp)
                        .padding(4.dp)
                ) {
                    Text(text = "${character.name}")
                }

            }
        }
    }
}

@Composable
fun CharactersRow(
    rowIndex: Int,
    entries: List<Result>,
    navController: NavController
) {
    Column {
        Row (
            modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            CharacterImageCard(
                character = entries[rowIndex * 2],
                navController = navController
            )
            Spacer(modifier = Modifier.width(16.dp))
            if(entries.size >= rowIndex * 2 + 2) {
                CharacterImageCard(
                    character = entries[rowIndex * 2 + 1],
                    navController = navController
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
