package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.el.JakartaValueReferenceAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.el.ValueReference;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JavaXValueReferenceAdapter extends ValueReference implements Adapted<jakarta.el.ValueReference> {

    private final jakarta.el.ValueReference delegate;

    public static ValueReference from(jakarta.el.ValueReference delegate) {
        if (delegate instanceof JakartaValueReferenceAdapter) {
            JakartaValueReferenceAdapter castDelegate = (JakartaValueReferenceAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXValueReferenceAdapter::new);
    }

    protected JavaXValueReferenceAdapter(jakarta.el.ValueReference delegate) {
        super(delegate.getBase(), delegate.getProperty());
        this.delegate = delegate;
    }

    @Override
    public jakarta.el.ValueReference getDelegate() {
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
