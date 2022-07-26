package ilya.chistousov.countcalories.util

import android.util.Log
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun <T, E> Response<T>.handleResponse(mapper: Mapper<T, E>): Resource<E> {
    return try {
        return Resource.Success(mapper.map(body()!!))
    } catch (ex: IOException) {
        ex.printStackTrace()
        Resource.Error("Cannot load data")
    } catch (ex: HttpException) {
        ex.printStackTrace()
        Resource.Error("Cannot load data")
    }
}

interface Mapper<T, E> {
    fun map(target: T): E
}