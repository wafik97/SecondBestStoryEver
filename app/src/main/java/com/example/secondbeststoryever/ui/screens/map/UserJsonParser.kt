package com.example.secondbeststoryever.ui.screens.map

import com.example.secondbeststoryever.data.model.UserLocation
import org.json.JSONArray
import org.json.JSONObject

object UserJsonParser {

    // Convert a single UserLocation to JSON string
    fun toJson(user: UserLocation): String {
        val json = JSONObject()
        json.put("userId", user.userId)
        json.put("name", user.name)
        json.put("lat", user.lat)
        json.put("lon", user.lon)
        return json.toString()
    }

    // Wrap the user into a server-friendly update message
    fun toUpdateMessage(user: UserLocation): String {
        val json = JSONObject()
        json.put("type", "updateUser")
        json.put("user", JSONObject(toJson(user)))
        return json.toString()
    }

    // Parse the server JSON (object with "type" and "users")
    fun parseUsersJson(jsonString: String): List<UserLocation> {
        val jsonObject = JSONObject(jsonString)

        if (jsonObject.optString("type") != "allUsers") return emptyList()

        val usersArray = jsonObject.getJSONArray("users")
        val users = mutableListOf<UserLocation>()

        for (i in 0 until usersArray.length()) {
            val obj = usersArray.getJSONObject(i)
            users.add(
                UserLocation(
                    userId = obj.getString("userId"),
                    name = obj.getString("name"),
                    lat = obj.getDouble("lat"),
                    lon = obj.getDouble("lon")
                )
            )
        }

        return users
    }
}
