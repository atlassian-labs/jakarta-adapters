package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaRequestDispatcherAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXRequestDispatcherAdapter implements RequestDispatcher {
    private final jakarta.servlet.RequestDispatcher delegate;

    public static RequestDispatcher from(jakarta.servlet.RequestDispatcher delegate) {
        if (delegate instanceof JakartaRequestDispatcherAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXRequestDispatcherAdapter::new);
    }

    JavaXRequestDispatcherAdapter(jakarta.servlet.RequestDispatcher delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.servlet.RequestDispatcher getDelegate() {
        return delegate;
    }

    @Override
    public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        try {
            delegate.forward(asJakarta(request), asJakarta(response));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        try {
            delegate.include(asJakarta(request), asJakarta(response));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }
}
