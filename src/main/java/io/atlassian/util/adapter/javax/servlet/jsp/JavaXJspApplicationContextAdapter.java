package io.atlassian.util.adapter.javax.servlet.jsp;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.el.JakartaELContextListenerAdapter;
import io.atlassian.util.adapter.jakarta.servlet.jsp.JakartaJspApplicationContextAdapter;
import io.atlassian.util.adapter.javax.el.JavaXExpressionFactoryAdapter;

import javax.el.ELContextListener;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.servlet.jsp.JspApplicationContext;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXJspApplicationContextAdapter implements JspApplicationContext, Adapted<jakarta.servlet.jsp.JspApplicationContext> {

    private final jakarta.servlet.jsp.JspApplicationContext delegate;

    public static JspApplicationContext from(jakarta.servlet.jsp.JspApplicationContext delegate) {
        if (delegate instanceof JakartaJspApplicationContextAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXJspApplicationContextAdapter::new);
    }

    JavaXJspApplicationContextAdapter(jakarta.servlet.jsp.JspApplicationContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.jsp.JspApplicationContext getDelegate() {
        return delegate;
    }

    @Override
    public void addELResolver(ELResolver resolver) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public ExpressionFactory getExpressionFactory() {
        return JavaXExpressionFactoryAdapter.from(delegate.getExpressionFactory());
    }

    @Override
    public void addELContextListener(ELContextListener listener) {
        delegate.addELContextListener(JakartaELContextListenerAdapter.from(listener));
    }
}
