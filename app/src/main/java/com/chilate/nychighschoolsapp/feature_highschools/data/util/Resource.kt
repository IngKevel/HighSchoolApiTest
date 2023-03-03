package com.chilate.nychighschoolsapp.feature_highschools.data.util

/**
 * Created by Kevel on 3/2/2023.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}
