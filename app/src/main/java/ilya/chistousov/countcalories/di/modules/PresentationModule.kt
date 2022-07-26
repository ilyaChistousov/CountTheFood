package ilya.chistousov.countcalories.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ilya.chistousov.countcalories.di.annotation.ViewModelKey
import ilya.chistousov.countcalories.presentation.diary.viewmodel.DiaryViewModel
import ilya.chistousov.countcalories.presentation.diary.viewmodel.ProfileViewModel
import ilya.chistousov.countcalories.presentation.foods.viewmodels.MealViewModel
import ilya.chistousov.countcalories.presentation.meal.viewmodel.AddFoodViewModel
import ilya.chistousov.countcalories.presentation.meal.viewmodel.SearchFoodViewModel

@Module
interface PresentationModule {

    @Binds
    @[IntoMap ViewModelKey(AddFoodViewModel::class)]
    fun bindAddFoodViewModel(addFoodViewModel: AddFoodViewModel) : ViewModel

    @Binds
    @[IntoMap ViewModelKey(DiaryViewModel::class)]
    fun bindDiaryViewModel(diaryViewModel: DiaryViewModel) : ViewModel

    @Binds
    @[IntoMap ViewModelKey(ProfileViewModel::class)]
    fun bindProfileViewModel(profileViewModel: ProfileViewModel) : ViewModel

    @Binds
    @[IntoMap ViewModelKey(MealViewModel::class)]
    fun bindMealViewModel(mealViewModel: MealViewModel) : ViewModel

    @Binds
    @[IntoMap ViewModelKey(SearchFoodViewModel::class)]
    fun bindSearchFoodViewModel(searchFoodViewModel: SearchFoodViewModel) : ViewModel
}