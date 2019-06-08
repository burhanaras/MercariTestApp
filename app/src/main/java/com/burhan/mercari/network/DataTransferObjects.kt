package com.burhan.mercari.network

import com.burhan.mercari.database.DatabaseProduct
import com.squareup.moshi.JsonClass

/**
 * Developed by tcbaras on 2019-06-09.
 */

@JsonClass(generateAdapter = true)
data class NetworkProductContainer(val products: List<NetworkProduct>)

@JsonClass(generateAdapter = true)
data class NetworkProduct(
    val id: String,
    val name: String,
    val status: String,
    val num_likes: String,
    val num_comments: String,
    val price: String,
    val photo: String
)


fun NetworkProductContainer.asDatabaseModel(): Array<DatabaseProduct> {
    return products.map {
        DatabaseProduct(
            id = it.id,
            name = it.name,
            status = it.status,
            num_likes = it.num_likes,
            num_comments = it.num_comments,
            price = it.price,
            photo = it.photo
        )
    }.toTypedArray()
}

fun List<NetworkProduct>.asDatabaseModel(): Array<DatabaseProduct> {
    return map {
        DatabaseProduct(
            id = it.id,
            name = it.name,
            status = it.status,
            num_likes = it.num_likes,
            num_comments = it.num_comments,
            price = it.price,
            photo = it.photo
        )
    }.toTypedArray()
}