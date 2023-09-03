package com.beeldi.beelding.domain.model

import com.google.firebase.database.PropertyName

data class Checkpoint(
    @PropertyName("equipmentKey") val equipmentKey: String,
    @PropertyName("fault") val fault: String,
    @PropertyName("name") val name: String,
    @PropertyName("photo") val photo: String,
    @PropertyName("recommandation") val recommendation: String
){
    constructor():this(
        "",
        "",
        "",
        "",
        "",
    )
}
