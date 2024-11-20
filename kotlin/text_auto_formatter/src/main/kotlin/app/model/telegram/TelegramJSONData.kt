package app.model.telegram

/**
 * Top level structure when you import Telegram channel history.
 */
data class TelegramJSONChannelHistory (
    val name: String,
    val type: String,
    val id: Int,
    val messages: ArrayList<LinkedHashMap<String, *>>
)