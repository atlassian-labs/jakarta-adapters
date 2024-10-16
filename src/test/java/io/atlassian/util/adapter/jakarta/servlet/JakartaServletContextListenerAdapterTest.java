package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXServletContextListenerAdapter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaServletContextListenerAdapterTest {

    @Mock
    private jakarta.servlet.ServletContextListener original;

    private jakarta.servlet.ServletContextListener biAdapted;

    @BeforeEach
    void setUp() {
        biAdapted = new JakartaServletContextListenerAdapter(new JavaXServletContextListenerAdapter(original));
    }

    @Test
    void contextInitialized() {
        var servletContextEvent = mock(ServletContextEvent.class);
        when(servletContextEvent.getServletContext()).thenReturn(mock(ServletContext.class));

        biAdapted.contextInitialized(servletContextEvent);

        verify(original).contextInitialized(any());
    }

    @Test
    void contextDestroyed() {
        var servletContextEvent = mock(ServletContextEvent.class);
        when(servletContextEvent.getServletContext()).thenReturn(mock(ServletContext.class));

        biAdapted.contextDestroyed(servletContextEvent);

        verify(original).contextDestroyed(any());
    }
}
