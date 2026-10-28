package com.dam.bbddconroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDAO{
    @Query("Select * from users")
    fun selectAll(): List<Usuario>
    @Query("Select * from users Where name = :name")
    fun getByName(name: String): List<Usuario>
    @Query("Delete from users Where name = :name")
    fun deleteByName(name: String)
    @Query("SELECT * FROM users WHERE email = :correo LIMIT 1")
    fun getByEmail(correo: String): Usuario?
    @Query("Update users set email= :newMail WHERE email= :oldMail")
    fun updateByEmail(oldMail: String, newMail: String)
    @Insert
    fun insert(usuario: Usuario): Long
    @Delete
    fun delete(usuario: Usuario)
}
