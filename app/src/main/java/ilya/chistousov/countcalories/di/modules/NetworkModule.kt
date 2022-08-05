package ilya.chistousov.countcalories.di.modules

import dagger.Module
import dagger.Provides
import ilya.chistousov.countcalories.data.network.service.ApiFoodService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
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