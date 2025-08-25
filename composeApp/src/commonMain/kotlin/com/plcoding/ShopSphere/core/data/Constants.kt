package com.plcoding.ShopSphere.core.data

object Constants {

    object SUPABASE{
        const val URL = "https://jntbkxrqyjefnoerkmeu.supabase.co"
        const val KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpudGJreHJxeWplZm5vZXJrbWV1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTQ0MDc0NDksImV4cCI6MjA2OTk4MzQ0OX0.-sIlgATXTKGgGJfPWJtt512vv5-J06sq9hbHSSh9Ze4"
    }

    const val TOKEN_FOR_PREFS = "This_Token_Is_Used_For_Our_App_Wide_Shared_Pref"

    const val AUTH_TOKEN  = "auth_token"

    object TABLE{
        const val USERS = "users"
        const val CUSTOMER_STORIES = "customerStories"
        const val PRODUCTS = "products"
        const val EXPLORABLES = "explorables"
    }

    const val MY_NOTES = "myNotes"

    object IMG_URL{     // Place these image urls instead of that chair as you see fit
        const val HERO_SECTION = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/hero_section_carpet.png"
        const val VINTAGE_CARPET = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/vintage_carpet.jpg"
        const val ROYAL_PERSIAN_MEDALION = HERO_SECTION

        const val VINTAGE_SILK_KILIM = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/vintage_silk_kilim.png"

        const val PERSIAN_MEDALION_IN_MODERN_LIVING_ROOM = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/Persian_Medallion_in_Modern_Living_Room.png"

        object DP{      // For customer stories, DP of various people
            const val MICHAEL = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/dp_michael_chen.png"
            const val SARAH = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/dp_sarah_johnson.png"
            const val JENIFER = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/Jennifer%20Martinez.png"
        }
    }


}

