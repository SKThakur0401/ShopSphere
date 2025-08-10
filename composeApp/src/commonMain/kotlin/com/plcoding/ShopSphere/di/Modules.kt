package com.plcoding.ShopSphere.di

import androidx.compose.ui.text.input.ImeAction.Companion.Go
import com.plcoding.ShopSphere.core.data.Constants
import com.plcoding.ShopSphere.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.dsl.module
import com.plcoding.ShopSphere.login_signup.presentation.login.AuthViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import org.koin.core.module.dsl.viewModelOf
//import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.*
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage


expect val platformModule: Module

val sharedModule = module {

    single { HttpClientFactory.create(get()) }

    single<SupabaseClient> {
        createSupabaseClient(
            supabaseUrl = Constants.SUPABASE.URL,
            supabaseKey = Constants.SUPABASE.KEY
        ) {
            install(Postgrest)
            install(Auth)
            install(Realtime)
            install(Storage)
        }
    }

    viewModelOf(::AuthViewModel)
}
