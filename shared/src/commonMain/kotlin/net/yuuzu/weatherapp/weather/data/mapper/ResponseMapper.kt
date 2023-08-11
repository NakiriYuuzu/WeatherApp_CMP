package net.yuuzu.weatherapp.weather.data.mapper

import io.ktor.client.call.body
import io.ktor.http.isSuccess
import net.yuuzu.weatherapp.util.exception.AuthException
import net.yuuzu.weatherapp.util.exception.ServerException
import net.yuuzu.weatherapp.weather.data.remote.ktor.Response


class ResponseMapper {
    suspend inline fun <reified T> map(httpResponse: Response<T>): Result<T> {
        return if (httpResponse.response.status.isSuccess()) {
            Result.success(httpResponse.response.body())
        } else if (httpResponse.response.status.value == 401) {
            Result.failure(AuthException(error = httpResponse.response.status.description))
        } else {
            Result.failure(ServerException(error = httpResponse.response.status.description))
        }
    }
}

inline fun <R, T : R> Result<T>.guard(onFailure: (failure: Result<Nothing>) -> R): R {
    return getOrElse { onFailure(Result.failure(it)) }
}