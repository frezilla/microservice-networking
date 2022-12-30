package eu.frezilla.networking.model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class IPV6 implements Serializable {

    private final String value;

    private IPV6(String value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public static IPV6 fromIPV6(IPV6 ipv6) {
        if (ipv6 == null) {
            return null;
        }
        return fromString(ipv6.toString());
    }

    public static IPV6 fromString(String ip) {
        return new IPV6(ip);
    }

    public static boolean isValid(String ip) {
        if (StringUtils.isEmpty(ip) || ip.endsWith(":")) {
            return false;
        }

        String[] parts = ip.split(":");
        if (parts.length != 8) {
            return false;
        }

        Pattern pattern = Pattern.compile("[0-9a-fA-F]{1,4}");

        for (String part : parts) {
            int partLength = StringUtils.length(part);
            if (partLength < 1 && partLength > 4) {
                return false;
            }
            Matcher matcher = pattern.matcher(part);
            if (!matcher.matches()) {
                return false;
            }
        }

        return true;
    }
    
}
