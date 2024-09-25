package io.atlassian.util.adapter.javax.servlet.http;

import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.MappingMatch;

import static java.util.Objects.requireNonNull;

public class JavaXHttpServletMappingAdapter implements HttpServletMapping {

    private final jakarta.servlet.http.HttpServletMapping delegate;

    public JavaXHttpServletMappingAdapter(jakarta.servlet.http.HttpServletMapping delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public String getMatchValue() {
        return delegate.getMatchValue();
    }

    @Override
    public String getPattern() {
        return delegate.getPattern();
    }

    @Override
    public String getServletName() {
        return delegate.getServletName();
    }

    @Override
    public MappingMatch getMappingMatch() {
        return MappingMatch.valueOf(delegate.getMappingMatch().name());
    }
}
