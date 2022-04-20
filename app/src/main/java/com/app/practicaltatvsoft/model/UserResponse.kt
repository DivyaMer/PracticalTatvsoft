package com.app.practicaltatvsoft.model

import com.app.practicaltatvsoft.base.BaseResponse
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val `data`: UserModel = UserModel(),
) : BaseResponse()