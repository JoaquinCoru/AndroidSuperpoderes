package com.joaquinco.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.joaquinco.marvelapp.ui.characterList.CharacterListScreen
import com.joaquinco.marvelapp.ui.components.Screens
import com.joaquinco.marvelapp.ui.detailList.DetailListScreen
import com.joaquinco.marvelapp.ui.theme.MarvelAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screens.Home.route) {
                        composable(Screens.Home.route) {
                            CharacterListScreen() { id ->
                                navController.navigate(Screens.Detail.createRoute(id))
                            }
                        }

                        composable(
                            Screens.Detail.route, arguments = listOf(
                                navArgument(Screens.Detail.ARG_ID) {
                                    type = NavType.StringType
                                })
                        ) { backStackEntry ->
                            val id =
                                backStackEntry.arguments?.getString(Screens.Detail.ARG_ID) ?: ""

                            DetailListScreen(id)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarvelAppTheme {
        Greeting("Android")
    }
}