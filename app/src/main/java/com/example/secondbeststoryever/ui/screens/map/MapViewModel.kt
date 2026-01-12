package com.example.secondbeststoryever.ui.screens.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secondbeststoryever.data.model.UserLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import kotlin.random.Random

class MapViewModel : ViewModel() {

    private val _users = MutableStateFlow<List<UserLocation>>(emptyList())
    val users: StateFlow<List<UserLocation>> = _users

    private var wsClient: WebSocketClient? = null
    private var connected = false

    private var _currentUserName: String = ""
    var currentUser: UserLocation? = null
        private set

    fun setName(name: String) {
        _currentUserName = name
    }

    fun connect() {
        if (connected || _currentUserName.isBlank()) return
        connected = true

        // Create current user
        currentUser = UserLocation(
            userId = "user_${Random.nextInt(1000)}",
            name = _currentUserName,
            lat = randomLat(),
            lon = randomLon()
        )

        val serverUrl = "wss://ws-realtime-server.onrender.com"

        wsClient = object : WebSocketClient(URI(serverUrl)) {
            override fun onOpen(handshakedata: ServerHandshake?) {
                println("Connected to WebSocket server")
                currentUser?.let { sendUserUpdate(it) }
            }

            override fun onMessage(message: String?) {
                message?.let {
                    val allUsers = UserJsonParser.parseUsersJson(it)
                    viewModelScope.launch {
                        _users.value = allUsers
                    }
                }
            }

            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                println("WebSocket closed: $reason")
            }

            override fun onError(ex: Exception?) {
                ex?.printStackTrace()
            }
        }

        wsClient?.connect()
    }

    fun sendUserUpdate(user: UserLocation) {
        wsClient?.send(UserJsonParser.toUpdateMessage(user))
    }

    private fun randomLat() = Random.nextDouble(1.30, 1.40)
    private fun randomLon() = Random.nextDouble(103.80, 103.90)

    override fun onCleared() {
        super.onCleared()
        wsClient?.close()
    }
}
