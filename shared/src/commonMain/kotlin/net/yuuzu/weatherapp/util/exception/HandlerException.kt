package net.yuuzu.weatherapp.util.exception

class AuthException(val error: String): Exception()
class ClientException(val error: String): Exception()
class ServerException(val error: String): Exception()