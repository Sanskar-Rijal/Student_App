package com.example.studentapp.di

import android.content.Context
import com.example.studentapp.caching.DataStoreManager
import com.example.studentapp.cookies.AppCookieJar
import com.example.studentapp.network.network
import com.example.studentapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCookieJar(@ApplicationContext context: Context): AppCookieJar {
        return AppCookieJar(context)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(cookieJar: AppCookieJar): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .addInterceptor(logging) // Add this interceptor for better debugging
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): network {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(network::class.java)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}