package com.beeldi.beelding.domain.model

import com.google.firebase.database.PropertyName

data class Equipment(
    @PropertyName("brand") val brand: String,
    @PropertyName("building") val building: String,
    @PropertyName("domain") val domain: String,
    @PropertyName("local") val local: String,
    @PropertyName("model") val model: String,
    @PropertyName("name") val name: String,
    @PropertyName("nbFaults") val nbFaults: Any,
    @PropertyName("niveau") val level: String,
    @PropertyName("notes") val notes: String,
    @PropertyName("photo") val photo: String,
    @PropertyName("quantity") val quantity: Any,
    @PropertyName("serialNumber") val serialNumber: String,
    @PropertyName("status") val status: String,
){
    var equipmentKey: String = ""
    constructor():this(
        "",
        "",
        "",
        "",
        "",
        "",
        0,
        "",
        "",
        "",
        0,
        "",
        "",
    )
}