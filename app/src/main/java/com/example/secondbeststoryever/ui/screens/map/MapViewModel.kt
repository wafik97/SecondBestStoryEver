import android.annotation.SuppressLint
import android.app.Application
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.secondbeststoryever.data.model.UserLocation
import com.example.secondbeststoryever.ui.screens.map.UserJsonParser
import com.google.android.gms.location.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import kotlin.random.Random

class MapViewModel(application: Application) : AndroidViewModel(application) {

    private val _users = MutableStateFlow<List<UserLocation>>(emptyList())
    val users: StateFlow<List<UserLocation>> = _users

    private var wsClient: WebSocketClient? = null
    private var connected = false

    private var _currentUserName: String = ""
    var currentUser: UserLocation? = null
        private set

    private var fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    fun setName(name: String) {
        _currentUserName = name
    }

    fun connect() {
        if (connected || _currentUserName.isBlank()) return
        connected = true

        // Create current user with dummy coordinates first
        currentUser = UserLocation(
            userId = "user_${Random.nextInt(1000)}",
            name = _currentUserName,
            lat = 0.0,
            lon = 0.0
        )

        val serverUrl = "wss://ws-realtime-server.onrender.com"

        wsClient = object : WebSocketClient(URI(serverUrl)) {
            override fun onOpen(handshakedata: ServerHandshake?) {
                println("Connected to WebSocket server")

                // Send initial user info
                currentUser?.let { sendUserUpdate(it) }

                // Start location updates only after WS is connected
                startLocationUpdates()
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

    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = 5000L // 5 seconds
            fastestInterval = 3000L
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val loc = result.lastLocation ?: return

                // Update current user with real GPS
                currentUser = currentUser?.copy(
                    lat = loc.latitude,
                    lon = loc.longitude
                )

                // Send update if WebSocket is connected
                currentUser?.let {
                    if (wsClient?.isOpen == true) {
                        wsClient?.send(UserJsonParser.toUpdateMessage(it))
                    }
                }
            }
        }

        @SuppressLint("MissingPermission")
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    override fun onCleared() {
        super.onCleared()
        wsClient?.close()
    }

    fun sendUserUpdate(user: UserLocation) {
        wsClient?.send(UserJsonParser.toUpdateMessage(user))
    }
}
