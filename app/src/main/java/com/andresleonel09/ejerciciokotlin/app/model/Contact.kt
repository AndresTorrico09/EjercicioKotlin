package com.andresleonel09.ejerciciokotlin.app.model

class Contact {
    var user_id: String? = null
    var birth_date: String? = null
    var first_name: String? = null
    var last_name: String? = null
    var phones: List<Phone>? = null
    var thumb: String? = null
    var photo: String? = null
    var addresses: List<Address>? = null
}