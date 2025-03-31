package com.etsoft.interceptors

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.util.ContentCachingRequestWrapper

class LoggingFilter : Filter {

    override fun doFilter(
        req: ServletRequest,
        res: ServletResponse,
        filterChain: FilterChain
    ) {
        val castedRequest = req as HttpServletRequest
        val wrappedRequest = ContentCachingRequestWrapper(castedRequest)

        try {
            filterChain.doFilter(wrappedRequest, res)
        } finally {
            val contentAsString = wrappedRequest.contentAsString
            val responseAsString = CustomThreadLocal.responseBodyString.get()

            println("Request: $contentAsString")
            println("Response: $responseAsString")

        }

    }
}