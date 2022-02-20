package com.example.core

import kotlinx.coroutines.CancellationException

/**
 * This class is used to provide a result for an operation, which may be succesfull or fail.
 * Based on https://github.com/michaelbull/kotlin-result
 */

sealed class MyResult<out V, out E> {
    data class Ok<out V>(val value: V) : MyResult<V, Nothing>()
    data class Err<out E>(val error: E) : MyResult<Nothing, E>()
}

/**
 * Calls the specified function [block] and returns its encapsulated result if
 * invocation was successful, catching and encapsulating any thrown exception
 * as a failure.
 */
inline fun <V> myRunCatching(block: () -> V): MyResult<V, Throwable> {
    return try {
        MyResult.Ok(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        MyResult.Err(e)
    }
}

/**
 * Calls the specified function [block] with [this] value as its receiver and
 * returns its encapsulated result if invocation was successful, catching and
 * encapsulating any thrown exception as a failure.
 */
inline infix fun <T, V> T.myRunCatching(block: T.() -> V): MyResult<V, Throwable> {
    return try {
        MyResult.Ok(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        MyResult.Err(e)
    }
}

/**
 * Maps this [MyResult<V, E>][MyResult] to [MyResult<U, E>][MyResult] by either applying the
 * [transform] function if this [MyResult] is [Ok], or returning this [Err].
 */
inline infix fun <V, E, U> MyResult<V, E>.andThen(
    transform: (V) -> MyResult<U, E>
): MyResult<U, E> = when (this) {
    is MyResult.Ok -> transform(value)
    is MyResult.Err -> this
}

/**
 * Maps this [MyResult<V, E>][MyResult] to [MyResult<U, E>][MyResult] by either applying the
 * [transform] function to the [value][Ok.value] if this [MyResult] is [Ok], or returning this
 * [Err].
 */
inline infix fun <V, E, U> MyResult<V, E>.map(transform: (V) -> U): MyResult<U, E> {
    return when (this) {
        is MyResult.Ok -> MyResult.Ok(transform(value))
        is MyResult.Err -> this
    }
}

/**
 * Maps this [MyResult<V, E>][MyResult] to [MyResult<V, F>][MyResult] by either applying the
 * [transform] function to the [error][Err.error] if this [MyResult] is [Err], or returning this
 * [Ok].
 */
inline infix fun <V, E, F> MyResult<V, E>.mapError(transform: (E) -> F): MyResult<V, F> {
    return when (this) {
        is MyResult.Ok -> this
        is MyResult.Err -> MyResult.Err(transform(error))
    }
}

/**
 * Maps this [MyResult<V, E>][MyResult] to [U] by applying either the [success] function if this
 * [MyResult] is [Ok], or the [failure] function if this [MyResult] is an [Err]. Both of these
 * functions must return the same type ([U]).
 */
inline fun <V, E, U> MyResult<V, E>.mapBoth(success: (V) -> U, failure: (E) -> U): U {
    return when (this) {
        is MyResult.Ok -> success(value)
        is MyResult.Err -> failure(error)
    }
}

/**
 * Invokes an [action] if this [MyResult] is [Ok].
 */
inline infix fun <V, E> MyResult<V, E>.onSuccess(action: (V) -> Unit): MyResult<V, E> {
    if (this is MyResult.Ok) {
        action(value)
    }

    return this
}

/**
 * Invokes an [action] if this [MyResult] is [Err].
 */
inline infix fun <V, E> MyResult<V, E>.onFailure(action: (E) -> Unit): MyResult<V, E> {
    if (this is MyResult.Err) {
        action(error)
    }

    return this
}

/**
 * Returns [result] if this [MyResult] is [Err], otherwise this [Ok].
 */
public infix fun <V, E> MyResult<V, E>.or(result: MyResult<V, E>): MyResult<V, E> {
    return when (this) {
        is MyResult.Ok -> this
        is MyResult.Err -> result
    }
}

/**
 * Returns the [transformation][transform] of the [error][Err.error] if this [MyResult] is [Err],
 * otherwise this [Ok].
 */
public inline infix fun <V, E> MyResult<V, E>.orElse(
    transform: (E) -> MyResult<V, E>
): MyResult<V, E> {
    return when (this) {
        is MyResult.Ok -> this
        is MyResult.Err -> transform(error)
    }
}
