package com.burhan.mercari.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Developed by tcbaras on 2019-06-09.
 */

@Dao
interface ProductDao {
    @Query("select * from databaseproduct")
    fun getAllProducts(): LiveData<List<DatabaseProduct>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg products: DatabaseProduct)
}


@Database(entities = [DatabaseProduct::class], version = 1)
abstract class ProductsDatabase : RoomDatabase() {
    abstract val productDao: ProductDao
}


private lateinit var INSTANCE: ProductsDatabase

fun getDatabase(context: Context): ProductsDatabase {
    synchronized(ProductsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ProductsDatabase::class.java,
                "productsDb"
            ).build()
        }
    }
    return INSTANCE
}