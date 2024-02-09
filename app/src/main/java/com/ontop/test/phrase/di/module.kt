package com.ontop.test.phrase.di

import com.ontop.test.phrase.helper.CapitalizeFirstLetter
import com.ontop.test.phrase.helper.IPhraseParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhraseModule {
    @Singleton
    @Provides
    fun providePhraseParser() : IPhraseParser = CapitalizeFirstLetter()

}