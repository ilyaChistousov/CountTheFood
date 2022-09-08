package ilya.chistousov.countthefood.core.mapper

interface BaseMapper<T, E> {
    fun mapFromEntityToModel(entity: T) : E

    fun mapFromModelToEntity(model: E) : T
}