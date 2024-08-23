package com.example.inventorymanagement.FinishedGoodsScreens


import LaminateViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.inventorymanagement.Navigation.Screen
import com.example.inventorymanagement.ui.theme.FinishedViewModel

@Composable
fun BottlesFinishedScreen(navController: NavHostController, viewModel: FinishedViewModel) {
    val scrollState = rememberScrollState()
    var bottlesData by remember { mutableStateOf<Map<String, Any>?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var updatedData by remember { mutableStateOf<Map<String, Any>>(emptyMap()) }

    // Fetch initial data from temporaryData collection
    LaunchedEffect(Unit) {
        viewModel.fetchFinishedBottlesTemporaryData(
            onSuccess = { data ->
                bottlesData = data
            },
            onFailure = { exception ->
                errorMessage = "Failed to load data: ${exception.message}"
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Glass Bottles",
                color = Color.Black,
                fontSize = 24.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Button(
                onClick = {
                    navController.navigate(Screen.FinishedGood.route)
                    viewModel.updateFinishedBottlesDataTemp(updatedData,
                        onSuccess = {
                            // Handle success, such as showing a toast or dialog
                        },
                        onFailure = { exception ->
                            errorMessage = "Failed to save data: ${exception.message}"
                        }
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF001F3F)),
                modifier = Modifier.height(48.dp)
            ) {
                Text(text = "Done", color = Color.White)
            }
        }

        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        bottlesData?.forEach { (key, value) ->
            ItemRowBottles(itemName = key, itemValue = value.toString()) { itemName, quantity ->
                updatedData = updatedData.toMutableMap().apply {
                    this[itemName] = quantity
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                navController.navigate(Screen.FinishedGood.route)
                viewModel.updateFinishedBottlesDataTemp(updatedData,
                    onSuccess = {
                        // Handle success, such as showing a toast or dialog
                    },
                    onFailure = { exception ->
                        errorMessage = "Failed to save data: ${exception.message}"
                    }
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF001F3F)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(48.dp)
        ) {
            Text(text = "Done", color = Color.White)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemRowBottles(itemName: String, itemValue: String, onQuantityChange: (String, Int) -> Unit) {
    var quantity by remember { mutableStateOf(itemValue.toIntOrNull() ?: 1) }
    var textFieldValue by remember { mutableStateOf(quantity.toString()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = itemName,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if (quantity > 1) {
                        quantity -= 1
                        textFieldValue = quantity.toString()
                        onQuantityChange(itemName, quantity)
                    }
                }
            ) {
                Text(text = "-", color = Color.Black, fontSize = 24.sp)
            }

            TextField(
                value = textFieldValue,
                onValueChange = { newValue ->
                    // Validate input to make sure it's a number and not empty
                    val newQuantity = newValue.toIntOrNull()
                    if (newQuantity != null && newQuantity > 0) {
                        textFieldValue = newValue
                        quantity = newQuantity
                        onQuantityChange(itemName, quantity)
                    } else if (newValue.isEmpty()) {
                        textFieldValue = ""
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .width(75.dp)
                    .background(Color.White, shape = MaterialTheme.shapes.small)
                    .padding(8.dp),
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            IconButton(
                onClick = {
                    quantity += 1
                    textFieldValue = quantity.toString()
                    onQuantityChange(itemName, quantity)
                }
            ) {
                Text(text = "+", color = Color.Black, fontSize = 24.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottlesScreenPreview() {
    //BottlesScreen( /* Pass the necessary parameters */ )
}
