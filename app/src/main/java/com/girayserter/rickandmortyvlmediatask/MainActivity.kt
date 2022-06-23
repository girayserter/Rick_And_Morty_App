package com.girayserter.rickandmortyvlmediatask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.girayserter.rickandmortyvlmediatask.ui.characterDetailPage.CharacterDetailPage

import com.girayserter.rickandmortyvlmediatask.ui.characters.CharactersPage
import com.girayserter.rickandmortyvlmediatask.ui.theme.RickAndMortyVLMediaTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyVLMediaTaskTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "characters_page"
                ) {
                    composable("characters_page") {
                        CharactersPage(navController)
                    }
                    composable(
                        "character_detail_page/{characterId}",
                        arguments = listOf(
                            navArgument("characterId") {
                                type = NavType.IntType
                            }
                        )
                    ) {
                        val characterId = remember {
                            it.arguments?.getInt("characterId") ?: 1

                        }
                        CharacterDetailPage(characterId = characterId,navController)
                    }
                }
            }
        }
    }
}