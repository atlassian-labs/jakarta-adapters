package io.atlassian.util.adapter.javax.servlet.jsp;

import io.atlassian.util.adapter.jakarta.el.JakartaELContextListenerAdapter;
import io.atlassian.util.adapter.javax.el.JavaXExpressionFactoryAdapter;

import javax.el.ELContextListener;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.servlet.jsp.JspApplicationContext;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXJspApplicationContextAdapter implements JspApplicationContext {

    private final jakarta.servlet.jsp.JspApplicationContext delegate;

    public JavaXJspApplicationContextAdapter(jakarta.servlet.jsp.JspApplicationContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void addELResolver(ELResolver resolver) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public ExpressionFactory getExpressionFactory() {
        return applyIfNonNull(delegate.getExpressionFactory(), JavaXExpressionFactoryAdapter::new);
    }

    @Override
    public void addELContextListener(ELContextListener listener) {
        delegate.addELContextListener(applyIfNonNull(listener, JakartaELContextListenerAdapter::new));
    }
}
