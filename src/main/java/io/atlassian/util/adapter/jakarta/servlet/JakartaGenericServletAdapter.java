package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpServletAdapter;
import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.util.Enumeration;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaGenericServletAdapter extends GenericServlet {

    private final javax.servlet.GenericServlet delegate;

    public static GenericServlet from(javax.servlet.GenericServlet delegate) {
        if (delegate instanceof javax.servlet.http.HttpServlet castDelegate) {
            return new JakartaHttpServletAdapter(castDelegate);
        }
        return applyIfNonNull(delegate, JakartaGenericServletAdapter::new);
    }

    private JakartaGenericServletAdapter(javax.servlet.GenericServlet delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public javax.servlet.GenericServlet getDelegate() {
        return delegate;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        try {
            delegate.service(asJavaX(req), asJavaX(res));
        } catch (javax.servlet.ServletException e) {
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
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            delegate.init(asJavaX(config));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return delegate.getServletInfo();
    }

    @Override
    public ServletContext getServletContext() {
        return asJakarta(delegate.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return asJakarta(delegate.getServletConfig());
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
}
