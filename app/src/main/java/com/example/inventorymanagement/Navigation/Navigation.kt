package com.example.inventorymanagement.Navigation

import LaminateViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inventorymanagement.RawMaterialScreens.LaminateScreen
import com.google.firebase.firestore.FirebaseFirestore
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventorymanagement.AdminScreens.ProductUpload
import com.example.inventorymanagement.AdminScreens.UserCreationScreen
import com.example.inventorymanagement.FinishedGoodsScreens.BottlesFinishedScreen
import com.example.inventorymanagement.FinishedGoodsScreens.BulkPackScreen
import com.example.inventorymanagement.FinishedGoodsScreens.PastaSaucesScreen
import com.example.inventorymanagement.FinishedGoodsScreens.PortionPackScreen
import com.example.inventorymanagement.FinishedGoodsScreens.SaucesScreen
import com.example.inventorymanagement.FinishedGoodsScreens.SeasoningsScreen
import com.example.inventorymanagement.RawMaterialScreens.BottlesScreen
import com.example.inventorymanagement.RawMaterialScreens.CorrugatedScreen
import com.example.inventorymanagement.RawMaterialScreens.FlavoursScreen
import com.example.inventorymanagement.RawMaterialScreens.GeneralScreen
import com.example.inventorymanagement.RawMaterialScreens.LabelsScreen
import com.example.inventorymanagement.RawMaterialScreens.MonocartonsScreen
import com.example.inventorymanagement.RawMaterialScreens.PulpsScreen
import com.example.inventorymanagement.RawMaterialScreens.SpicesScreen
import com.example.inventorymanagement.screen.AdminScreen
import com.example.inventorymanagement.screen.FinishedGoodScreen
import com.example.inventorymanagement.screen.HomeScreen
import com.example.inventorymanagement.screen.LoginScreen
import com.example.inventorymanagement.screen.RawMaterialScreen
import com.example.inventorymanagement.ui.theme.FinishedViewModel
import com.google.firebase.auth.FirebaseAuth


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    firestore: FirebaseFirestore,
) {
    val activityContext = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val startDestination = if (auth.currentUser != null) {
        Screen.HomeScreen.route
    } else {
        Screen.LoginScreen.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()
    ){
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Laminates.route) {
            val viewModel: LaminateViewModel = viewModel()
            LaminateScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.BottlesClosuresPouches.route) {
            val viewModel: LaminateViewModel = viewModel()
            BottlesScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.CorrugatedBoxes.route) {
            val viewModel: LaminateViewModel = viewModel()
            CorrugatedScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.GeneralIngredients.route) {
            val viewModel: LaminateViewModel = viewModel()
            GeneralScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.Labels.route) {
            val viewModel: LaminateViewModel = viewModel()
            LabelsScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.Monocartons.route) {
            val viewModel: LaminateViewModel = viewModel()
            MonocartonsScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.OleoresinsAndFlavours.route) {
            val viewModel: LaminateViewModel = viewModel()
            FlavoursScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.PulpsPastesPuree.route) {
            val viewModel: LaminateViewModel = viewModel()
            PulpsScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.SpicesAndSeasoning.route) {
            val viewModel: LaminateViewModel = viewModel()
            SpicesScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController, onLoginSuccess = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.LoginScreen.route) { inclusive = true }
                }
            })
        }

        composable(route = Screen.RawMaterial.route) {
            val viewModel: LaminateViewModel = viewModel()
            RawMaterialScreen(
                navController = navController,
                viewModel = viewModel,
                activityContext = activityContext
            )
        }
        composable(route = Screen.FinishedGood.route) {
            val viewModel: FinishedViewModel = viewModel()
            FinishedGoodScreen(navController =navController , viewModel = viewModel,activityContext = activityContext)
        }
        composable(route = Screen.OleoresinsAndFlavours.route) {
            val viewModel: LaminateViewModel = viewModel()
            FlavoursScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.Bottles.route) {
            val viewModel1: FinishedViewModel = viewModel()
            BottlesFinishedScreen(navController = navController, viewModel = viewModel1)
        }
        composable(route = Screen.BulkPack.route) {
            val viewModel1: FinishedViewModel = viewModel()
            BulkPackScreen(navController = navController, viewModel = viewModel1)
        }
        composable(route = Screen.PastaAndSauce.route) {
            val viewModel1: FinishedViewModel = viewModel()
            PastaSaucesScreen(navController = navController, viewModel = viewModel1)
        }
        composable(route = Screen.PortionPack.route) {
            val viewModel1: FinishedViewModel = viewModel()
            PortionPackScreen(navController = navController, viewModel = viewModel1)
        }
        composable(route = Screen.Sauces.route) {
            val viewModel1: FinishedViewModel = viewModel()
            SaucesScreen(navController = navController, viewModel = viewModel1)
        }
        composable(route = Screen.Seasonings.route) {
            val viewModel1: FinishedViewModel = viewModel()
            SeasoningsScreen(navController = navController, viewModel = viewModel1)
        }
        composable(route = Screen.AdminScreen.route) {
            AdminScreen(navController = navController)
        }
        composable(route = Screen.ProductUploadScreen.route) {
            ProductUpload(navController = navController)
        }
        composable(route = Screen.UserCreationScreen.route) {
            UserCreationScreen(navController = navController)
        }
    }
}
