package com.example.mykmm3.domain.repository

import com.example.mykmm3.data.Database
import com.example.mykmm3.data.DatabaseDriverFactory
import com.example.mykmm3.data.entity.RocketLaunch
import com.example.mykmm3.domain.api.SpaceXApi
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class SpaceXRepository(databaseDriverFactory: DatabaseDriverFactory) {
    private val dispatcher: CoroutineContext = Dispatchers.Default
    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi() //これもDIしたい

    @Throws(Exception::class)
    fun getLaunches(forceReload: Boolean): Flow<List<RocketLaunch>> {
        return flow {
            val cachedLaunches = database.getAllLaunches()

            if (cachedLaunches.isNotEmpty() && !forceReload) {
                emit(cachedLaunches)
            } else {
                emit(
                    api.getAllLaunches().also {
                        database.clearDatabase()
                        database.createLaunches(it)
                    }
                )
            }
        }.flowOn(this.dispatcher)
    }
}