package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXServletAdapter implements Servlet, Adapted<jakarta.servlet.Servlet> {

    private final jakarta.servlet.Servlet delegate;

    public static Servlet from(jakarta.servlet.Servlet delegate) {
        if (delegate instanceof jakarta.servlet.GenericServlet) {
            jakarta.servlet.GenericServlet castDelegate = (jakarta.servlet.GenericServlet) delegate;
            return JavaXGenericServletAdapter.from(castDelegate);
        }
        if (delegate instanceof JakartaServletAdapter) {
            JakartaServletAdapter castDelegate = (JakartaServletAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXServletAdapter::new);
    }

    protected JavaXServletAdapter(jakarta.servlet.Servlet delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.Servlet getDelegate() {
        return delegate;
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            delegate.init(asJakarta(servletConfig));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        return asJavaX(delegate.getServletConfig());
    }

    @Override
    public void service(ServletRequest servletRequest,
                        ServletResponse servletResponse) throws ServletException, IOException {
        try {
            delegate.service(asJakarta(servletRequest), asJakarta(servletResponse));
        } catch (jakarta.servlet.ServletException e) {
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
