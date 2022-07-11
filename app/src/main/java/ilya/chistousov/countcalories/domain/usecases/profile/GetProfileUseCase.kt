package ilya.chistousov.countcalories.domain.usecases.profile

import ilya.chistousov.countcalories.domain.repository.ProfileRepository

class GetProfileUseCase(private val profileRepository: ProfileRepository) {

    operator fun invoke()  = profileRepository.getProfile()
}