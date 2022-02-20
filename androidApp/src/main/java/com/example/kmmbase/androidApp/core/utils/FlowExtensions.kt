package com.example.kmmbase.androidApp.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow

/**
 * Utils to collect flows from compose while being lifecycle aware.
 * It cancels collection when the activity is STOPPED and starts again when STARTED.
 *
 * To obtain a LifecycleOwner from compose, we can use `LocalLifecycleOwner.current`.
 */
@Composable
fun <T> Flow<T>.collectAsStateWithLifecycle(
    owner: LifecycleOwner,
    initial: T,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
): State<T> {
    return remember(this, owner) {
        flowWithLifecycle(owner.lifecycle, minActiveState)
    }.collectAsState(initial)
}
