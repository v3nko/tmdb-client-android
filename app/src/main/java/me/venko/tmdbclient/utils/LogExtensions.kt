package me.venko.tmdbclient.utils

import android.util.Log

/**
 * @author Victor Kosenko
 */
object LogUtils {
    var loggingLevel = 0
}

private val Any.tag: String
    get() = this::class.java.simpleName

fun Any.logd(message: () -> Any?) {
    if (LogUtils.loggingLevel <= Log.DEBUG) {
        val msg = message.toStringSafe()
        Log.d(tag, msg)
    }
}

fun Any.logd(cause: Throwable, message: () -> Any?) {
    if (LogUtils.loggingLevel <= Log.DEBUG) {
        val msg = message.toStringSafe()
        Log.d(tag, msg, cause)
    }
}

fun Any.logi(message: () -> Any?) {
    if (LogUtils.loggingLevel <= Log.INFO) {
        val msg = message.toStringSafe()
        Log.i(tag, msg)
    }
}

fun Any.logi(cause: Throwable, message: () -> Any?) {
    if (LogUtils.loggingLevel <= Log.INFO) {
        val msg = message.toStringSafe()
        Log.i(tag, msg, cause)
    }
}

fun Any.logv(message: () -> Any?) {
    if (LogUtils.loggingLevel <= Log.VERBOSE) {
        val msg = message.toStringSafe()
        Log.v(tag, msg)
    }
}

fun Any.logv(cause: Throwable, message: () -> Any?) {
    if (LogUtils.loggingLevel <= Log.VERBOSE) {
        val msg = message.toStringSafe()
        Log.v(tag, msg, cause)
    }
}

fun Any.logw(message: () -> Any?) {
    if (LogUtils.loggingLevel <= Log.WARN) {
        val msg = message.toStringSafe()
        Log.w(tag, msg)
    }
}

fun Any.logw(cause: Throwable, message: () -> Any?) {
    if (LogUtils.loggingLevel <= Log.WARN) {
        val msg = message.toStringSafe()
        Log.w(tag, msg, cause)
    }
}

fun Any.loge(message: () -> Any?) {
    if (LogUtils.loggingLevel <= Log.ERROR) {
        val msg = message.toStringSafe()
        Log.e(tag, msg)
    }
}

fun Any.loge(cause: Throwable, message: () -> Any?) {
    if (LogUtils.loggingLevel <= Log.ERROR) {
        val msg = message.toStringSafe()
        Log.e(tag, msg, cause)
    }
}

internal fun (() -> Any?).toStringSafe(): String {
    return try {
        invoke().toString()
    } catch (e: Exception) {
        "Log message invocation failed: $e"
    }
}
