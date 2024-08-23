package com.example.inventorymanagement.screen

import LaminateViewModel
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import com.example.inventorymanagement.Navigation.Screen
import java.io.File
import java.io.FileWriter
import java.io.IOException

@Composable
fun RawMaterialScreen(
    navController: NavHostController,
    viewModel: LaminateViewModel,
    activityContext: Context
) {
    val buttonHeight = 60.dp
    val buttonSpacing = 8.dp
    var showDialog by remember { mutableStateOf(false) }
    var temporaryData by remember { mutableStateOf<Map<String, Any>>(emptyMap()) }
    val context = viewModel.getApplicationContext().applicationContext

    LaunchedEffect(Unit) {
        viewModel.fetchTemporaryData(
            onSuccess = { data ->
                temporaryData = data
                Log.d("RawMaterialScreen", "Temporary data fetched successfully: $data")
            },
            onFailure = { exception ->
                Log.e("RawMaterialScreen", "Error fetching temporary data", exception)
            }
        )
    }

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
                text = "Raw Material",
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
                Text("Confirm RM", fontSize = 19.sp)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate(Screen.Laminates.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Laminates", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate(Screen.CorrugatedBoxes.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Corrugated Box", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate(Screen.SpicesAndSeasoning.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Spices & Seasonings", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate(Screen.Labels.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Labels", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate(Screen.Monocartons.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Monocartons", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate(Screen.OleoresinsAndFlavours.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Oleoresins & Flavours", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate(Screen.BottlesClosuresPouches.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Bottles, Closures, Pouches", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate(Screen.GeneralIngredients.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("General Ingredients", color = Color.Black, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate(Screen.PulpsPastesPuree.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .padding(vertical = buttonSpacing)
        ) {
            Text("Pulps, Pastes, Puree", color = Color.Black, fontSize = 16.sp)
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

fun generateCsvFile(context: Context, data: Map<String, Map<String, Any>>): File? {
    return try {
        val csvFile = File(context.getExternalFilesDir(null), "ProductsData.csv")
        FileWriter(csvFile).use { writer ->
            writer.append("ID,Key,Value\n")
            data.forEach { (id, fields) ->
                fields.forEach { (key, value) ->
                    writer.append("$id,$key,$value\n")
                }
            }
        }
        Log.d("generateCsvFile", "CSV file created at: ${csvFile.absolutePath}")
        csvFile
    } catch (e: IOException) {
        Log.e("generateCsvFile", "Error creating CSV file", e)
        null
    }
}

fun shareCsvFileViaWhatsApp(activity: Context, csvFile: File) {
    val uri: Uri = FileProvider.getUriForFile(
        activity,
        "${activity.packageName}.provider",
        csvFile
    )

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/csv"
        putExtra(Intent.EXTRA_STREAM, uri)
        putExtra(Intent.EXTRA_TEXT, "Please find the attached products data CSV file.")
        setPackage("com.whatsapp")  // Set the package to WhatsApp
    }

    try {
        activity.startActivity(Intent.createChooser(intent, "Share CSV file via WhatsApp"))
        Log.d("shareCsvFileViaWhatsApp", "Chooser launched with WhatsApp option")
    } catch (e: ActivityNotFoundException) {
        Log.e("shareCsvFileViaWhatsApp", "WhatsApp not installed", e)
        Toast.makeText(activity, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Log.e("shareCsvFileViaWhatsApp", "Error sharing CSV file", e)
        Toast.makeText(activity, "Error sharing CSV file", Toast.LENGTH_SHORT).show()
    }
}