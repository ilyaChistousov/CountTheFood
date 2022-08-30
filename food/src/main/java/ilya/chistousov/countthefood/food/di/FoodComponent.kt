package ilya.chistousov.countthefood.food.di

import dagger.Component
import ilya.chistousov.countthefood.core.viewmodelfactory.MultiViewModelFactory
import ilya.chistousov.countthefood.food.data.dao.FoodDao
import ilya.chistousov.countthefood.food.data.dao.GetProfileDao
import ilya.chistousov.countthefood.food.data.network.service.ApiFoodService
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
    val getProfileDao: GetProfileDao
    val apiFoodService: ApiFoodService
    val fragmentContainerId: Int
}

object FoodDepsProvider {
    var deps: FoodDeps by notNull()
}