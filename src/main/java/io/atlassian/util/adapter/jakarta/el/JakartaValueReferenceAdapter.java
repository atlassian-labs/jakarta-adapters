package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.javax.el.JavaXValueReferenceAdapter;
import jakarta.el.ValueReference;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JakartaValueReferenceAdapter extends ValueReference {

    private final javax.el.ValueReference delegate;

    public static ValueReference from(javax.el.ValueReference delegate) {
        if (delegate instanceof JavaXValueReferenceAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaValueReferenceAdapter::new);
    }

    JakartaValueReferenceAdapter(javax.el.ValueReference delegate) {
        super(delegate.getBase(), delegate.getProperty());
        this.delegate = delegate;
    }

    public javax.el.ValueReference getDelegate() {
        return delegate;
    }
}
