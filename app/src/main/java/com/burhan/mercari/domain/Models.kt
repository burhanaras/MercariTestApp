package com.burhan.mercari.domain

/**
 * Developed by tcbaras on 2019-06-08.
 */

data class Product(var id: String,
                   var name: String,
                   var status: String,
                   var likeCount: String,
                   var commentCount: String,
                   var price: String,
                   var photoUrl: String)