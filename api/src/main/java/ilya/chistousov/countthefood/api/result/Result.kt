package ilya.chistousov.countthefood.api.result

sealed class Result<T>(val data: T?) {
    class Success<T>(data: T?) : Result<T>(data) {
        fun <E> map(mapper: ResponseMapper<T, E>) : E {
            return mapper.map(data!!)
        }
    }
    class Error<T>(message: String) : Result<T>(null)
    class Loading<T> : Result<T>(null)
}

interface ResponseMapper<T, E> {
    fun map(response: T) : E
}