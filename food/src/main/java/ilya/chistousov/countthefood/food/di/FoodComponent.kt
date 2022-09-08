package ilya.chistousov.countthefood.food.di

import dagger.Component
import ilya.chistousov.counthefood.database.dao.FoodDao
import ilya.chistousov.counthefood.database.dao.ProfileDao
import ilya.chistousov.counthefood.database.entity.FoodEntity
import ilya.chistousov.counthefood.database.entity.ProfileEntity
import ilya.chistousov.countthefood.api.dto.FoodDto
import ilya.chistousov.countthefood.api.dto.ProfileFoodDto
import ilya.chistousov.countthefood.api.service.FoodService
import ilya.chistousov.countthefood.api.service.ProfileService
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.core.model.Profile
import ilya.chistousov.countthefood.core.viewmodelfactory.MultiViewModelFactory
import kotlin.properties.Delegates.notNull

@Component(
    modules = [FoodModule::class],
    dependencies = [FoodDeps::class]
)
internal interface FoodComponent {
    val factory: MultiViewModelFactory
    val fragmentContainerId: Int

    @Component.Factory
    interface Factory {
        fun create(deps: FoodDeps): FoodComponent
    }
}

interface FoodDeps {
    val foodDao: FoodDao
    val profileDao: ProfileDao
    val foodService: FoodService
    val profileService: ProfileService
    val fragmentContainerId: Int
    val foodMapper: BaseMapper<FoodEntity, Food>
    val profileMapper: BaseMapper<ProfileEntity, Profile>
    val profileFoodDtoMapper: BaseMapper<ProfileFoodDto, Food>
}

object FoodDepsProvider {
    var deps: FoodDeps by notNull()
}