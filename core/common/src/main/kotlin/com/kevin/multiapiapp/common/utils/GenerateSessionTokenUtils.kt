package com.kevin.multiapiapp.common.utils

import java.util.UUID

fun generateSessionToken(): String {
    return UUID.randomUUID().toString()
}