package ilya.chistousov.countcalories.data.room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ilya.chistousov.countcalories.data.room.mapper.FoodEntityMapper
import ilya.chistousov.countcalories.data.room.dao.FoodDao
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import ilya.chistousov.countcalories.domain.repository.FoodRepository
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val dao: FoodDao,
    private val mapper: FoodEntityMapper
) : FoodRepository {

    override suspend fun addFood(food: Food) {
        dao.addFood(mapper.mapFromModelToEntity(food))
    }

    override suspend fun getFood(foodId: Int) =
        dao.getFoodById(foodId)?.let { mapper.mapFromEntityToModel(it) }

    override fun getAllFoods(): LiveData<List<Food>> {
        return Transformations.map(dao.getAllFood()) {
            mapper.mapFromListToModelList(it)
        }
    }

    override suspend fun deleteFood(food: Food) {
        dao.deleteFood(mapper.mapFromModelToEntity(food))
    }

    override fun getFoodByMeal(meal: Meal): LiveData<List<Food>> {
        return Transformations.map(dao.getFoodByMeal(meal)) {
            mapper.mapFromListToModelList(it)
        }
    }
}