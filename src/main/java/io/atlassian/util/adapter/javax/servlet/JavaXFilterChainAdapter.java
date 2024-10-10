package io.atlassian.util.adapter.javax.servlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static java.util.Objects.requireNonNull;

public class JavaXFilterChainAdapter implements FilterChain {

    private final jakarta.servlet.FilterChain delegate;

    public JavaXFilterChainAdapter(jakarta.servlet.FilterChain delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        try {
            delegate.doFilter(asJakarta(request), asJakarta(response));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }
}
