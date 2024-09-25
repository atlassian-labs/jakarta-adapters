package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXServletRequestAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletResponseAdapter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class JakartaRequestDispatcherAdapter implements RequestDispatcher {
    private final javax.servlet.RequestDispatcher delegate;

    public JakartaRequestDispatcherAdapter(javax.servlet.RequestDispatcher delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        try {
            delegate.forward(JavaXServletRequestAdapter.from(request), JavaXServletResponseAdapter.from(response));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        try {
            delegate.include(JavaXServletRequestAdapter.from(request), JavaXServletResponseAdapter.from(response));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }
}
