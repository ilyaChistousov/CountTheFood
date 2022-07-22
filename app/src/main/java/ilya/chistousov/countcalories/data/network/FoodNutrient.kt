package ilya.chistousov.countcalories.data.network

data class FoodNutrient(
    val dataPoints: Int,
    val derivationCode: String,
    val derivationDescription: String,
    val derivationId: Int,
    val foodNutrientId: Int,
    val foodNutrientSourceCode: String,
    val foodNutrientSourceDescription: String,
    val foodNutrientSourceId: Int,
    val indentLevel: Int,
    val nutrientId: Int,
    val nutrientName: String,
    val nutrientNumber: String,
    val rank: Int,
    val unitName: String,
    val value: Double
)