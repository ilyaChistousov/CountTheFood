package ilya.chistousov.countcalories.domain.usecases.profile

import ilya.chistousov.countcalories.domain.repository.ProfileRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(private val profileRepository: ProfileRepository) {

    operator fun invoke()  = profileRepository.getProfile()
}