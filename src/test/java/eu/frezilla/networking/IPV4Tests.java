package eu.frezilla.networking;

import eu.frezilla.networking.model.IPV4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IPV4Tests {
    
    @Test
    public void isValidTest() {
        
        Assertions.assertTrue(IPV4.isValid("127.0.0.1"));
        Assertions.assertFalse(IPV4.isValid("256.0.0.0"));
        Assertions.assertFalse(IPV4.isValid("0.-1.0.0"));
        Assertions.assertFalse(IPV4.isValid("a.0.0.0"));
        Assertions.assertTrue(IPV4.isValid("0.00.0.0"));
        Assertions.assertFalse(IPV4.isValid("0.00.0"));
        
    }
    
}
