package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaServletRequestAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletResponseAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class JavaXRequestDispatcherAdapter implements RequestDispatcher {
    private final jakarta.servlet.RequestDispatcher delegate;

    public JavaXRequestDispatcherAdapter(jakarta.servlet.RequestDispatcher delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        try {
            delegate.forward(JakartaServletRequestAdapter.from(request), JakartaServletResponseAdapter.from(response));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        try {
            delegate.include(JakartaServletRequestAdapter.from(request), JakartaServletResponseAdapter.from(response));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }
}
