package com.kevin.multiapiapp.common.network

interface NetworkError {
    val code: Int
    val message: String

    class BadRequest(override val message: String) : NetworkError {
        override val code: Int
            get() = 400
    }

    class Unauthorized(override val message: String) : NetworkError {
        override val code: Int
            get() = 401
    }

    class Forbidden(override val message: String) : NetworkError {
        override val code: Int
            get() = 403
    }

    class NotFound(override val message: String) : NetworkError {
        override val code: Int
            get() = 404
    }

    class InternalServerError(override val message: String) : NetworkError {
        override val code: Int
            get() = 500
    }

    class UnResolveAddress(override val message: String) : NetworkError {
        override val code: Int
            get() = -1
    }

    class Unknown(override val message: String) : NetworkError {
        override val code: Int
            get() = -2
    }
}