package ilya.chistousov.countcalories.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ilya.chistousov.countcalories.data.database.AppDatabase
import ilya.chistousov.countcalories.data.mapper.FoodMapper
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import ilya.chistousov.countcalories.domain.repository.FoodRepository

class FoodRepositoryImpl(database: AppDatabase) : FoodRepository {

    private val foodDao = database.foodDao()
    private val mapper = FoodMapper()

    override suspend fun addFood(food: Food) {
        foodDao.addFood(mapper.mapFromModelToDbEntity(food))
    }

    override fun getFood(foodId: Int): LiveData<Food> {
        return Transformations.map(foodDao.getFoodById(foodId)) {
            mapper.mapFromDbEntityToModel(it)
        }
    }

    override fun getAllFoods(): LiveData<List<Food>> {
        return Transformations.map(foodDao.getAllFood()) {
            mapper.mapFromDbListToModelList(it)
        }
    }

    override suspend fun deleteFood(food: Food) {
        foodDao.deleteFood(mapper.mapFromModelToDbEntity(food))
    }

    override fun getFoodByMeal(meal: Meal): LiveData<List<Food>> {
        return Transformations.map(foodDao.getFoodByMeal(meal)) {
            mapper.mapFromDbListToModelList(it)
        }
    }
}