package com.example.notes

import androidx.room.*

@Dao
interface NotaDao {

    @Query("select * from Nota")
    suspend fun getAll():List<Nota>

    @Query("select * from Nota where id = :id")
    suspend fun getById(id:Int):Nota

    @Update
    suspend fun actualizar(nota: Nota)

    @Insert
    suspend fun insert(notas:List<Nota>)

    @Delete
    suspend fun eliminar(nota: Nota)


}