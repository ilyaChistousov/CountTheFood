package ilya.chistousov.countthefood.food.di

import androidx.lifecycle.ViewModel

internal class FoodComponentViewModel : ViewModel() {
    val foodComponent = DaggerFoodComponent.factory().create(FoodDepsProvider.deps)
}