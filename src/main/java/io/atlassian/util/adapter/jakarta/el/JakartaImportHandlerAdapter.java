package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.el.JavaXImportHandlerAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.el.ELException;
import jakarta.el.ImportHandler;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaImportHandlerAdapter extends ImportHandler implements Adapted<javax.el.ImportHandler> {

    private final javax.el.ImportHandler delegate;

    public static ImportHandler from(javax.el.ImportHandler delegate) {
        if (delegate instanceof JavaXImportHandlerAdapter) {
            JavaXImportHandlerAdapter castDelegate = (JavaXImportHandlerAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaImportHandlerAdapter::new);
    }

    protected JakartaImportHandlerAdapter(javax.el.ImportHandler delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.el.ImportHandler getDelegate() {
        return delegate;
    }

    @Override
    public void importStatic(String name) throws ELException {
        delegate.importStatic(name);
    }

    @Override
    public void importClass(String name) throws ELException {
        delegate.importClass(name);
    }

    @Override
    public void importPackage(String packageName) {
        if (delegate == null) {
            return; // Ignore call from super constructor
        }
        delegate.importPackage(packageName);
    }

    @Override
    public Class<?> resolveClass(String name) {
        return delegate.resolveClass(name);
    }

    @Override
    public Class<?> resolveStatic(String name) {
        return delegate.resolveStatic(name);
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
