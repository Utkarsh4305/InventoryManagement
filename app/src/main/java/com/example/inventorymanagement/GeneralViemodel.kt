import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.launch


class LaminateViewModel(application: Application) : AndroidViewModel(application) {

    private val firestore = FirebaseFirestore.getInstance()

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
    }

    // Function to fetch initial laminate data from Products collection
    fun fetchLaminateData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("Products")
                    .document("Laminate")
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

    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("temporaryData")
                    .document("Laminate")
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

    // Function to update Laminate data in the Products collection
    fun updateLaminateData(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("Products")
                    .document("Laminate")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    // Function to update Laminate data in the temporaryData collection
    fun updateLaminateDataTemp(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("temporaryData")
                    .document("Laminate")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch initial laminate data from Products collection
    fun fetchBottlesData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("Products")
                    .document("Bottles")
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

    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchBottlesTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("temporaryData")
                    .document("Bottles")
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

    // Function to update Laminate data in the Products collection
    fun updateBottlesData(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("Products")
                    .document("Bottles")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    // Function to update Laminate data in the temporaryData collection
    fun updateBottlesDataTemp(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("temporaryData")
                    .document("Bottles")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch initial laminate data from Products collection
    fun fetchCorrugatedData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("Products")
                    .document("Corrugated")
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

    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchCorrugatedTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("temporaryData")
                    .document("Corrugated")
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

    // Function to update Laminate data in the Products collection
    fun updateCorrugatedData(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("Products")
                    .document("Corrugated")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    // Function to update Laminate data in the temporaryData collection
    fun updateCorrugatedDataTemp(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("temporaryData")
                    .document("Corrugated")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch initial laminate data from Products collection
    fun fetchGeneralData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("Products")
                    .document("General")
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

    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchGeneralTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("temporaryData")
                    .document("General")
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

    // Function to update Laminate data in the Products collection
    fun updateGeneralData(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("Products")
                    .document("General")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    // Function to update Laminate data in the temporaryData collection
    fun updateGeneralDataTemp(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("temporaryData")
                    .document("General")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch initial laminate data from Products collection
    fun fetchMonocartonsData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("Products")
                    .document("Monocartons")
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

    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchMonocartonsTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("temporaryData")
                    .document("Monocartons")
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

    // Function to update Laminate data in the Products collection
    fun updateMonocartonsData(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("Products")
                    .document("Monocartons")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    // Function to update Laminate data in the temporaryData collection
    fun updateMonocartonsDataTemp(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("temporaryData")
                    .document("Monocartons")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch initial laminate data from Products collection
    fun fetchFlavoursData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("Products")
                    .document("Flavours")
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

    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchFlavoursTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("temporaryData")
                    .document("Flavours")
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

    // Function to update Laminate data in the Products collection
    fun updateFlavoursData(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("Products")
                    .document("Flavours")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    // Function to update Laminate data in the temporaryData collection
    fun updateFlavoursDataTemp(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("temporaryData")
                    .document("Flavours")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch initial laminate data from Products collection
    fun fetchPulpsData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("Products")
                    .document("Pulps")
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

    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchPulpsTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("temporaryData")
                    .document("Pulps")
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

    // Function to update Laminate data in the Products collection
    fun updatePulpsData(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("Products")
                    .document("Pulps")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    // Function to update Laminate data in the temporaryData collection
    fun updatePulpsDataTemp(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("temporaryData")
                    .document("Pulps")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch initial laminate data from Products collection
    fun fetchSpicesData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("Products")
                    .document("Spices")
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

    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchSpicesTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("temporaryData")
                    .document("Spices")
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

    // Function to update Laminate data in the Products collection
    fun updateSpicesData(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("Products")
                    .document("Spices")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch initial laminate data from Products collection
    fun fetchLabelData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("Products")
                    .document("Label")
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

    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchLabelTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("temporaryData")
                    .document("Label")
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

    // Function to update Laminate data in the Products collection
    fun updateLabelData(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("Products")
                    .document("Label")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    // Function to update Laminate data in the temporaryData collection
    fun updateLabelDataTemp(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("temporaryData")
                    .document("Label")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to update Laminate data in the temporaryData collection
    fun updateSpicesDataTemp(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("temporaryData")
                    .document("Spices")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to update Laminate data in the temporaryData collection
    fun updateDehydratedDataTemp(
        data: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                firestore.collection("FinishedData")
                    .document("Dehydrated")
                    .set(data, SetOptions.merge())
                    .await()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
    // Function to fetch temporary laminate data from temporaryData collection
    fun fetchDehydratedTemporaryData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("FinishedData")
                    .document("Dehydrated")
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
    // Function to fetch initial laminate data from Products collection
    fun exportProductsData(
        onSuccess: (Map<String, Map<String, Any>>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                // Fetch all documents from the Products collection
                val productsCollection = firestore.collection("temporaryData").get().await()

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


    fun updateExportCollection(
        data: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                // Log the data for debugging
                Log.d("UpdateExportCollection", "Moving data to export collection: $data")

                // Reference to the Firestore collection and document
                val exportCollectionRef = firestore.collection("export")
                val rawMaterialDocumentRef = exportCollectionRef.document("raw_material")

                // Use `SetOptions.merge()` to merge the data, avoiding overwriting existing fields
                rawMaterialDocumentRef.set(data, SetOptions.merge()).await()

                // Call the success callback
                onSuccess()
            } catch (e: Exception) {
                // Log the error
                Log.e("UpdateExportCollection", "Error moving data to export collection", e)
                onFailure(e)
            }
        }
    }



    // Function to get the application context
    fun getApplicationContext(): Application {
        return getApplication()
    }
}
