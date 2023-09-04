package com.beeldi.beelding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.beeldi.beelding.ui.equipment_list.EquipmentListViewModel
import com.beeldi.beelding.ui.theme.BeeldingTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.beeldi.beelding.ui.equipment_detail.EquipmentDetailView
import com.beeldi.beelding.ui.equipment_list.EquipmentListView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeeldingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BeeldingNavHost()
                }
            }
        }
    }
}

@Composable
fun BeeldingNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "equipment_list"
){
    val listRoute = stringResource(id = R.string.list_route)
    val detailRoute = stringResource(id = R.string.detail_route)
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier.fillMaxSize()
    ) {

        composable(listRoute) {
            EquipmentListView(navController = navController)
        }
        composable(
            route = detailRoute,
            arguments = listOf(navArgument("key") { type = NavType.StringType })
        ) { backStackEntry ->
            val equipmentKey = backStackEntry.arguments?.getString("key") ?: ""
            EquipmentDetailView(equipmentKey = equipmentKey){
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BeeldingTheme {
        Greeting("Android")
    }
}