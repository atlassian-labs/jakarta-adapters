package io.atlassian.util.adapter.javax.servlet.http;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

import static java.util.Collections.emptyEnumeration;

public class JavaXHttpSessionContextAdapter implements HttpSessionContext {

    public static final HttpSessionContext INSTANCE = new JavaXHttpSessionContextAdapter();

    private JavaXHttpSessionContextAdapter() {
    }

    @Override
    public HttpSession getSession(String sessionId) {
        return null;
    }

    @Override
    public Enumeration<String> getIds() {
        return emptyEnumeration();
    }
}
