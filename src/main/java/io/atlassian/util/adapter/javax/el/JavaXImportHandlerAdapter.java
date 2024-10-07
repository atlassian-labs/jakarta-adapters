package io.atlassian.util.adapter.javax.el;

import javax.el.ELException;
import javax.el.ImportHandler;

import static java.util.Objects.requireNonNull;

public class JavaXImportHandlerAdapter extends ImportHandler {

    private final jakarta.el.ImportHandler delegate;

    public JavaXImportHandlerAdapter(jakarta.el.ImportHandler delegate) {
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
