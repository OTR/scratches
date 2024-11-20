package app.datasource.telegram

import app.datasource.TextDataSource
import app.model.TextData
import app.model.WholeTextData
import app.model.telegram.TelegramJSONChannelHistory
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File


/**
 *
 */
class TelegramJSONChannelDataSource: TextDataSource {

    fun main(pathName:String) {
        val channelMassages = getChannelMessagesAsJSON(pathName = pathName)
        val textDataList: List<TextData> = getOnlyPlainTextFromChannelMessages(channelMassages)
        for (textData in textDataList) {
            println(textData.text)
            println("__________________________________________________________________")
        }
    }

    /**
     * Parse Telegram chat history file, and get only channel messages. Leave out meta data about channel.
     */
    fun getChannelMessagesAsJSON(pathName: String): ArrayList<LinkedHashMap<String, *>> {
        val mapper = jacksonObjectMapper().registerKotlinModule()
        val jsonFile: String = File(pathName).readText(Charsets.UTF_8)
        val telegramChannelHistory: TelegramJSONChannelHistory = mapper.readValue<TelegramJSONChannelHistory>(jsonFile)
        val channelMessages = telegramChannelHistory.messages

        return channelMessages
    }

    /**
     *
     */
    fun getOnlyPlainTextFromChannelMessages(channelMessages: ArrayList<LinkedHashMap<String, *>>): List<TextData> {
        var resultTextDataList: MutableList<TextData> = mutableListOf()

        for (message in channelMessages) {
            val textEntities: ArrayList<LinkedHashMap<String, String>> = message.get("text_entities") as ArrayList<LinkedHashMap<String, String>>
            if (textEntities.isEmpty()) continue

            val plainTextFromMessage: String = filterTextEntities(textEntities)
            val textDataObject: TextData = WholeTextData(text = plainTextFromMessage)
            resultTextDataList.add(textDataObject)
        }

        return resultTextDataList as List<TextData>
    }

    /**
     *
     */
    fun filterTextEntities(textEntities: ArrayList<LinkedHashMap<String, String>>): String {
        var filteredText: String = ""
        // Which entities will get into result text
        val entityWhiteList: List<String> = listOf(
            "plain", "bold", "italic", "underline", "hashtag", "custom_emoji"
        )
        // Which entities will be filtered out from result text
        // Do not need @paveldurov and https://t.me/pavel_durov
        val entityBlackList: List<String> = listOf(
            "mention", "link"
        )

        for (entity in textEntities) {
            val entityType: String = entity["type"] as String

            when {
                entityType in entityWhiteList -> filteredText += entity["text"]
                entityType in entityBlackList -> filteredText += "" // Do nothing
                else -> println("!!!!!!!!!!!!!!" + entity.get("type")) // TODO: Log out and raise Exception
            }
        }

        return filteredText
    }

    override fun loadText(): TextData {
        TODO("Not yet implemented")
    }

    override fun saveText(dataToSave: TextData) {
        TODO("Not yet implemented")
    }
}

fun main() {
    TelegramJSONChannelDataSource().main(pathName = "./src/test/resources/test_samples/result.json")
}
