package com.sosity.routes.society.tables

import com.sosity.routes.society.enums.SuggestionType
import kotlinx.serialization.Serializable

@Serializable
data class Suggestion(val text: String, val suggestionType: SuggestionType, val attachment: String? = null)