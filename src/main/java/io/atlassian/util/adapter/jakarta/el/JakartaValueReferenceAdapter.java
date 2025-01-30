package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.el.JavaXValueReferenceAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.el.ValueReference;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JakartaValueReferenceAdapter extends ValueReference implements Adapted<javax.el.ValueReference> {

    private final javax.el.ValueReference delegate;

    public static ValueReference from(javax.el.ValueReference delegate) {
        if (delegate instanceof JavaXValueReferenceAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaValueReferenceAdapter::new);
    }

    protected JakartaValueReferenceAdapter(javax.el.ValueReference delegate) {
        super(delegate.getBase(), delegate.getProperty());
        this.delegate = delegate;
    }

    @Override
    public javax.el.ValueReference getDelegate() {
        return delegate;
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
