package com.example.inventorymanagement.Navigation



sealed class Screen(val route: String) {
    object FinishedGood : Screen("Finished_good")
    object LoginScreen : Screen("login_screen")
    object HomeScreen : Screen("home_screen")
    object RawMaterial : Screen("Raw_material")
    object Laminates : Screen("Laminates")
    object BottlesClosuresPouches : Screen("Bottles_Closures_Pouches")
    object CorrugatedBoxes : Screen("Corrugated_Boxes")
    object GeneralIngredients : Screen("General_Ingredients")
    object Labels : Screen("Labels")
    object Monocartons : Screen("Monocartons")
    object OleoresinsAndFlavours : Screen("Oleoresins_Flavours")
    object PulpsPastesPuree : Screen("Pulps_Pastes_Puree")
    object SpicesAndSeasoning : Screen("Spices_Seasoning")
    object Sauces : Screen("Sauces")
    object Seasonings: Screen("Seasonings")
    object Bottles : Screen("Bottles")
    object PastaAndSauce : Screen("Pasta_Sauce")
    object PortionPack : Screen("Portion_Pack")
    object BulkPack : Screen("Bulk_Pack")
    object AdminScreen : Screen("Admin_screen")
    object ProductUploadScreen : Screen("Product_Upload_screen")
    object UserCreationScreen : Screen("User_Creation_screen")

}
