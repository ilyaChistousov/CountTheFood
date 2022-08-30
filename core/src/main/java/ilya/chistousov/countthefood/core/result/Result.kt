package ilya.chistousov.countthefood.core.result

sealed class Result<T>(val data: T?) {
    class Success<T>(data: T?) : Result<T>(data)
    class Error<T>(message: String) : Result<T>(null)
    class Loading<T>() : Result<T>(null)
}