package ilya.chistousov.countthefood.food.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.core.model.Meal
import ilya.chistousov.countthefood.core.result.Result
import ilya.chistousov.countthefood.core.result.handleResponse
import ilya.chistousov.countthefood.food.data.dao.FoodDao
import ilya.chistousov.countthefood.food.data.mapper.FoodEntityMapper
import ilya.chistousov.countthefood.food.data.network.mapper.FoodDtoListMapper
import ilya.chistousov.countthefood.food.data.network.service.ApiFoodService
import ilya.chistousov.countthefood.food.domain.repository.FoodRepository
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val dao: FoodDao,
    private val foodEntityMapper: FoodEntityMapper,
    private val foodDtoListMapper: FoodDtoListMapper,
    private val apiFoodService: ApiFoodService
) : FoodRepository {

    override suspend fun addFood(food: Food) {
        dao.addFood(foodEntityMapper.mapFromModelToEntity(food))
    }

    override suspend fun getFood(foodId: Int) =
        dao.getFoodById(foodId)?.let { foodEntityMapper.mapFromEntityToModel(it) }

    override fun getAllFoods(): LiveData<List<Food>> {
        return Transformations.map(dao.getAllFood()) {
            foodEntityMapper.mapFromListToModelList(it)
        }
    }

    override suspend fun deleteFood(food: Food) {
        dao.deleteFood(foodEntityMapper.mapFromModelToEntity(food))
    }

    override fun getFoodByMeal(meal: Meal): LiveData<List<Food>> {
        return Transformations.map(dao.getFoodByMeal(meal)) {
            foodEntityMapper.mapFromListToModelList(it)
        }
    }

    override fun searchFoodByName(name: String): LiveData<Result<List<Food>>> {
        return liveData {
            emit(Result.Loading())
            emit(apiFoodService.searchFoodByName(name).handleResponse(foodDtoListMapper))
        }
    }
}