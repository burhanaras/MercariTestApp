package com.burhan.mercari.domain

/**
 * Developed by tcbaras on 2019-06-08.
 */

data class Product(var id: String,
                   var name: String,
                   var status: String,
                   var likeCount: Int,
                   var commentCount: Int,
                   var price: Double,
                   var photoUrl: String)