package ilya.chistousov.countthefood.food.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ilya.chistousov.countthefood.core.viewmodelfactory.ViewModelKey
import ilya.chistousov.countthefood.food.data.repository.FoodRepositoryImpl
import ilya.chistousov.countthefood.food.data.repository.GetProfileRepositoryImpl
import ilya.chistousov.countthefood.food.domain.repository.FoodRepository
import ilya.chistousov.countthefood.food.domain.repository.GetProfileRepository
import ilya.chistousov.countthefood.food.presentation.diary.viewmodel.DiaryViewModel
import ilya.chistousov.countthefood.food.presentation.diary.viewmodel.ProfileViewModel
import ilya.chistousov.countthefood.food.presentation.meal.viewmodel.AddFoodViewModel
import ilya.chistousov.countthefood.food.presentation.meal.viewmodel.SearchFoodViewModel
import ilya.chistousov.countthefood.food.presentation.meal.viewmodel.MealViewModel

@Module
interface FoodModule {

    @Binds
    fun bindFoodRepository(foodRepositoryImpl: FoodRepositoryImpl) : FoodRepository

    @Binds
    fun bindGetProfileRepository(getProfileRepositoryImpl: GetProfileRepositoryImpl) : GetProfileRepository

    @Binds
    @[IntoMap ViewModelKey(value = DiaryViewModel::class)]
    fun bindDiaryViewModel(diaryViewModel: DiaryViewModel) : ViewModel

    @Binds
    @[IntoMap ViewModelKey(value = ProfileViewModel::class)]
    fun bindProfileViewModel(profileViewModel: ProfileViewModel) : ViewModel

    @Binds
    @[IntoMap ViewModelKey(value = AddFoodViewModel::class)]
    fun bindAddFoodViewModel(addFoodViewModel: AddFoodViewModel) : ViewModel

    @Binds
    @[IntoMap ViewModelKey(value = MealViewModel::class)]
    fun bindMealViewModel(mealViewModel: MealViewModel) : ViewModel

    @Binds
    @[IntoMap ViewModelKey(value = SearchFoodViewModel::class)]
    fun bindSearchFoodViewModel(searchViewModel: SearchFoodViewModel) : ViewModel
}