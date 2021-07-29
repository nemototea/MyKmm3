package com.example.mykmm3.presentation.main

import com.example.mykmm3.data.entity.Links
import com.example.mykmm3.data.entity.Rocket
import com.example.mykmm3.presentation.main.MainState.*
import kotlinx.serialization.SerialName

interface Intent
interface Action
interface Result
interface State

sealed class MainIntent : Intent {
    object OnResume : MainIntent()
    object OnTap : MainIntent()
}

sealed class MainAction : Action {
    object LoadLaunch : MainAction()
    object SkipMe : MainAction()
}

sealed class MainResult : Result {
    data class DoneLoad(val launchData: LaunchState) : MainResult()
    data class Failed(val error: Throwable) : MainResult()
}

data class MainState(
    val error: Throwable? = null,
    val isLoading: Boolean = false,
    val launchState: LaunchState? = null,
) : State {
    companion object {
        fun default() = MainState()
    }

    data class LaunchState(
        val flightNumber: Int = -1,
        val missionName: String = "",
        val launchYear: Int = -1,
        val launchDateUTC: String = "",
        val rocket: RocketState? = null,
        val details: String?,
        val launchSuccess: Boolean?,
        val links: LinksState? = null,
    )

    data class RocketState(
        val id: String = "",
        val name: String = "",
        val type: String = ""
    )

    data class LinksState(
        val missionPatchUrl: String?,
        val articleUrl: String?
    )
}