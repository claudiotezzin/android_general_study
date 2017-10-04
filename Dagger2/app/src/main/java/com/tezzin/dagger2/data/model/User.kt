package com.tezzin.dagger2.data.model

import com.tezzin.dagger2.utils.ClassIntro

/**
 * Created by claudiotezzin on 04/10/17.
 *
 */

@ClassIntro(
        author = "Jere",
        date = "04/10/2017",
        currentRevision = 1
)
data class User(private val id: Long = -1,
                val name: String,
                val address: String,
                private val createdAt: String = "",
                private val updatedAt: String = "") {

    override fun toString(): String = "User{" +
            "id=" + id +
            ", name='" + name +
            ", address='" + address +
            ", createdAt='" + createdAt +
            ", updatedAt='" + updatedAt +
            "}"
}