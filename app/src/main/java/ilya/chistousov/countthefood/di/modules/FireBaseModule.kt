package ilya.chistousov.countthefood.di.modules

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import ilya.chistousov.countthefood.R

@Module
class FireBaseModule {

    @Provides
    fun provideGoogleSignInClient(context: Context) : GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }
}