package io.atlassian.util.adapter.javax.el;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.el.MethodInfo;

import static io.atlassian.util.adapter.jakarta.JakartaJspAdapters.asJakartaJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXMethodExpressionAdapter extends MethodExpression {

    private final jakarta.el.MethodExpression delegate;

    public JavaXMethodExpressionAdapter(jakarta.el.MethodExpression delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public boolean isParametersProvided() {
        return delegate.isParametersProvided();
    }

    @Override
    public MethodInfo getMethodInfo(ELContext context) {
        return applyIfNonNull(delegate.getMethodInfo(asJakartaJsp(context)), JavaXMethodInfoAdapter::new);
    }

    @Override
    public Object invoke(ELContext context, Object[] params) {
        return delegate.invoke(asJakartaJsp(context), params);
    }

    @Override
    public String getExpressionString() {
        return delegate.getExpressionString();
    }

    @Override
    public boolean equals(Object obj) {
        return delegate.equals(obj);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public boolean isLiteralText() {
        return delegate.isLiteralText();
    }
}