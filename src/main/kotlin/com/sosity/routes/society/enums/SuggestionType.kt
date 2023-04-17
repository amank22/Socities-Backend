package com.sosity.routes.society.enums

import kotlinx.serialization.Serializable

@Serializable
enum class SuggestionType {
    Complaint,
    Feedback,
    Improvement,
    Discussion,
    Announcement, // To be used by only specific members (Admin or some other roles)
}