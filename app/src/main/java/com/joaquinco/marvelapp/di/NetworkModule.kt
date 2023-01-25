package com.joaquinco.marvelapp.di

import com.joaquinco.marvelapp.data.remote.MarvelAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://gateway.marvel.com/"
    private const val API_KEY= "d72aea39392229f66a932d0911077b21"
    private const val TS= "1"
    private const val HASH= "3217f92be7a204d3d005647bc7fd7a43"

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor{ chain ->
                val originalRequet = chain.request()
                val originalHttpUrl = originalRequet.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("ts", TS)
                    .addQueryParameter("hash", HASH)
                    .build()
                val newRequest = originalRequet.newBuilder()
                    .url(url)
                    .build()
                chain.proceed(newRequest)
            }
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
    }

    @Provides
    @Singleton
    fun provideDragonBallApi(retrofit: Retrofit): MarvelAPI {
        return retrofit.create(MarvelAPI::class.java)
    }
}