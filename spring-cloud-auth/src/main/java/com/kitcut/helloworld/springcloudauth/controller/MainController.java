package com.kitcut.helloworld.springcloudauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class MainController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        return "<a href='showAllServiceIds'>Show All Service Ids</a>";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String home() {

        return "<a href='showAllServiceIds'>Show All Service Ids</a>";
    }

    @RequestMapping(value = "/showAllServiceIds", method = RequestMethod.GET)
    public String showAllServiceIds() {

        List<String> serviceIds = this.discoveryClient.getServices();

        if (serviceIds == null || serviceIds.isEmpty()) {
            return "No services found!";
        }
        String html = "<h3>Service Ids:</h3>";
        for (String serviceId : serviceIds) {
            html += "<br><a href='showService?serviceId=" + serviceId + "'>" + serviceId + "</a>";
        }
        return html;
    }

    @RequestMapping(value = "/showService", method = RequestMethod.POST)
    public String showFirstService(@RequestParam(defaultValue = "") String serviceId) {

        // (Need!!) eureka.client.fetchRegistry=true
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);

        if (instances == null || instances.isEmpty()) {
            return "No instances for service: " + serviceId;
        }
        String html = "<h2>Instances for Service Id: " + serviceId + "</h2>";

        for (ServiceInstance serviceInstance : instances) {
            html += "<h3>Instance: " + serviceInstance.getUri() + "</h3>";
            html += "Host: " + serviceInstance.getHost() + "<br>";
            html += "Port: " + serviceInstance.getPort() + "<br>";
        }

        return html;
    }

    // A REST method, To call from another service.
    // See in Lesson "Load Balancing with Ribbon".
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String hello() {
        return "<html>Hello from ABC-SERVICE</html>" + environment.getProperty("local.server.port");
    }

}
