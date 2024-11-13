package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXRequestDispatcherAdapter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaRequestDispatcherAdapter implements RequestDispatcher, Adapted<javax.servlet.RequestDispatcher> {
    private final javax.servlet.RequestDispatcher delegate;

    public static RequestDispatcher from(javax.servlet.RequestDispatcher delegate) {
        if (delegate instanceof JavaXRequestDispatcherAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaRequestDispatcherAdapter::new);
    }

    protected JakartaRequestDispatcherAdapter(javax.servlet.RequestDispatcher delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.RequestDispatcher getDelegate() {
        return delegate;
    }

    @Override
    public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        try {
            delegate.forward(asJavaX(request), asJavaX(response));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        try {
            delegate.include(asJavaX(request), asJavaX(response));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }
}
