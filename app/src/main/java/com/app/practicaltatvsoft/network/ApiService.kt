package com.app.practicaltatvsoft.network

import com.app.practicaltatvsoft.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(APIConstant.API_USERS)
    suspend fun getUserList(
        @Query(APIConstant.PARAM_OFFSET) offset: Int,
        @Query(APIConstant.PARAM_LIMIT) limit: Int,
    ): Response<UserResponse>

}