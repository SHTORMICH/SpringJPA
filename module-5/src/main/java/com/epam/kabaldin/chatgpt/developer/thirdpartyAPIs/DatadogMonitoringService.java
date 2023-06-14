package com.epam.kabaldin.chatgpt.developer.thirdpartyAPIs;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.NonBlockingStatsDClientBuilder;
import com.timgroup.statsd.StatsDClient;

public class DatadogMonitoringService {
    private static final String DATADOG_API_KEY = "your_datadog_api_key";
    private static final String DATADOG_METRIC_PREFIX = "your_metric_prefix";
    private static NonBlockingStatsDClientBuilder nonBlockingStatsDClientBuilder = new NonBlockingStatsDClientBuilder();

    public static void main(String[] args) {
        nonBlockingStatsDClientBuilder.prefix(DATADOG_METRIC_PREFIX).hostname("localhost").port(8125).build();
        // Create a Datadog StatsD client
        StatsDClient statsd = new NonBlockingStatsDClient(nonBlockingStatsDClientBuilder);

        // Send a metric
        statsd.count("application.page.views", 1);

        // Increment a metric
        statsd.increment("application.request.count");

        // Decrement a metric
        statsd.decrement("application.queue.size");

        // Close the StatsD client
        statsd.close();
    }
}
