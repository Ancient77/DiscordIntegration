package com.lambda.managers

import com.lambda.client.event.events.RunGameLoopEvent
import com.lambda.client.event.listener.listener
import com.lambda.client.manager.Manager


object DiscordIntegrationManager : Manager {
    var gameLoopStartTime = 0L; private set

    init {
        listener<RunGameLoopEvent.Start> {
            gameLoopStartTime = System.currentTimeMillis()
        }
    }
}