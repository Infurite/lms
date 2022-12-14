package com.temchik.lms.config.tenant;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class TenantContext {

    private static final InheritableThreadLocal<UUID> currentTenant = new InheritableThreadLocal<>();

    public static void setTenantId(UUID tenantId) {
        log.debug("Setting tenantId to " + tenantId);
        currentTenant.set(tenantId);
    }

    public static UUID getTenantId() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
}
