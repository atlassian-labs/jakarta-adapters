package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.jakarta.el.JakartaValueReferenceAdapter;

import javax.el.ValueReference;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JavaXValueReferenceAdapter extends ValueReference {

    private final jakarta.el.ValueReference delegate;

    public static ValueReference from(jakarta.el.ValueReference delegate) {
        if (delegate instanceof JakartaValueReferenceAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXValueReferenceAdapter::new);
    }

    JavaXValueReferenceAdapter(jakarta.el.ValueReference delegate) {
        super(delegate.getBase(), delegate.getProperty());
        this.delegate = delegate;
    }

    public jakarta.el.ValueReference getDelegate() {
        return delegate;
    }
}
