package io.atlassian.util.adapter.jakarta.servlet.jsp;

import io.atlassian.util.adapter.jakarta.el.JakartaExpressionFactoryAdapter;
import io.atlassian.util.adapter.javax.el.JavaXELContextListenerAdapter;
import jakarta.el.ELContextListener;
import jakarta.el.ELResolver;
import jakarta.el.ExpressionFactory;
import jakarta.servlet.jsp.JspApplicationContext;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaJspApplicationContextAdapter implements JspApplicationContext {

    private final javax.servlet.jsp.JspApplicationContext delegate;

    public JakartaJspApplicationContextAdapter(javax.servlet.jsp.JspApplicationContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void addELResolver(ELResolver resolver) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public ExpressionFactory getExpressionFactory() {
        return applyIfNonNull(delegate.getExpressionFactory(), JakartaExpressionFactoryAdapter::new);
    }

    @Override
    public void addELContextListener(ELContextListener listener) {
        delegate.addELContextListener(applyIfNonNull(listener, JavaXELContextListenerAdapter::new));
    }
}
