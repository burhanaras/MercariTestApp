package com.burhan.mercari.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.burhan.mercari.database.entity.DatabaseProduct

/**
 * Developed by tcbaras on 2019-06-09.
 */

@Dao
interface ProductDao {
    @Query("select * from databaseproduct")
    fun getAllProducts(): LiveData<List<DatabaseProduct>>

    @Query("select * from databaseproduct where id LIKE 'mmen%' ")
    fun getAllProductsOfMen(): LiveData<List<DatabaseProduct>>

    @Query("select * from databaseproduct where id LIKE 'mwomen%' ")
    fun getAllProductsOfWomen(): LiveData<List<DatabaseProduct>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg products: DatabaseProduct)
}
