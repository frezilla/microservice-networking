package eu.frezilla.networking.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class IP implements Serializable {

    private final IPV4 ipv4;
    private final IPV6 ipv6;

    public IP(IPV4 ipv4, IPV6 ipv6) {
        this.ipv4 = ipv4;
        this.ipv6 = ipv6;
    }
    
}
