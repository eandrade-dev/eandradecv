package pt.eandrade.eandrade.utils

import pt.eandrade.eandrade.FIREBASE_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("o/aboutme.txt?alt=media")
    suspend fun getAboutMe(): String

    @GET("o/jobexperience.json?alt=media")
    suspend fun getAllJobs(): String

    @GET("o/experience%2F{firebase_identifier}.json?alt=media")
    suspend fun getJobDetails(@Path(value = "firebase_identifier", encoded = true) firebaseIdentifier: String): String

    companion object {
        var apiService: ApiService? = null

        fun getInstance() : ApiService {
            if(apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl(FIREBASE_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}