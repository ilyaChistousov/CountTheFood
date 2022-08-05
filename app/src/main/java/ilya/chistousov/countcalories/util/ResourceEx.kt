package ilya.chistousov.countcalories.util

import android.util.Log
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

fun <T, E> Response<T>.handleResponse(mapper: Mapper<T, E>): ilya.chistousov.countcalories.util.Response<E> {
    return try {
        return ilya.chistousov.countcalories.util.Response.Success(mapper.map(body()!!))
    } catch (ex: IOException) {
        ex.printStackTrace()
        ilya.chistousov.countcalories.util.Response.Error("Cannot load data")
    } catch (ex: HttpException) {
        ex.printStackTrace()
        ilya.chistousov.countcalories.util.Response.Error("Cannot load data")
    } catch (ex: SocketTimeoutException) {
        Log.d("Response", "$ex")
        ex.printStackTrace()
        ilya.chistousov.countcalories.util.Response.Error("Cannot load data")
    }
}

interface Mapper<T, E> {
    fun map(target: T): E
}