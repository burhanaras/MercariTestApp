package com.burhan.mercari.network.dto

import com.squareup.moshi.JsonClass

/**
 * Developed by tcbaras on 2019-06-12.
 */

@JsonClass(generateAdapter = true)
data class CategoryUrlHolder(
    var name: String,
    var data: String
)