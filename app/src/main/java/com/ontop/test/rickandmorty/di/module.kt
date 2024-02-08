package com.ontop.test.rickandmorty.di

import com.ontop.test.rickandmorty.data.datasource.remote.RickAndMortyService
import com.ontop.test.rickandmorty.data.repository.RickAndMortyRepository
import com.ontop.test.rickandmorty.domain.repository.IRickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickAndMortyModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    @Provides
    @Singleton
    fun provideRickAndMortyService(okHttpClient: OkHttpClient): RickAndMortyService {
        return Retrofit.Builder()
            .baseUrl(RickAndMortyService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRickAndMortyRepository(service: RickAndMortyService): IRickAndMortyRepository {
        return RickAndMortyRepository(service)
    }
}