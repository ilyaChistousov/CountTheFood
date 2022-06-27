package ilya.chistousov.countcalories.data.repository

import ilya.chistousov.countcalories.data.database.AppDatabase
import ilya.chistousov.countcalories.data.mapper.FoodMapper
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class FoodRepositoryImpl(database: AppDatabase) : FoodRepository {

    private val foodDao = database.foodDao()
    private val mapper = FoodMapper()

    override suspend fun addFood(food: Food) {
        foodDao.addFood(mapper.mapFromModelToDbEntity(food))
    }

    override fun getFood(foodId: Int): Flow<Food> {
        return foodDao.getFoodById(foodId).transform {
            mapper.mapFromDbEntityToModel(it)
        }
    }

    override fun getAllFoods(): Flow<List<Food>> {
        return foodDao.getAllFood().transform {
            mapper.mapFromDbListToModelList(it)
        }
    }

    override suspend fun deleteFood(food: Food) {
        foodDao.deleteFood(mapper.mapFromModelToDbEntity(food))
    }
}