//Doc: https://ktor.io/docs/server-call-logging.html#mdc

package org.dopc.config

import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.http.*
import org.slf4j.event.Level

fun Application.callingLogConfig() {
    install(CallLogging){
        level = Level.INFO
        filter { call ->
            call.request.path().startsWith("/api/v1")            
        }
        format { call ->
            val status = call.response.status()
            val httpMethod = call.request.httpMethod.value
            val userAgent = call.request.headers["User-Agent"]
            "Hihihi Status: $status, HTTP method: $httpMethod, User agent: $userAgent"
        }
        mdc("request-id") { call ->
            call.request.queryParameters["request-id"]
        }
    }
}