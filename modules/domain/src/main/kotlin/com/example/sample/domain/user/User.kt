package com.example.sample.domain.user

import com.example.sample.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Column(name = "username")
    var username: String,

    @Column(name = "email")
    var email: String
) : BaseEntity() {
    fun update(username: String?, email: String?) {
        username?.let { this.username = username }
        email?.let { this.email = email }
    }
}
