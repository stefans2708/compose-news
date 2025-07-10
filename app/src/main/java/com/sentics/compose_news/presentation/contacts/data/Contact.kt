package com.sentics.compose_news.presentation.contacts.data

data class Contact(
    val id: String,
    val firstName: String,
    val lastName: String,
    val avatar: String?,
    val number: String,
    val email: String?
) {
    val initials get() :String = "${firstName.first()}${lastName.first()}"
    val fullName get() :String = "$firstName $lastName"
}
