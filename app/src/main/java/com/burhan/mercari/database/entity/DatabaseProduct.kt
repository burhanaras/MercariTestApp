package com.burhan.mercari.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.burhan.mercari.domain.Product

/**
 * Developed by tcbaras on 2019-06-09.
 */

@Entity
data class DatabaseProduct constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val status: String,
    val num_likes: String,
    val num_comments: String,
    val price: String,
    val photo: String
)

fun List<DatabaseProduct>.asDomainModel(): List<Product>{
    return map {
        Product(
            id = it.id,
            name = it.name,
            status = it.status,
            likeCount = it.num_likes,
            commentCount = it.num_comments,
            price = "$ ${it.price}",
            photoUrl = it.photo
        )
    }
}