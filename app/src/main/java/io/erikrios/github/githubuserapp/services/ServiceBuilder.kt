package io.erikrios.github.githubuserapp.services

import com.google.gson.GsonBuilder
import io.erikrios.github.githubuserapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {

    // Get the Base Url and Authorization from build config
    private const val BASE_URL = BuildConfig.BASE_URL
    private const val AUTHORIZATION = BuildConfig.AUTHORIZATION

    // Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // Create a Custom Interceptor to apply Headers application wide
    private val headerInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()

            request = request.newBuilder()
                .addHeader("Authorization", "Token $AUTHORIZATION")
                .build()

            return chain.proceed(request)
        }
    }

    // Create GSON Builder
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    // Create OkHttp Client
    private val okHttp = OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(headerInterceptor)
        .addInterceptor(logger)

    // Create Retrofit Builder
    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttp.build())

    // Create Retrofit Instance
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}