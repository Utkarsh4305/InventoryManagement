package com.example.inventorymanagement.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.* // For layout components
import androidx.compose.foundation.background // For background color
import androidx.compose.foundation.clickable // For clickable actions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.* // For material design components
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment // For alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp // For defining sizes
import androidx.compose.ui.unit.sp // For defining font sizes
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.inventorymanagement.Navigation.Screen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) } // State to control dialog visibility

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Text at the top
        Text(
            text = "Fric Bergen",
            color = Color.White,
            fontSize = 48.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )

        // Center content (if any)
        Text(
            text = "Stock Management System",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Center)
        )

        // Column to hold the buttons at the bottom
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            // Row for Finished Goods and Raw Material buttons
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                // Finished Goods Button
                Button(
                    onClick = { navController.navigate(Screen.FinishedGood.route) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF707070)), // Lighter blue for contrast
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .height(60.dp) // Increased height for more impact
                ) {
                    Icon(
                        imageVector = Icons.Default.Storage,
                        contentDescription = "Finished Goods",
                        tint = Color.White,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = "Finished Goods", color = Color.White, fontSize = 20.sp)
                }

                // Raw Material Button
                Button(
                    onClick = { navController.navigate(Screen.RawMaterial.route) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF707070)), // Yellow for attention
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .height(60.dp) // Increased height for more impact
                ) {
                    Icon(
                        imageVector = Icons.Default.ProductionQuantityLimits,
                        contentDescription = "Raw Material",
                        tint = Color.White,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = "Raw Material", color = Color.White, fontSize = 20.sp)
                }
            }

            // Logout Button
            Button(
                onClick = { showDialog = true }, // Show dialog on click
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F1F1F)), // Red color for logout
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(text = "Log Out", color = Color.White, fontSize = 20.sp)
            }
        }
    }

    // Confirmation Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Log out and navigate to LoginScreen
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate(Screen.LoginScreen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                        showDialog = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            },
            title = { Text("Log Out") },
            text = { Text("Are you sure you want to log out?") }
        )
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    // Preview with a mock NavController
    HomeScreen(navController = rememberNavController())
}