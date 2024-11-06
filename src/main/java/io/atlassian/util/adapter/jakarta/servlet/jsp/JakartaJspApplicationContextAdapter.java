package io.atlassian.util.adapter.jakarta.servlet.jsp;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.el.JakartaExpressionFactoryAdapter;
import io.atlassian.util.adapter.javax.el.JavaXELContextListenerAdapter;
import io.atlassian.util.adapter.javax.servlet.jsp.JavaXJspApplicationContextAdapter;
import jakarta.el.ELContextListener;
import jakarta.el.ELResolver;
import jakarta.el.ExpressionFactory;
import jakarta.servlet.jsp.JspApplicationContext;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaJspApplicationContextAdapter implements JspApplicationContext, Adapted<javax.servlet.jsp.JspApplicationContext> {

    private final javax.servlet.jsp.JspApplicationContext delegate;

    public static JspApplicationContext from(javax.servlet.jsp.JspApplicationContext delegate) {
        if (delegate instanceof JavaXJspApplicationContextAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaJspApplicationContextAdapter::new);
    }

    JakartaJspApplicationContextAdapter(javax.servlet.jsp.JspApplicationContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.jsp.JspApplicationContext getDelegate() {
        return delegate;
    }

    @Override
    public void addELResolver(ELResolver resolver) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public ExpressionFactory getExpressionFactory() {
        return JakartaExpressionFactoryAdapter.from(delegate.getExpressionFactory());
    }

    @Override
    public void addELContextListener(ELContextListener listener) {
        delegate.addELContextListener(JavaXELContextListenerAdapter.from(listener));
    }
}
