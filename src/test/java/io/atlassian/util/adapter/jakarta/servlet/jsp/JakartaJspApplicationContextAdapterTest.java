package io.atlassian.util.adapter.jakarta.servlet.jsp;

import io.atlassian.util.adapter.javax.servlet.jsp.JavaXJspApplicationContextAdapter;
import jakarta.el.ELContext;
import jakarta.el.ELContextEvent;
import jakarta.el.ELContextListener;
import jakarta.el.ExpressionFactory;
import jakarta.servlet.jsp.JspApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaJspApplicationContextAdapterTest {

    @Mock
    private JspApplicationContext original;

    private JspApplicationContext biAdapted;

    @BeforeEach
    void setUp() {
        biAdapted = new JakartaJspApplicationContextAdapter(new JavaXJspApplicationContextAdapter(original));
    }

    @Test
    void addELResolver() {
        assertThrows(UnsupportedOperationException.class, () -> biAdapted.addELResolver(null));
    }

    @Test
    void getExpressionFactory() throws Exception {
        var method = Object.class.getMethod("hashCode");
        var exprFactory = mock(ExpressionFactory.class);
        when(exprFactory.getInitFunctionMap()).thenReturn(Map.of("key", method));
        when(original.getExpressionFactory()).thenReturn(exprFactory);

        assertEquals(method, biAdapted.getExpressionFactory().getInitFunctionMap().get("key"));
    }

    @Test
    void addELContextListener() {
        var listener = mock(ELContextListener.class);
        var event = mock(ELContextEvent.class);
        var context = mock(ELContext.class);
        when(context.isPropertyResolved()).thenReturn(true);
        when(event.getELContext()).thenReturn(context);

        biAdapted.addELContextListener(listener);

        verify(original).addELContextListener(assertArg(listenerArg -> {
            listenerArg.contextCreated(event);
            verify(listener).contextCreated(argThat(e -> e.getELContext().isPropertyResolved()));
            clearInvocations(listener);
        }));
    }
}
