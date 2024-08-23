package com.example.inventorymanagement.AdminScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun ProductUpload(navController: NavHostController) {
    var selectedCollection by remember { mutableStateOf("") }
    var selectedDocument by remember { mutableStateOf("") }
    var fields by remember { mutableStateOf<Map<String, Any>>(emptyMap()) }
    var newFieldName by remember { mutableStateOf("") }
    var newFieldValue by remember { mutableStateOf("") }
    val collections = listOf("FinishedData", "temporaryData") // Replace with actual collection names

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()), // Make the screen scrollable
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Dropdown for selecting the collection
            DropdownSelector(
                label = "Select Collection",
                options = collections,
                selectedOption = selectedCollection,
                onOptionSelected = { selectedCollection = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown for selecting the document (once a collection is selected)
            if (selectedCollection.isNotEmpty()) {
                DocumentDropdown(
                    collection = selectedCollection,
                    selectedDocument = selectedDocument,
                    onDocumentSelected = { document ->
                        selectedDocument = document
                        // Fetch fields when document is selected
                        scope.launch {
                            fetchDocumentFields(selectedCollection, selectedDocument) { fetchedFields ->
                                fields = fetchedFields
                            }
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Display fields of the selected document
            fields.forEach { field ->
                FieldRow(
                    fieldName = field.key,
                    fieldValue = field.value.toString(),
                    onDelete = {
                        deleteField(selectedCollection, selectedDocument, field.key) {
                            fields = fields.minus(field.key)
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Add new field
            TextField(
                value = newFieldName,
                onValueChange = { newFieldName = it },
                label = { Text("New Field Name") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = newFieldValue,
                onValueChange = { newFieldValue = it },
                label = { Text("New Field Value (Number)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (newFieldName.isNotEmpty() && newFieldValue.isNotEmpty()) {
                        val valueAsNumber = newFieldValue.toDoubleOrNull()
                        if (valueAsNumber != null) {
                            addField(selectedCollection, selectedDocument, newFieldName, valueAsNumber) {
                                fields = fields.plus(newFieldName to valueAsNumber)
                            }
                        } else {
                            println("Error: Value is not a number")
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Add Field", color = Color.White, fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun FieldRow(fieldName: String, fieldValue: String, onDelete: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "$fieldName: $fieldValue", color = Color.White, modifier = Modifier.weight(1f))
        IconButton(onClick = { showDialog = true }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Field", tint = Color.Red)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        onDelete()
                        showDialog = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            },
            title = { Text("Delete Field") },
            text = { Text("Are you sure you want to delete this field?") },
            containerColor = Color.Gray,
            //contentColor = Color.White
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentDropdown(
    collection: String,
    selectedDocument: String,
    onDocumentSelected: (String) -> Unit
) {
    var documents by remember { mutableStateOf<List<String>>(emptyList()) }
    var expanded by remember { mutableStateOf(false) }

    // Fetch documents whenever the collection changes
    LaunchedEffect(collection) {
        documents = fetchDocumentsInCollection(collection)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedDocument,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Document") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            documents.forEach { document ->
                DropdownMenuItem(
                    text = { Text(document) },
                    onClick = {
                        onDocumentSelected(document)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSelector(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

// Firestore functions

suspend fun fetchDocumentsInCollection(collection: String): List<String> {
    return try {
        val db = FirebaseFirestore.getInstance()
        val snapshot = db.collection(collection).get().await()

        // Log the number of documents found
        println("Documents in $collection: ${snapshot.documents.size}")

        // Return the document IDs
        snapshot.documents.map { it.id }
    } catch (e: Exception) {
        // Log the error
        println("Error fetching documents from $collection: ${e.message}")
        emptyList()
    }
}


suspend fun fetchDocumentFields(
    collection: String,
    document: String,
    onFieldsFetched: (Map<String, Any>) -> Unit
) {
    try {
        val db = FirebaseFirestore.getInstance()
        val docSnapshot = db.collection(collection).document(document).get().await()
        onFieldsFetched(docSnapshot.data ?: emptyMap())
    } catch (e: Exception) {
        onFieldsFetched(emptyMap())
    }
}

fun addField(
    collection: String,
    document: String,
    fieldName: String,
    fieldValue: Any,
    onFieldAdded: () -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    db.collection(collection).document(document)
        .update(fieldName, fieldValue)
        .addOnSuccessListener { onFieldAdded() }
        .addOnFailureListener { println("Error adding field: $it") }
}

fun deleteField(
    collection: String,
    document: String,
    fieldName: String,
    onFieldDeleted: () -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    db.collection(collection).document(document)
        .update(fieldName, FieldValue.delete())
        .addOnSuccessListener { onFieldDeleted() }
        .addOnFailureListener { println("Error deleting field: $it") }
}
