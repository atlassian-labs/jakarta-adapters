package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.el.JakartaImportHandlerAdapter;

import javax.el.ELException;
import javax.el.ImportHandler;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXImportHandlerAdapter extends ImportHandler implements Adapted<jakarta.el.ImportHandler> {

    private final jakarta.el.ImportHandler delegate;

    public static ImportHandler from(jakarta.el.ImportHandler delegate) {
        if (delegate instanceof JakartaImportHandlerAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXImportHandlerAdapter::new);
    }

    JavaXImportHandlerAdapter(jakarta.el.ImportHandler delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.el.ImportHandler getDelegate() {
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
}
