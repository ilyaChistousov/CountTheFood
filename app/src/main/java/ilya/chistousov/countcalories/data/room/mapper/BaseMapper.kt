package ilya.chistousov.countcalories.data.room.mapper

interface BaseMapper<DB, M> {

    fun mapFromDbEntityToModel(dbEntity: DB) : M

    fun mapFromModelToDbEntity(model: M) : DB

    fun mapFromDbListToModelList(dbList: List<DB>)= dbList.map { mapFromDbEntityToModel(it) }

    fun mapFromModelListToDbList(models: List<M>) = models.map { mapFromModelToDbEntity(it)}
}