package ilya.chistousov.countthefood.api.result

import android.util.Log
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

fun <T> Response<T>.handleResponse(): Result<T> {
    return try {
        return Result.Success(body()!!)
    } catch (ex: IOException) {
        ex.printStackTrace()
        Result.Error("Cannot load data, ex: ${ex.message}")
    } catch (ex: HttpException) {
        ex.printStackTrace()
        Result.Error("Cannot load data, ex: ${ex.message}")
    }
}
