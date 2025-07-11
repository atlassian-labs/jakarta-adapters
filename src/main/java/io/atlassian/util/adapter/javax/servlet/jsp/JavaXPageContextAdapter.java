package io.atlassian.util.adapter.javax.servlet.jsp;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.jsp.JakartaPageContextAdapter;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpSessionAdapter;
import io.atlassian.util.adapter.javax.servlet.jsp.el.JavaXExpressionEvaluatorAdapter;
import io.atlassian.util.adapter.javax.servlet.jsp.el.JavaXVariableResolverAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.el.ELContext;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;
import java.io.IOException;
import java.util.Enumeration;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakartaIfJavaX;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaXIfJakarta;
import static io.atlassian.util.adapter.javax.JavaXJspAdapters.asJavaXJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXPageContextAdapter extends PageContext implements Adapted<jakarta.servlet.jsp.PageContext> {

    private final jakarta.servlet.jsp.PageContext delegate;

    public static PageContext from(jakarta.servlet.jsp.PageContext delegate) {
        if (delegate instanceof JakartaPageContextAdapter) {
            JakartaPageContextAdapter castDelegate = (JakartaPageContextAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXPageContextAdapter::new);
    }

    protected JavaXPageContextAdapter(jakarta.servlet.jsp.PageContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.jsp.PageContext getDelegate() {
        return delegate;
    }

    @Override
    public void initialize(Servlet servlet,
                           ServletRequest servletRequest,
                           ServletResponse servletResponse,
                           String s,
                           boolean b,
                           int i,
                           boolean b1) throws IOException, IllegalStateException, IllegalArgumentException {
        delegate.initialize(asJakarta(servlet), asJakarta(servletRequest), asJakarta(servletResponse), s, b, i, b1);
    }

    @Override
    public void release() {
        delegate.release();
    }

    @Override
    public HttpSession getSession() {
        return JavaXHttpSessionAdapter.from(delegate.getSession());
    }

    @Override
    public Object getPage() {
        return asJavaXIfJakarta(delegate.getPage());
    }

    @Override
    public ServletRequest getRequest() {
        return asJavaX(delegate.getRequest());
    }

    @Override
    public ServletResponse getResponse() {
        return asJavaX(delegate.getResponse());
    }

    @Override
    public Exception getException() {
        return delegate.getException();
    }

    @Override
    public ServletConfig getServletConfig() {
        return asJavaX(delegate.getServletConfig());
    }

    @Override
    public ServletContext getServletContext() {
        return asJavaX(delegate.getServletContext());
    }

    @Override
    public void forward(String s) throws ServletException, IOException {
        try {
            delegate.forward(s);
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void include(String s) throws ServletException, IOException {
        try {
            delegate.include(s);
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void include(String s, boolean b) throws ServletException, IOException {
        try {
            delegate.include(s, b);
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void handlePageException(Exception ex) throws ServletException, IOException {
        try {
            delegate.handlePageException(ex);
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void handlePageException(Throwable throwable) throws ServletException, IOException {
        try {
            delegate.handlePageException(throwable);
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void setAttribute(String s, Object o) {
        delegate.setAttribute(s, asJakartaIfJavaX(o));
    }

    @Override
    public void setAttribute(String s, Object o, int i) {
        delegate.setAttribute(s, asJakartaIfJavaX(o), i);
    }

    @Override
    public Object getAttribute(String s) {
        return asJavaXIfJakarta(delegate.getAttribute(s));
    }

    @Override
    public Object getAttribute(String s, int i) {
        return asJavaXIfJakarta(delegate.getAttribute(s, i));
    }

    @Override
    public Object findAttribute(String s) {
        return asJavaXIfJakarta(delegate.findAttribute(s));
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
        return JavaXJspWriterAdapter.from(delegate.getOut());
    }

    @Override
    public ExpressionEvaluator getExpressionEvaluator() {
        return JavaXExpressionEvaluatorAdapter.from(delegate.getExpressionEvaluator());
    }

    @Override
    public VariableResolver getVariableResolver() {
        return JavaXVariableResolverAdapter.from(delegate.getVariableResolver());
    }

    @Override
    public ELContext getELContext() {
        return asJavaXJsp(delegate.getELContext());
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
