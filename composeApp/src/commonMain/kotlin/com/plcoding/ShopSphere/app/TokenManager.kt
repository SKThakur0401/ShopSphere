package com.plcoding.ShopSphere.app

import com.plcoding.ShopSphere.core.data.Constants.AUTH_TOKEN
import com.plcoding.ShopSphere.core.data.Constants.TOKEN_FOR_PREFS
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class TokenManager(private val settings: Settings) {

    fun saveStr(str: String){
        settings["MyStr"] = str
    }

    fun getStr() : String?{
        return settings.getStringOrNull("MyStr")
    }

    fun saveAuthToken(token : String?){
        settings[AUTH_TOKEN] = token
    }

    fun getAuthToken():String?{
        return settings.getStringOrNull(AUTH_TOKEN)
    }

    fun isUserSignedIn(): Boolean {
        return getAuthToken() != null
    }

    fun clearAuthData() {
        settings.remove(AUTH_TOKEN)
    }

//    fun saveUser(user: User) {
//        val jsonString = Json.encodeToString(User.serializer(), user)
//        settings[USER_IDENTITY] = jsonString
//        settings[IS_USER_SIGNED_IN] = true
//    }
//
//    fun getUser(): User? {
//        return settings.getStringOrNull(USER_IDENTITY)?.let {
//            Json.decodeFromString(User.serializer(), it)
//        }
//    }
//
//    fun saveFcmToken(fcmToken: String) {
//        settings[DEVICE_TOKEN] = fcmToken
//    }
//
//    fun getFcmToken(): String? {
//        return settings.getStringOrNull(DEVICE_TOKEN)
//    }
//
//    fun isUserSignedIn(): Boolean {
//        return settings.getBoolean(IS_USER_SIGNED_IN, false)
//    }
//
//    fun signOut() {
//        settings[IS_USER_SIGNED_IN] = false
//        settings.remove(USER_IDENTITY)
//    }
//
//    fun setServerMinusDeviceTime(timeDiff: Long) {
//        Constants.SERVER_MINUS_DEVICE_TIME_LONG = timeDiff
//        settings[SERVER_MINUS_DEVICE_TIME] = timeDiff
//    }
}

