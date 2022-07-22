package ilya.chistousov.countcalories.data.network

data class Food(
    val additionalDescriptions: String,
    val allHighlightFields: String,
    val commonNames: String,
    val dataType: String,
    val description: String,
    val fdcId: Int,
    val finalFoodInputFoods: List<Any>,
    val foodAttributeTypes: List<Any>,
    val foodAttributes: List<Any>,
    val foodCategory: String,
    val foodMeasures: List<Any>,
    val foodNutrients: List<FoodNutrient>,
    val foodVersionIds: List<Any>,
    val lowercaseDescription: String,
    val ndbNumber: Int,
    val publishedDate: String,
    val scientificName: String,
    val score: Double
)