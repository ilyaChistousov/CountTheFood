package ilya.chistousov.countthefood.signup.di

import android.content.SharedPreferences
import android.provider.ContactsContract
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Component
import ilya.chistousov.counthefood.database.dao.ProfileDao
import ilya.chistousov.counthefood.database.entity.ProfileEntity
import ilya.chistousov.countthefood.api.dto.ProfileDto
import ilya.chistousov.countthefood.api.service.ProfileService
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.Profile
import ilya.chistousov.countthefood.core.viewmodelfactory.MultiViewModelFactory
import kotlin.properties.Delegates.notNull

@Component(
    modules = [SignUpModule::class],
    dependencies = [SignUpDeps::class]
)
internal interface SignUpComponent {

    val factory : MultiViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(deps: SignUpDeps) : SignUpComponent
    }
}

interface SignUpDeps {
    val auth: FirebaseAuth
    val googleClient: GoogleSignInClient
    val sharedPreferences: SharedPreferences
    val profileDao: ProfileDao
    val profileService: ProfileService
    val profileMapper: BaseMapper<ProfileEntity, Profile>
    val profileDtoMapper: BaseMapper<ProfileDto, Profile>
}

object SignUpDepsProvider {
    var deps: SignUpDeps by notNull()
}