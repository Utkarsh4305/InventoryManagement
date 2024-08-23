package com.example.inventorymanagement.AdminScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun UserCreationScreen(navController: NavHostController) {
    var selectedUser by remember { mutableStateOf("") }
    var userFields by remember { mutableStateOf<Map<String, Any>>(emptyMap()) }
    var newUserName by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Dropdown for selecting the user
            DocumentDropdown(
                collection = "Users",
                selectedDocument = selectedUser,
                onDocumentSelected = { user ->
                    selectedUser = user
                    // Fetch fields (like Password) when user is selected
                    scope.launch {
                        fetchDocumentFields("Users", selectedUser) { fetchedFields ->
                            userFields = fetchedFields
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Display user fields with an edit option
            userFields.forEach { field ->
                if (field.key == "Password") {
                    EditableFieldRow(
                        fieldName = field.key,
                        fieldValue = field.value.toString(),
                        onUpdate = { newValue ->
                            updateField("Users", selectedUser, field.key, newValue) {
                                // Refresh the user fields after update
                                scope.launch {
                                    fetchDocumentFields("Users", selectedUser) { fetchedFields ->
                                        userFields = fetchedFields
                                    }
                                }
                            }
                        }
                    )
                } else {
                    // Handle other fields if necessary
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Add or Update User (Password Field)
            TextField(
                value = newUserName,
                onValueChange = { newUserName = it },
                label = { Text("New User Name") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down) // Move focus to the next TextField
                    }
                )
            )

            TextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text("New Password") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                //visualTransformation = PasswordVisualTransformation(),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus() // Hide the keyboard
                        // Trigger the add/update action
                        if (newUserName.isNotEmpty() && newPassword.isNotEmpty()) {
                            addUserOrUpdatePassword("Users", newUserName, newPassword) {
                                // Refresh screen after operation
                                scope.launch {
                                    fetchDocumentFields("Users", newUserName) { fetchedFields ->
                                        userFields = fetchedFields
                                        selectedUser = newUserName
                                    }
                                }
                                newUserName = ""
                                newPassword = ""
                            }
                        }
                    }
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (newUserName.isNotEmpty() && newPassword.isNotEmpty()) {
                        addUserOrUpdatePassword("Users", newUserName, newPassword) {
                            // Refresh screen after operation
                            scope.launch {
                                fetchDocumentFields("Users", newUserName) { fetchedFields ->
                                    userFields = fetchedFields
                                    selectedUser = newUserName
                                }
                            }
                            newUserName = ""
                            newPassword = ""
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Add User", color = Color.White, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (selectedUser.isNotEmpty()) {
                        deleteUser("Users", selectedUser) {
                            // Refresh screen after deletion
                            selectedUser = ""
                            userFields = emptyMap()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Delete User", color = Color.White, fontSize = 20.sp)
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableFieldRow(
    fieldName: String,
    fieldValue: String,
    onUpdate: (String) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var editedValue by remember { mutableStateOf(fieldValue) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.Black) // Set background color to black
    ) {
        if (isEditing) {
            TextField(
                value = editedValue,
                onValueChange = { editedValue = it },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    //backgroundColor = Color.Black,
                    //textColor = Color.Red,
                    cursorColor = Color.Red
                )
            )
            IconButton(onClick = {
                onUpdate(editedValue)
                isEditing = false
            }) {
                Icon(Icons.Default.Check, contentDescription = "Save", tint = Color.Red)
            }
        } else {
            Text(
                text = "$fieldName: $fieldValue",
                modifier = Modifier.weight(1f),
                color = Color.Red
            )
            IconButton(onClick = { isEditing = true }) {
                Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.Red)
            }
        }
    }
}

fun updateField(
    collection: String,
    userName: String,
    fieldName: String,
    newValue: String,
    onOperationComplete: () -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    db.collection(collection).document(userName)
        .update(fieldName, newValue)
        .addOnSuccessListener { onOperationComplete() }
        .addOnFailureListener { println("Error updating field: $it") }
}

fun addUserOrUpdatePassword(
    collection: String,
    userName: String,
    password: String,
    onOperationComplete: () -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    val userDoc = db.collection(collection).document(userName)

    userDoc.get().addOnSuccessListener { documentSnapshot ->
        if (documentSnapshot.exists()) {
            userDoc.update("Password", password)
                .addOnSuccessListener { onOperationComplete() }
                .addOnFailureListener { println("Error updating user: $it") }
        } else {
            val userData = hashMapOf(
                "Password" to password
            )
            userDoc.set(userData)
                .addOnSuccessListener { onOperationComplete() }
                .addOnFailureListener { println("Error adding user: $it") }
        }
    }.addOnFailureListener { println("Error checking user existence: $it") }
}

fun deleteUser(
    collection: String,
    userName: String,
    onOperationComplete: () -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    db.collection(collection).document(userName)
        .delete()
        .addOnSuccessListener { onOperationComplete() }
        .addOnFailureListener { println("Error deleting user: $it") }
}
