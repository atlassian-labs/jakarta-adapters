package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static java.util.Objects.requireNonNull;

public class JakartaFilterChainAdapter implements FilterChain {

    private final javax.servlet.FilterChain delegate;

    public JakartaFilterChainAdapter(javax.servlet.FilterChain delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        try {
            delegate.doFilter(asJavaX(request), asJavaX(response));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }
}
