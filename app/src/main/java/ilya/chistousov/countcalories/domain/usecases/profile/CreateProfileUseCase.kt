package ilya.chistousov.countcalories.domain.usecases.profile

import ilya.chistousov.countcalories.domain.model.Profile
import ilya.chistousov.countcalories.domain.repository.ProfileRepository

class CreateProfileUseCase(private val profileRepository: ProfileRepository) {

    suspend operator fun invoke(profile: Profile) {
        profileRepository.createProfile(profile)
    }
}