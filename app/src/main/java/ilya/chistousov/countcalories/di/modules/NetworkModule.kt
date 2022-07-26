package ilya.chistousov.countcalories.di.modules

import dagger.Module
import dagger.Provides
import ilya.chistousov.countcalories.data.network.service.ApiFoodService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiFoodService {
        return retrofit.create(ApiFoodService::class.java)
    }

    companion object {
        const val BASE_URL = "https://count-calories-api.herokuapp.com"
    }
}