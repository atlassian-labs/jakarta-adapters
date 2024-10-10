package io.atlassian.util.adapter.jakarta.servlet.jsp;

import io.atlassian.util.adapter.javax.servlet.jsp.JavaXJspEngineInfoAdapter;
import jakarta.servlet.jsp.JspEngineInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaJspEngineInfoAdapterTest {

    @Mock
    private JspEngineInfo original;

    private JspEngineInfo biAdapted;

    @BeforeEach
    void setUp() {
        biAdapted = new JakartaJspEngineInfoAdapter(new JavaXJspEngineInfoAdapter(original));
    }

    @Test
    void getSpecificationVersion() {
        when(original.getSpecificationVersion()).thenReturn("1.0");

        assertEquals("1.0", biAdapted.getSpecificationVersion());
    }
}
