package com.example.inventorymanagement

import LaminateViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.inventorymanagement.Navigation.SetupNavGraph
import com.example.inventorymanagement.ui.theme.InventoryManagementTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    // Initialize the ViewModel
    private val laminateViewModel: LaminateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InventoryManagementTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Initialize NavController
                    val navController = rememberNavController()

                    // Get Firestore instance
                    val firestore = FirebaseFirestore.getInstance()

                    // Setup the navigation graph
                    SetupNavGraph(
                        navController = navController,
                        firestore = firestore
                    )
                }
            }
        }
    }
}
