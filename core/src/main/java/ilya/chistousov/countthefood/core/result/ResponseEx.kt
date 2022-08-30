package ilya.chistousov.countthefood.core.result

import android.util.Log
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

fun <T, E> Response<T>.handleResponse(mapper: ResponseMapper<T, E>): Result<E> {
    return try {
        return Result.Success(mapper.map(body()!!))
    } catch (ex: IOException) {
        ex.printStackTrace()
        Result.Error("Cannot load data, ex: ${ex.message}")
    } catch (ex: HttpException) {
        ex.printStackTrace()
        Result.Error("Cannot load data, ex: ${ex.message}")
    } catch (ex: SocketTimeoutException) {
        Log.d("Response", "$ex")
        ex.printStackTrace()
        Result.Error("Cannot load data, ex: ${ex.message}")
    }
}

interface ResponseMapper<T, E> {
    fun map(response: T) : E
}
