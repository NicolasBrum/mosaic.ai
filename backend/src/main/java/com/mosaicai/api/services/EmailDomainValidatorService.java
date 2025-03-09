package com.mosaicai.api.services;

import org.springframework.stereotype.Service;
import org.xbill.DNS.*;
import org.xbill.DNS.Record;

@Service
public class EmailDomainValidatorService {
    public boolean isValidEmailDomain(String email) {
        String domain = email.split("@")[1];
        try {
            Lookup lookup = new Lookup(domain, Type.MX);
            lookup.setCache(null);
            Record[] records = lookup.run();

            // Verifica se o domínio tem registros MX
            return records != null && records.length > 0;
        } catch (TextParseException e) {
            // Lança uma exceção com uma mensagem detalhada
            throw new IllegalArgumentException("Invalid domain syntax: " + domain);
        } catch (Exception e) {
            // Exceção genérica para erros de DNS
            throw new RuntimeException("DNS lookup failed for domain: " + domain + " - " + e.getMessage());
        }
    }
}
