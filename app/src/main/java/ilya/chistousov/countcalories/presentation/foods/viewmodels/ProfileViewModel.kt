package ilya.chistousov.countcalories.presentation.foods.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ilya.chistousov.countcalories.data.database.AppDatabase
import ilya.chistousov.countcalories.data.room.repository.ProfileRepositoryImpl
import ilya.chistousov.countcalories.domain.usecases.profile.GetProfileUseCase

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val appDatabase = AppDatabase.getInstance(application)
    private val repository =  ProfileRepositoryImpl(appDatabase)
    private val getProfileUseCase = GetProfileUseCase(repository)

    val currentProfile = getProfileUseCase()

}