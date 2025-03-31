package com.etsoft.interceptors

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

class CustomInterceptor : HandlerInterceptor {


    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        println("paso por el pre")
        val requestBody = getBodyString(request)
        println(requestBody)

        return super.preHandle(request, response, handler)
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        println("paso por el post")
        val requestBody = getBodyString(request)
        println(requestBody)

        super.postHandle(request, response, handler, modelAndView)
    }

    private fun getBodyString(request: HttpServletRequest): String {
        val builder = StringBuilder()
        request.reader.lines().forEach { line -> builder.append(line) }
        val requestBody = builder.toString()
        return requestBody
    }
}