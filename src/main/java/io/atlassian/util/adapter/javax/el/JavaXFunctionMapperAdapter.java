package io.atlassian.util.adapter.javax.el;

import javax.el.FunctionMapper;
import java.lang.reflect.Method;

import static java.util.Objects.requireNonNull;

public class JavaXFunctionMapperAdapter extends FunctionMapper {

    private final jakarta.el.FunctionMapper delegate;

    public JavaXFunctionMapperAdapter(jakarta.el.FunctionMapper delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void mapFunction(String prefix, String localName, Method meth) {
        delegate.mapFunction(prefix, localName, meth);
    }

    @Override
    public Method resolveFunction(String s, String s1) {
        return delegate.resolveFunction(s, s1);
    }
}
