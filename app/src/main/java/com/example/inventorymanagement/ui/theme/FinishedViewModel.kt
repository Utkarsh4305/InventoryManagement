package com.example.inventorymanagement.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FinishedViewModel(application: Application) : AndroidViewModel(application) {

    private val firestore = FirebaseFirestore.getInstance()

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
    }

    // Function to fetch initial laminate data from Products collection


    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchFinishedBottlesTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("FinishedData")
                    .document("FinishedBottles")
                    .get()
                    .await()

                if (document.exists()) {
                    onSuccess(document.data ?: emptyMap())
                } else {
                    onSuccess(emptyMap())
                }
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    // Function to update Laminate data in the temporaryData collection
    fun updateBulkPackDataTemp(
        data: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                firestore.collection("FinishedData")
                    .document("BulkPack")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchBulkPackTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("FinishedData")
                    .document("BulkPack")
                    .get()
                    .await()

                if (document.exists()) {
                    onSuccess(document.data ?: emptyMap())
                } else {
                    onSuccess(emptyMap())
                }
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    // Function to update Laminate data in the temporaryData collection
    fun updatePastaSaucesDataTemp(
        data: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                firestore.collection("FinishedData")
                    .document("PastaSauces")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchPastaSaucesTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("FinishedData")
                    .document("PastaSauces")
                    .get()
                    .await()

                if (document.exists()) {
                    onSuccess(document.data ?: emptyMap())
                } else {
                    onSuccess(emptyMap())
                }
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    // Function to update Laminate data in the temporaryData collection
    fun updatePortionPackDataTemp(
        data: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                firestore.collection("FinishedData")
                    .document("PortionPack")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchPortionPackTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("FinishedData")
                    .document("PortionPack")
                    .get()
                    .await()

                if (document.exists()) {
                    onSuccess(document.data ?: emptyMap())
                } else {
                    onSuccess(emptyMap())
                }
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    // Function to update Laminate data in the temporaryData collection
    fun updateSaucesDataTemp(
        data: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                firestore.collection("FinishedData")
                    .document("Sauces")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchSaucesTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("FinishedData")
                    .document("Sauces")
                    .get()
                    .await()

                if (document.exists()) {
                    onSuccess(document.data ?: emptyMap())
                } else {
                    onSuccess(emptyMap())
                }
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to update Laminate data in the temporaryData collection
    fun updateSeasoningDataTemp(
        data: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                firestore.collection("FinishedData")
                    .document("Seasoning")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchSeasoningTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("FinishedData")
                    .document("Seasoning")
                    .get()
                    .await()

                if (document.exists()) {
                    onSuccess(document.data ?: emptyMap())
                } else {
                    onSuccess(emptyMap())
                }
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to update Laminate data in the temporaryData collection
    fun updateFinishedBottlesDataTemp(
        data: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                firestore.collection("FinishedData")
                    .document("FinishedBottles")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    fun getApplicationContext(): Application {
        return getApplication()
    }
    fun exportProductsData(
        onSuccess: (Map<String, Map<String, Any>>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                // Fetch all documents from the Products collection
                val productsCollection = firestore.collection("FinishedData").get().await()

                // Prepare data to be exported
                val productsData = mutableMapOf<String, Map<String, Any>>()
                productsCollection.forEach { document ->
                    productsData[document.id] = document.data
                }

                // Notify success with fetched data
                onSuccess(productsData)
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

}







