package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.el.JakartaELContextEventAdapter;

import javax.el.ELContextEvent;

import static io.atlassian.util.adapter.javax.JavaXJspAdapters.asJavaXJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JavaXELContextEventAdapter extends ELContextEvent implements Adapted<jakarta.el.ELContextEvent> {

    private final jakarta.el.ELContextEvent delegate;

    public static ELContextEvent from(jakarta.el.ELContextEvent delegate) {
        if (delegate instanceof JakartaELContextEventAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXELContextEventAdapter::new);
    }

    protected JavaXELContextEventAdapter(jakarta.el.ELContextEvent delegate) {
        super(asJavaXJsp(delegate.getELContext()));
        this.delegate = delegate;
    }

    @Override
    public jakarta.el.ELContextEvent getDelegate() {
        return delegate;
    }
}
