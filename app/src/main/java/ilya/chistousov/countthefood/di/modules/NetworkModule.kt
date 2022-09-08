package ilya.chistousov.countthefood.di.modules

import dagger.Module
import dagger.Provides
import ilya.chistousov.countthefood.api.service.FoodService
import ilya.chistousov.countthefood.api.service.ProfileService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .callTimeout(20, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodService(retrofit: Retrofit) : FoodService {
        return retrofit.create(FoodService::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit) : ProfileService {
        return retrofit.create(ProfileService::class.java)
    }

    companion object {
        const val BASE_URL = "https://countthefood.herokuapp.com/"
    }
}