package eu.frezilla.networking.model;

import java.io.Serializable;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public final class IPV4 implements Serializable {

    private final String value;

    private IPV4(String value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public static IPV4 fromIPV4(IPV4 ipv4) {
        if (ipv4 == null) {
            return null;
        }
        return fromString(ipv4.toString());
    }

    public static IPV4 fromString(String value) {
        return new IPV4(value);
    }

    public static boolean isValid(String ip) {
        if (StringUtils.isEmpty(ip) || ip.endsWith(".")) {
            return false;
        }

        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            return false;
        }

        try {
            for (String part : parts) {
                int i = Integer.parseInt(part);
                if ((i < 0) || (i > 255)) {
                    return false;
                }
            }

            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
