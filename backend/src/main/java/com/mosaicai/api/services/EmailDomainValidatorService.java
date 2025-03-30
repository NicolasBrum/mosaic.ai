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

            return records != null && records.length > 0;
        } catch (TextParseException e) {
            throw new IllegalArgumentException("Invalid domain syntax: " + domain);
        } catch (Exception e) {
            throw new RuntimeException("DNS lookup failed for domain: " + domain + " - " + e.getMessage());
        }
    }
}
