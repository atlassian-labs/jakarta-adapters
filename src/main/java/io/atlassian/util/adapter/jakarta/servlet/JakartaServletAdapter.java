package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXServletRequestAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletResponseAdapter;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static java.util.Objects.requireNonNull;

public class JakartaServletAdapter implements Servlet {

    private final javax.servlet.Servlet delegate;

    public JakartaServletAdapter(javax.servlet.Servlet delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public javax.servlet.Servlet getDelegate() {
        return delegate;
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            delegate.init(asJavaX(servletConfig));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        return asJakarta(delegate.getServletConfig());
    }

    @Override
    public void service(ServletRequest servletRequest,
                        ServletResponse servletResponse) throws ServletException, IOException {
        try {
            delegate.service(JavaXServletRequestAdapter.from(servletRequest), JavaXServletResponseAdapter.from(servletResponse));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return delegate.getServletInfo();
    }

    @Override
    public void destroy() {
        delegate.destroy();
    }
}
