package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.el.JakartaMethodExpressionAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.el.MethodInfo;

import static io.atlassian.util.adapter.jakarta.JakartaJspAdapters.asJakartaJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXMethodExpressionAdapter extends MethodExpression implements Adapted<jakarta.el.MethodExpression> {

    private final jakarta.el.MethodExpression delegate;

    public static MethodExpression from(jakarta.el.MethodExpression delegate) {
        if (delegate instanceof JakartaMethodExpressionAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXMethodExpressionAdapter::new);
    }

    protected JavaXMethodExpressionAdapter(jakarta.el.MethodExpression delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.el.MethodExpression getDelegate() {
        return delegate;
    }

    @Override
    public boolean isParametersProvided() {
        return delegate.isParametersProvided();
    }

    @Override
    public MethodInfo getMethodInfo(ELContext context) {
        return JavaXMethodInfoAdapter.from(delegate.getMethodInfo(asJakartaJsp(context)));
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
    public boolean isLiteralText() {
        return delegate.isLiteralText();
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
