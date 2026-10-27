package de.humboldtgym.maiskolbistan.buergerportal.data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

object SupabaseClient {
    const val URL = "https://tyfdwdzglguiiqbsttav.supabase.co"
    const val ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InR5ZmR3ZHpnbGd1aWlxYnN0dGF2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODIyMjc0NTIsImV4cCI6MjA5NzgwMzQ1Mn0.d7LwJH1gsXgz_h0TrQLPX38uI6slf0IpeFwFXxe99Uc"

    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = URL,
            supabaseKey = ANON_KEY
        ) {
            install(Auth)
            install(Postgrest)
            install(Realtime)
        }
    }
}
