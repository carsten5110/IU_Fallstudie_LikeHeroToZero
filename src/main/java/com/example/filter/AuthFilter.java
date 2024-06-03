package com.example.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Keine Initialisierung erforderlich
    }

    // Überprüft, ob der Benutzer authentifiziert ist, bevor die Anfrage weitergeleitet wird
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Prüft, ob der Benutzer in der Session gespeichert ist
        if (req.getSession().getAttribute("user") == null) {
            // Wenn der Benutzer nicht angemeldet ist, leite zur Login-Seite weiter
            res.sendRedirect(req.getContextPath() + "/login.xhtml");
        } else {
            // Benutzer ist angemeldet, fahre mit der Anfrage fort
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Keine Ressourcen freizugeben
    }
}
