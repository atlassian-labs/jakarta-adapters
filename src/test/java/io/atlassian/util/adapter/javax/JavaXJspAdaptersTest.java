package io.atlassian.util.adapter.javax;

import jakarta.servlet.jsp.JspFactory;
import org.junit.jupiter.api.Test;

import static io.atlassian.util.adapter.javax.JavaXJspAdapters.asJavaXJsp;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JavaXJspAdaptersTest {

    @Test
    public void jspFactory() {
        var delegate = mock(JspFactory.class);
        var pageContext = mock(javax.servlet.jsp.PageContext.class);
        when(pageContext.getPage()).thenReturn("foo");

        var proxy = asJavaXJsp(delegate);
        proxy.releasePageContext(pageContext);

        verify(delegate).releasePageContext(argThat(pC -> pC.getPage().equals("foo")));
    }
}
