package com.redner.postapp.view.base

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redner.postapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseViewModel : ViewModel() {
    private val baseModelEventChannel = Channel<BaseModelEvent>()
    val baseModelEventFlow = baseModelEventChannel.receiveAsFlow()

    protected fun launchWithErrorHandling(
        context: CoroutineContext = EmptyCoroutineContext,
        apiCall: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope.launch(context) {
        try {
            apiCall()
        } catch (e: Exception) {
            baseModelEventChannel.send(
                BaseModelEvent.Fail(
                    when (e) {
                        is NetworkErrorException,
                        is SocketTimeoutException,
                        is UnknownHostException -> R.string.post_no_connection
                        is HttpException -> R.string.post_http_error
                        else -> R.string.post_something_wrong
                    }
                )
            )
        }
    }

    sealed class BaseModelEvent {
        data class Fail(val errorMessage: Int) : BaseModelEvent()
    }
}