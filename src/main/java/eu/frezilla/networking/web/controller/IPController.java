package eu.frezilla.networking.web.controller;

import eu.frezilla.networking.model.IP;
import eu.frezilla.networking.model.IPV4;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("API de des adresses IP")
@RestController
public class IPController {
    
    private static final String[] IPV4_SERVICES = {
        "http://checkip.amazonaws.com/",
        "https://ipv4.icanhazip.com/",
        "http://myexternalip.com/raw",
        "http://ipecho.net/plain",
        "http://www.trackip.net/ip"
    };
    
    @ApiOperation("Retourne l'adresse IP publique")
    @GetMapping("/externalip")
    public MappingJacksonValue getExternalIP() {
        IP ip = null; 
        
        for (String ipv4Service : IPV4_SERVICES) {
            try {
                URL externalIpV4 = new URL(ipv4Service);
                BufferedReader br = new BufferedReader(new InputStreamReader(externalIpV4.openStream()));
                String ipv4Value = br.readLine();
                ip = new IP(IPV4.fromString(ipv4Value), null);
            } catch (IllegalArgumentException | IOException e) {
                ip = null;
            }
            if (ip != null) {
                break;
            }
        }
        
        return new MappingJacksonValue(ip);
    }
    
}
