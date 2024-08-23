package com.example.inventorymanagement.screen


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import com.example.inventorymanagement.Navigation.Screen
import com.example.inventorymanagement.ui.theme.FinishedViewModel
import java.io.File
import java.io.FileWriter
import java.io.IOException

@Composable
fun FinishedGoodScreen(navController: NavHostController,
                       viewModel: FinishedViewModel,
                       activityContext: Context) {
    // Adjust button height and spacing as needed
    var showDialog by remember { mutableStateOf(false) }
    var temporaryData by remember { mutableStateOf<Map<String, Any>>(emptyMap()) }
    val context = viewModel.getApplicationContext().applicationContext
    val buttonHeight = 60.dp
    val buttonSpacing = 8.dp  // Increased spacing between buttons

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Finished Goods",
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Button(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF001F3F)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .defaultMinSize(minWidth = 120.dp)
                    .height(48.dp)
            ) {
                Text("Confirm FG", fontSize = 19.sp)
            }
        }
        Button(
            onClick = { navController.navigate(Screen.Sauces.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Sauces", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate(Screen.Seasonings.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Seasoning", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate(Screen.Bottles.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Bottles", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate(Screen.PastaAndSauce.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Pasta 'N' Sauces", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate(Screen.PortionPack.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Portion Packs", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate(Screen.BulkPack.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Bulk Pack", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { Log.d("RawMaterialScreen", "Button clicked: Empty Button 1") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Empty Button 1", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { Log.d("RawMaterialScreen", "Button clicked: Empty Button 2") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Empty Button 2", color = Color.Black, fontSize = 16.sp)
        }


        // Spacer to push buttons to fill the remaining space
        Spacer(modifier = Modifier.weight(1f))
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Confirm RM Update") },
                text = { Text("Are you sure you want to confirm and update the data?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog = false

                            // Fetch data from Firestore and then generate and share CSV
                            viewModel.exportProductsData(
                                onSuccess = { productsData ->
                                    // Generate CSV file
                                    val csvFile = generateCsvFile(context, productsData)

                                    if (csvFile != null) {
                                        // Share the CSV file via WhatsApp
                                        shareCsvFileViaWhatsApp(activityContext, csvFile) // Pass activityContext
                                        Log.d(
                                            "RawMaterialScreen",
                                            "CSV file generated and shared successfully via WhatsApp"
                                        )
                                    } else {
                                        Log.e("RawMaterialScreen", "Failed to generate CSV file")
                                    }
                                },
                                onFailure = { exception ->
                                    Log.e(
                                        "RawMaterialScreen",
                                        "Error fetching products data",
                                        exception
                                    )
                                }
                            )
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FinsihedGoodScreenPreview() {
    //RawMaterialScreen()
}
