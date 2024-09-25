package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaServletConfigAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletRequestAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletResponseAdapter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXServletAdapter implements Servlet {

    private final jakarta.servlet.Servlet delegate;

    public JavaXServletAdapter(jakarta.servlet.Servlet delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.servlet.Servlet getDelegate() {
        return delegate;
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            delegate.init(applyIfNonNull(servletConfig, JakartaServletConfigAdapter::new));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        return applyIfNonNull(delegate.getServletConfig(), JavaXServletConfigAdapter::new);
    }

    @Override
    public void service(ServletRequest servletRequest,
                        ServletResponse servletResponse) throws ServletException, IOException {
        try {
            delegate.service(JakartaServletRequestAdapter.from(servletRequest), JakartaServletResponseAdapter.from(servletResponse));
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
}
