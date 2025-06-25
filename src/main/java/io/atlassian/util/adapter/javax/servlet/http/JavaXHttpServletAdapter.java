package io.atlassian.util.adapter.javax.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpServletAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Enumeration;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXHttpServletAdapter extends HttpServlet implements Adapted<jakarta.servlet.http.HttpServlet> {

    private final jakarta.servlet.http.HttpServlet delegate;

    public static HttpServlet from(jakarta.servlet.http.HttpServlet delegate) {
        if (delegate instanceof JakartaHttpServletAdapter) {
            JakartaHttpServletAdapter castDelegate = (JakartaHttpServletAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXHttpServletAdapter::new);
    }

    protected JavaXHttpServletAdapter(jakarta.servlet.http.HttpServlet delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.http.HttpServlet getDelegate() {
        return delegate;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        try {
            delegate.service(asJakarta(req), asJakarta(res));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletName() {
        return delegate.getServletName();
    }

    @Override
    public void log(String message, Throwable t) {
        delegate.log(message, t);
    }

    @Override
    public void log(String msg) {
        delegate.log(msg);
    }

    @Override
    public void init() throws ServletException {
        try {
            delegate.init();
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            delegate.init(asJakarta(config));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return delegate.getServletInfo();
    }

    @Override
    public ServletContext getServletContext() {
        return asJavaX(delegate.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return asJavaX(delegate.getServletConfig());
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return delegate.getInitParameterNames();
    }

    @Override
    public String getInitParameter(String name) {
        return delegate.getInitParameter(name);
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
