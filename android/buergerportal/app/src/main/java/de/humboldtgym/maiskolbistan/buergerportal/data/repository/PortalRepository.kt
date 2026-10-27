package de.humboldtgym.maiskolbistan.buergerportal.data.repository

import de.humboldtgym.maiskolbistan.buergerportal.data.SupabaseClient
import de.humboldtgym.maiskolbistan.buergerportal.data.model.Announcement
import de.humboldtgym.maiskolbistan.buergerportal.data.model.Appointment
import de.humboldtgym.maiskolbistan.buergerportal.data.model.Citizen
import de.humboldtgym.maiskolbistan.buergerportal.data.model.KvEntry
import de.humboldtgym.maiskolbistan.buergerportal.data.model.Profile
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class PortalRepository {
    private val client = SupabaseClient.client
    private val json = Json { ignoreUnknownKeys = true; isLenient = true }

    suspend fun signIn(email: String, password: String): Result<Profile> {
        return try {
            client.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            loadProfile()
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loadProfile(): Result<Profile> {
        return try {
            val user = client.auth.currentUserOrNull()
                ?: return Result.failure(Exception("Nicht angemeldet"))
            val profile = client.from("profiles")
                .select(Columns.list("id", "citizen_id", "level", "is_admin")) {
                    filter { eq("id", user.id) }
                }
                .decodeSingleOrNull<Profile>()
                ?: return Result.failure(Exception("Profil nicht gefunden"))
            Result.success(profile.copy(email = user.email.orEmpty()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signOut() {
        try {
            client.auth.signOut()
        } catch (_: Exception) {
        }
    }

    suspend fun resetPassword(email: String): Result<Unit> {
        return try {
            client.auth.resetPasswordForEmail(email)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCitizen(citizenId: String): Result<Citizen> {
        return try {
            val registry = fetchKvValue("mks:registry:v4")
                ?: return Result.failure(Exception("Bürgerregister nicht verfügbar"))
            val citizen = parseCitizenFromRegistry(registry, citizenId)
                ?: return Result.failure(Exception("Bürger nicht im Register gefunden"))
            Result.success(citizen)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAppointments(citizenId: String?): Result<List<Appointment>> {
        return try {
            val raw = fetchKvValue("mks:appointments:v1") ?: return Result.success(emptyList())
            val all = json.decodeFromString<List<Appointment>>(raw)
            val filtered = if (citizenId.isNullOrBlank()) all else all.filter {
                it.citizenId == citizenId || it.citizenId.isNullOrBlank()
            }
            Result.success(filtered.sortedBy { it.date + it.time })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAnnouncements(): Result<List<Announcement>> {
        return try {
            val raw = fetchKvValue("mks:announcements:v1") ?: return Result.success(emptyList())
            Result.success(json.decodeFromString<List<Announcement>>(raw))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun fetchKvValue(key: String): String? {
        return try {
            val entry = client.from("kv")
                .select(Columns.list("value")) {
                    filter { eq("key", key) }
                }
                .decodeSingleOrNull<KvEntry>()
            entry?.value
        } catch (e: Exception) {
            null
        }
    }

    private fun parseCitizenFromRegistry(registryJson: String, citizenId: String): Citizen? {
        return try {
            val element = json.parseToJsonElement(registryJson)
            val obj = element.jsonObject
            val citizenElement = obj[citizenId]
            if (citizenElement != null) {
                return json.decodeFromJsonElement(Citizen.serializer(), citizenElement)
            }
            element.jsonArray.firstOrNull {
                it.jsonObject["id"]?.jsonPrimitive?.content == citizenId
            }?.let {
                json.decodeFromJsonElement(Citizen.serializer(), it)
            }
        } catch (e: Exception) {
            null
        }
    }
}
