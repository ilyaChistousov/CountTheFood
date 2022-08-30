package ilya.chistousov.countthefood.core.mapper

interface BaseMapper<DB, M> {
    fun mapFromEntityToModel(entity: DB) : M

    fun mapFromModelToEntity(model: M) : DB

    fun mapFromListToModelList(entities: List<DB>)= entities.map { mapFromEntityToModel(it) }

    fun mapFromModelListToList(models: List<M>) = models.map { mapFromModelToEntity(it)}
}