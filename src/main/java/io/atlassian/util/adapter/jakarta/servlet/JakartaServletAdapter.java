package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXServletAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletAdapter implements Servlet, Adapted<javax.servlet.Servlet> {

    private final javax.servlet.Servlet delegate;

    public static Servlet from(javax.servlet.Servlet delegate) {
        if (delegate instanceof javax.servlet.GenericServlet) {
            javax.servlet.GenericServlet castDelegate = (javax.servlet.GenericServlet) delegate;
            return JakartaGenericServletAdapter.from(castDelegate);
        }
        if (delegate instanceof JavaXServletAdapter) {
            JavaXServletAdapter castDelegate = (JavaXServletAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaServletAdapter::new);
    }

    protected JakartaServletAdapter(javax.servlet.Servlet delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
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
            delegate.service(asJavaX(servletRequest), asJavaX(servletResponse));
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

    @Override
    public boolean equals(Object obj) {
        return WrapperUtil.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return WrapperUtil.hashCode(this);
    }
}
