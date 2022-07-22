package ilya.chistousov.countcalories.data.room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ilya.chistousov.countcalories.data.room.mapper.FoodMapper
import ilya.chistousov.countcalories.data.room.dao.FoodDao
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import ilya.chistousov.countcalories.domain.repository.FoodRepository
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val dao: FoodDao,
    private val mapper: FoodMapper
) : FoodRepository {

    override suspend fun addFood(food: Food) {
        dao.addFood(mapper.mapFromModelToDbEntity(food))
    }

    override fun getFood(foodId: Int): LiveData<Food> {
        return Transformations.map(dao.getFoodById(foodId)) {
            mapper.mapFromDbEntityToModel(it)
        }
    }

    override fun getAllFoods(): LiveData<List<Food>> {
        return Transformations.map(dao.getAllFood()) {
            mapper.mapFromDbListToModelList(it)
        }
    }

    override suspend fun deleteFood(food: Food) {
        dao.deleteFood(mapper.mapFromModelToDbEntity(food))
    }

    override fun getFoodByMeal(meal: Meal): LiveData<List<Food>> {
        return Transformations.map(dao.getFoodByMeal(meal)) {
            mapper.mapFromDbListToModelList(it)
        }
    }
}