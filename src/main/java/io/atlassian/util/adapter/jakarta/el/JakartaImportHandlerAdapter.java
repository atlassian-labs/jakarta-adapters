package io.atlassian.util.adapter.jakarta.el;

import jakarta.el.ELException;
import jakarta.el.ImportHandler;

import static java.util.Objects.requireNonNull;

public class JakartaImportHandlerAdapter extends ImportHandler {

    private final javax.el.ImportHandler delegate;

    public JakartaImportHandlerAdapter(javax.el.ImportHandler delegate) {
        this.delegate = requireNonNull(delegate);
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
        if (delegate == null) return; // Ignore call from super constructor
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
