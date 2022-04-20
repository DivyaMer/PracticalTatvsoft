package com.app.practicaltatvsoft.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("has_more")
    @Expose
    val hasMore: Boolean? = false,
    @SerializedName("users")
    @Expose
    val users: List<User>? = listOf()
) {
    data class User(
        @SerializedName("image")
        @Expose
        val image: String? = "",
        @SerializedName("items")
        @Expose
        val items: List<String>? = listOf(),
        @SerializedName("name")
        @Expose
        val name: String? = ""
    )
}