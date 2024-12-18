package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(
                modifier = Modifier,
                navigateToDetail = {
                    // setting this data here allows a value to be retrieved in the
                    // next screen
                    // but it also requires the parcelize decorator to serialize/deserialize between pages
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "cat",
                        it
                    ) // cat -> short for cat -> reference to retrieve details on the details screen
                    navController.navigate(Screen.DetailScreen.route)
                },
                viewState = viewState
            )
        }

        composable(route = Screen.DetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")
                    ?: Category("", "", "", "")

            CategoryDetailScreen(category)
        }
    }
}