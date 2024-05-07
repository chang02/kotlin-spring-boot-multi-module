package com.pang.skeleton.jpa.user

import com.pang.skeleton.domain.user.User
import com.pang.skeleton.jpa.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
internal class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "username")
    var username: String,

    @Column(name = "email")
    var email: String
) : BaseEntity() {
    companion object {
        fun fromDomain(domain: User): UserEntity {
            return UserEntity(
                id = domain.id,
                username = domain.username,
                email = domain.email
            )
        }
    }

    fun toDomain(): User {
        return User(
            id = id,
            username = username,
            email = email
        )
    }

    fun copyFromDomain(user: User) {
        this.username = user.username
        this.email = user.email
    }
}
