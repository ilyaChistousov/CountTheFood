package ilya.chistousov.countthefood.food.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.google.firebase.auth.FirebaseAuth
import ilya.chistousov.counthefood.database.dao.FoodDao
import ilya.chistousov.counthefood.database.entity.FoodEntity
import ilya.chistousov.countthefood.api.dto.ProfileFoodDto
import ilya.chistousov.countthefood.api.result.Result
import ilya.chistousov.countthefood.api.result.handleResponse
import ilya.chistousov.countthefood.api.service.FoodService
import ilya.chistousov.countthefood.api.service.ProfileService
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.core.model.Meal
import ilya.chistousov.countthefood.food.data.mapper.FoodDtoListMapper
import ilya.chistousov.countthefood.food.domain.repository.FoodRepository
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodDao: FoodDao,
    private val foodMapper: BaseMapper<FoodEntity, Food>,
    private val foodDtoListMapper: FoodDtoListMapper,
    private val profileFoodDtoMapper: BaseMapper<ProfileFoodDto, Food>,
    private val foodService: FoodService,
    private val profileService: ProfileService,
) : FoodRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun addFood(food: Food) {
        foodDao.addFood(foodMapper.mapFromModelToEntity(food))
        val email = auth.currentUser!!.email!!
        profileService.addFoodToProfile(email, profileFoodDtoMapper.mapFromModelToEntity(food))
    }

    override suspend fun getFood(foodId: Int) =
        foodDao.getFoodById(foodId)?.let { foodMapper.mapFromEntityToModel(it) }

    override fun getAllFoods(): LiveData<List<Food>> {
        return Transformations.map(foodDao.getAllFood()) { foodEntityList ->
            foodEntityList.map { foodMapper.mapFromEntityToModel(it) }
        }
    }

    override suspend fun deleteFood(food: Food) {
        foodDao.deleteFood(foodMapper.mapFromModelToEntity(food))
    }

    override fun getFoodByMeal(meal: Meal): LiveData<List<Food>> {
        return Transformations.map(foodDao.getFoodByMeal(meal.name)) { foodEntityList ->
            foodEntityList.map { foodMapper.mapFromEntityToModel(it) }
        }
    }

    override fun searchFoodByName(name: String): LiveData<Result<List<Food>>> {
        return liveData {
            emit(Result.Loading())
            val result = foodService.searchFoodByName(name).handleResponse()
            if (result is Result.Success) {
                emit(Result.Success(result.map(foodDtoListMapper)))
            } else {
                emit(Result.Error("Cannot load data"))
            }
        }
    }
}