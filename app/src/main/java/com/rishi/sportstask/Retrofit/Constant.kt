package com.rishi.sportstask.Retrofit

class Constant {

    interface TimeOut {
        companion object {
            const val SOCKET_TIME_OUT = 60
            const val CONNECTION_TIME_OUT = 60
        }
    }

    interface UrlPath {
        companion object {
            const val BASE_URL = "https://demo.sportz.io/"
        }
    }

}