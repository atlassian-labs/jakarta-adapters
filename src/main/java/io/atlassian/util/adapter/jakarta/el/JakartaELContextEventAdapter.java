package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.el.JavaXELContextEventAdapter;
import jakarta.el.ELContextEvent;

import static io.atlassian.util.adapter.jakarta.JakartaJspAdapters.asJakartaJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JakartaELContextEventAdapter extends ELContextEvent implements Adapted<javax.el.ELContextEvent> {

    private final javax.el.ELContextEvent delegate;

    public static ELContextEvent from(javax.el.ELContextEvent delegate) {
        if (delegate instanceof JavaXELContextEventAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaELContextEventAdapter::new);
    }

    JakartaELContextEventAdapter(javax.el.ELContextEvent delegate) {
        super(asJakartaJsp(delegate.getELContext()));
        this.delegate = delegate;
    }

    @Override
    public javax.el.ELContextEvent getDelegate() {
        return delegate;
    }
}
