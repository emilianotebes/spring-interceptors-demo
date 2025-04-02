package com.etsoft.interceptors

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

class LoggingFilter : Filter {

    override fun doFilter(
        req: ServletRequest,
        res: ServletResponse,
        filterChain: FilterChain
    ) {
        // Tenemos que castear los requests
        val castedRequest = req as HttpServletRequest
        val castedResponse = res as HttpServletResponse

        // Envolvemos los request en nuestros wrappers
        val wrappedRequest = ContentCachingRequestWrapper(castedRequest)
        val wrappedResponse = ContentCachingResponseWrapper(castedResponse)

        try {
            // Invocamos al resto de las operaciones para asegurarnos de que tanto el request como el response se lean y escriban
            // Notar que mandamos los wrappers, y no los objetos originales
            filterChain.doFilter(wrappedRequest, wrappedResponse)
        } finally {
            // Una vez ejecutado filterChain, tenemos nuestro objetos cargados con la info
            val contentAsString = wrappedRequest.contentAsString
            val responseAsString = String(wrappedResponse.contentAsByteArray)

            // Obligatoriamente tenemos que hacer esto, porque la conexión está establecida con el response original
            // y nuestro filter chain escribió en una copia, si no ponemos esta línea el body del response será vacío
            wrappedResponse.copyBodyToResponse()

            // Podemos acceder a los valores las veces que querramos
            println("Request: $contentAsString")
            println("Response: $responseAsString")

        }

    }
}