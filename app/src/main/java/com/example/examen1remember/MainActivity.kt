package com.example.examen1remember
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.examen1remember.ui.PantallaLoteria
import com.example.examen1remember.ui.PantallaVacia
import com.example.examen1remember.ui.theme.Examen1RememberTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Examen1RememberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Principal()
                }
            }
        }
    }
}

enum class PrincipalScreen(@StringRes val title: Int) {
    Pantalla1(title = R.string.p1),
    Pantalla2(title = R.string.p2),
}

@Composable
fun Principal(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    NavHost(
        navController = navController,
        startDestination = PrincipalScreen.Pantalla1.name,
        //modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = PrincipalScreen.Pantalla1.name) {
            PantallaLoteria { navController.navigate(PrincipalScreen.Pantalla2.name) }
        }
        composable(route = PrincipalScreen.Pantalla2.name) {
            PantallaVacia()
        }
    }
}








