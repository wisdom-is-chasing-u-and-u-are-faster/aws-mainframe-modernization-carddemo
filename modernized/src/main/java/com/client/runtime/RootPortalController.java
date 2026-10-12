package com.client.runtime;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Root Modernization Portal Controller for Cloud Run service discovery.
 * Ensures the root URL '/' serves an active dashboard and JSON status
 * preventing HTTP 404 "Page not found" errors.
 */
@RestController
public class RootPortalController {

    private final ProgramRegistry registry;

    public RootPortalController(ProgramRegistry registry) {
        this.registry = registry;
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String homeHtml() {
        int progs = registry.all().size();
        return "<!DOCTYPE html>\n"
            + "<html lang=\"en\">\n"
            + "<head>\n"
            + "  <meta charset=\"UTF-8\">\n"
            + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            + "  <title>Modernized Mainframe Service — Persistent MMAP</title>\n"
            + "  <style>\n"
            + "    body { font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif; background: #0b111e; color: #f8fafc; margin: 0; padding: 40px 20px; }\n"
            + "    .container { max-width: 860px; margin: 0 auto; background: #131d31; border: 1px solid rgba(255,255,255,0.1); border-radius: 12px; padding: 32px; box-shadow: 0 10px 25px rgba(0,0,0,0.5); }\n"
            + "    h1 { color: #38bdf8; margin-top: 0; font-size: 24px; display: flex; align-items: center; gap: 10px; }\n"
            + "    .badge { background: #047857; color: #a7f3d0; font-size: 12px; font-weight: 700; padding: 4px 10px; border-radius: 9999px; text-transform: uppercase; }\n"
            + "    p { color: #94a3b8; line-height: 1.6; font-size: 14px; }\n"
            + "    .endpoints { margin-top: 24px; }\n"
            + "    .endpoint-card { background: #0a0f1d; border: 1px solid #1e293b; border-radius: 8px; padding: 14px 18px; margin-bottom: 12px; display: flex; justify-content: space-between; align-items: center; text-decoration: none; color: inherit; transition: border-color 0.2s; }\n"
            + "    .endpoint-card:hover { border-color: #38bdf8; }\n"
            + "    .method { background: #0284c7; color: #fff; font-size: 11px; font-weight: 700; padding: 3px 8px; border-radius: 4px; font-family: monospace; }\n"
            + "    .path { font-family: monospace; font-size: 13px; color: #f1f5f9; margin-left: 10px; }\n"
            + "    .desc { font-size: 12px; color: #64748b; }\n"
            + "  </style>\n"
            + "</head>\n"
            + "<body>\n"
            + "  <div class=\"container\">\n"
            + "    <div style=\"display:flex; justify-content:space-between; align-items:center;\">\n"
            + "      <h1>☁️ Modernized Mainframe Microservice <span class=\"badge\">Active · Healthy</span></h1>\n"
            + "    </div>\n"
            + "    <p>This service was synthesized and deployed by <strong>Persistent MMAP</strong> (Google Cloud Run + Cloud SQL). "
            + "It runs fully modernized Java 17 / Spring Boot 3.3 business logic with 100% semantic parity to the original mainframe programs.</p>\n"
            + "    <div class=\"endpoints\">\n"
            + "      <h3 style=\"color: #e2e8f0; font-size: 15px;\">Available Operational Endpoints</h3>\n"
            + "      <a class=\"endpoint-card\" href=\"/actuator/health\" target=\"_blank\">\n"
            + "        <div><span class=\"method\">GET</span><span class=\"path\">/actuator/health</span></div>\n"
            + "        <span class=\"desc\">Spring Boot Liveness & Readiness Probes</span>\n"
            + "      </a>\n"
            + "      <a class=\"endpoint-card\" href=\"/api/v1/programs\" target=\"_blank\">\n"
            + "        <div><span class=\"method\">GET</span><span class=\"path\">/api/v1/programs</span></div>\n"
            + "        <span class=\"desc\">Modernized Program Catalog (" + progs + " Programs)</span>\n"
            + "      </a>\n"
            + "      <a class=\"endpoint-card\" href=\"/api/v1/rules\" target=\"_blank\">\n"
            + "        <div><span class=\"method\">GET</span><span class=\"path\">/api/v1/rules</span></div>\n"
            + "        <span class=\"desc\">Synthesized Business Rules Specification</span>\n"
            + "      </a>\n"
            + "      <a class=\"endpoint-card\" href=\"/api/v1/database/status\" target=\"_blank\">\n"
            + "        <div><span class=\"method\">GET</span><span class=\"path\">/api/v1/database/status</span></div>\n"
            + "        <span class=\"desc\">Cloud SQL PostgreSQL Schema & Status</span>\n"
            + "      </a>\n"
            + "      <a class=\"endpoint-card\" href=\"/api/v1/screens\" target=\"_blank\">\n"
            + "        <div><span class=\"method\">GET</span><span class=\"path\">/api/v1/screens</span></div>\n"
            + "        <span class=\"desc\">Interactive 3270 / Modernized Form Endpoints</span>\n"
            + "      </a>\n"
            + "    </div>\n"
            + "  </div>\n"
            + "</body>\n"
            + "</html>\n";
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeJson() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("status", "UP");
        m.put("service", "Persistent MMAP Modernized Mainframe Microservice");
        m.put("runtime", "Spring Boot 3.3.0 / Java 17 / Cloud Run");
        m.put("health", "/actuator/health");
        m.put("programs", "/api/v1/programs");
        m.put("rules", "/api/v1/rules");
        m.put("database", "/api/v1/database/status");
        m.put("screens", "/api/v1/screens");
        return m;
    }
}
