// MainActivity.kt
package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "mainHome") {
                        composable("mainHome") { MainHomeScreen(navController) }
                        composable("home") { HomeScreen(navController) }
                        composable("signIn") { LogInScreen(navController) }
                        composable("reservation") { ReservationScreen(navController) }
                        composable("confirmation") { ConfirmationScreen(navController) }
                        composable("signUp") { SignInScreen(navController) }
                        composable("cart") { CartScreen(navController) }
                        composable("greeksalad_specific") { GreekSaladSpecificScreen(navController) }
                        composable("bruschetta_specific") { BruschettaSpecificScreen(navController) }
                        composable("lemondessert_specific") { LemonDessertSpecificScreen(navController) }
                    }
                }
            }
        }
    }
}
