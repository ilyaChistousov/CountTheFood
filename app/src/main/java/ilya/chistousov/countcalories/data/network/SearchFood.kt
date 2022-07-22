package ilya.chistousov.countcalories.data.network

data class SearchFood(
    val currentPage: Int,
    val foodSearchCriteria: FoodSearchCriteria,
    val foods: List<Food>,
    val pageList: List<Int>,
    val totalHits: Int,
    val totalPages: Int
)