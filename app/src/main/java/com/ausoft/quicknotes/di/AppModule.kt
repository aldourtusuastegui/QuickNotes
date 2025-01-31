package com.ausoft.quicknotes.di

import com.ausoft.quicknotes.data.datasources.RemoteDataSource
import com.ausoft.quicknotes.data.datasources.RemoteDataSourceImpl
import com.ausoft.quicknotes.data.repositories.NoteRepositoryImpl
import com.ausoft.quicknotes.domain.repositories.NoteRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun providesRemoteDataSource(firestore: FirebaseFirestore): RemoteDataSource {
        return RemoteDataSourceImpl(firestore)
    }

    @Provides
    fun providesNoteRepository(remoteDataSource: RemoteDataSource): NoteRepository {
        return NoteRepositoryImpl(remoteDataSource)
    }

    @Provides
    fun providesDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}