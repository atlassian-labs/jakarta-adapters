package io.atlassian.util.adapter.javax.servlet.http;

import io.atlassian.util.adapter.jakarta.servlet.http.JakartaWebConnectionAdapter;

import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.WebConnection;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXHttpUpgradeHandlerAdapter implements HttpUpgradeHandler {

    private final jakarta.servlet.http.HttpUpgradeHandler delegate;

    public JavaXHttpUpgradeHandlerAdapter(jakarta.servlet.http.HttpUpgradeHandler delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void init(WebConnection wc) {
        delegate.init(applyIfNonNull(wc, JakartaWebConnectionAdapter::new));
    }

    @Override
    public void destroy() {
        delegate.destroy();
    }
}
