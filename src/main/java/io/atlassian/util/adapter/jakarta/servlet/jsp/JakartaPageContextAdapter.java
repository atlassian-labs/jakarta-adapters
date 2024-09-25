package io.atlassian.util.adapter.jakarta.servlet.jsp;

import io.atlassian.util.adapter.jakarta.servlet.JakartaServletConfigAdapter;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpSessionAdapter;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.el.ExpressionEvaluator;
import jakarta.servlet.jsp.el.VariableResolver;

import java.io.IOException;
import java.util.Enumeration;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakartaIfJavaX;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaXIfJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaPageContextAdapter extends jakarta.servlet.jsp.PageContext {

    private final javax.servlet.jsp.PageContext delegate;

    public JakartaPageContextAdapter(javax.servlet.jsp.PageContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void initialize(Servlet servlet,
                           ServletRequest servletRequest,
                           ServletResponse servletResponse,
                           String s,
                           boolean b,
                           int i,
                           boolean b1) throws IOException, IllegalStateException, IllegalArgumentException {
        delegate.initialize(
                asJavaX(servlet),
                asJavaX(servletRequest),
                asJavaX(servletResponse),
                s, b, i, b1);
    }

    @Override
    public void release() {
        delegate.release();
    }

    @Override
    public HttpSession getSession() {
        return applyIfNonNull(delegate.getSession(), JakartaHttpSessionAdapter::new);
    }

    @Override
    public Object getPage() {
        return asJakartaIfJavaX(delegate.getPage());
    }

    @Override
    public ServletRequest getRequest() {
        return asJakarta(delegate.getRequest());
    }

    @Override
    public ServletResponse getResponse() {
        return asJakarta(delegate.getResponse());
    }

    @Override
    public Exception getException() {
        return delegate.getException();
    }

    @Override
    public ServletConfig getServletConfig() {
        return applyIfNonNull(delegate.getServletConfig(), JakartaServletConfigAdapter::new);
    }

    @Override
    public ServletContext getServletContext() {
        return asJakarta(delegate.getServletContext());
    }

    @Override
    public void forward(String s) throws ServletException, IOException {
        try {
            delegate.forward(s);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void include(String s) throws ServletException, IOException {
        try {
            delegate.include(s);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void include(String s, boolean b) throws ServletException, IOException {
        try {
            delegate.include(s, b);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void handlePageException(Exception e) throws ServletException, IOException {
        try {
            delegate.handlePageException(e);
        } catch (javax.servlet.ServletException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    public void handlePageException(Throwable throwable) throws ServletException, IOException {
        try {
            delegate.handlePageException(throwable);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void setAttribute(String s, Object o) {
        delegate.setAttribute(s, asJavaXIfJakarta(o));
    }

    @Override
    public void setAttribute(String s, Object o, int i) {
        delegate.setAttribute(s, asJavaXIfJakarta(o), i);
    }

    @Override
    public Object getAttribute(String s) {
        return asJakartaIfJavaX(delegate.getAttribute(s));
    }

    @Override
    public Object getAttribute(String s, int i) {
        return asJakartaIfJavaX(delegate.getAttribute(s, i));
    }

    @Override
    public Object findAttribute(String s) {
        return asJakartaIfJavaX(delegate.findAttribute(s));
    }

    @Override
    public void removeAttribute(String s) {
        delegate.removeAttribute(s);
    }

    @Override
    public void removeAttribute(String s, int i) {
        delegate.removeAttribute(s, i);
    }

    @Override
    public int getAttributesScope(String s) {
        return delegate.getAttributesScope(s);
    }

    @Override
    public Enumeration<String> getAttributeNamesInScope(int i) {
        return delegate.getAttributeNamesInScope(i);
    }

    @Override
    public JspWriter getOut() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ExpressionEvaluator getExpressionEvaluator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public VariableResolver getVariableResolver() {
        throw new UnsupportedOperationException();
    }

    @Override
    public jakarta.el.ELContext getELContext() {
        throw new UnsupportedOperationException();
    }
}