package com.example.mykmm3.presentation.main

import com.example.mykmm3.data.entity.RocketLaunch
import com.example.mykmm3.domain.repository.SpaceXRepository
import com.example.mykmm3.presentation.main.MainAction.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class MainProcessor(private val repository: SpaceXRepository) {

    fun process(action: MainAction): Flow<List<RocketLaunch>> {
        return when (action) {
            is LoadLaunch -> {
                this.repository.getLaunches(forceReload = false)
            }
            is SkipMe -> {
                emptyFlow()
            }
        }
    }
}