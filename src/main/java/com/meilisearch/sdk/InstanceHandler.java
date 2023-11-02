package com.meilisearch.sdk;

import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.meilisearch.sdk.model.IndexStats;
import com.meilisearch.sdk.model.Stats;

/** Class providing information on the Meilisearch instance */
public class InstanceHandler {
    private final HttpClient httpClient;

    /**
     * Creates and sets up an instance of InstanceHandler
     *
     * @param config Meilisearch configuration
     */
    protected InstanceHandler(Config config) {
        this.httpClient = config.httpClient;
    }

    /**
     * Gets the status and availability of a Meilisearch instance
     *
     * @return Meilisearch API response
     * @throws MeilisearchApiException if an error occurs
     * @see <a href="https://www.meilisearch.com/docs/reference/api/health">API specification</a>
     */
    String health() {
        return httpClient.get("/health", String.class);
    }

    /**
     * Gets the status and availability of a Meilisearch instance
     *
     * @return Meilisearch API response
     * @throws MeilisearchApiException if an error occurs
     * @see <a href="https://www.meilisearch.com/docs/reference/api/health">API specification</a>
     */
    boolean isHealthy() {
        try {
            this.health();
            return true;
        } catch (MeilisearchException e) {
            return false;
        }
    }

    /**
     * Gets extended information and metrics about indexes and the Meilisearch database
     *
     * @return Meilisearch API response
     * @throws MeilisearchApiException if an error occurs
     * @see <a href="https://www.meilisearch.com/docs/reference/api/stats">API specification</a>
     */
    Stats getStats() {
        return httpClient.get("/stats", Stats.class);
    }

    /**
     * Gets extended information and metrics about indexes and the Meilisearch database
     *
     * @param uid Index identifier to the requested
     * @return Meilisearch API response
     * @throws MeilisearchApiException if an error occurs
     * @see <a href="https://www.meilisearch.com/docs/reference/api/stats">API specification</a>
     */
    IndexStats getIndexStats(String uid) {
        String requestQuery = "/indexes/" + uid + "/stats";
        return httpClient.<IndexStats>get(requestQuery, IndexStats.class);
    }

    /**
     * Gets the version of Meilisearch instance
     *
     * @return Meilisearch API response
     * @throws MeilisearchApiException if an error occurs
     * @see <a href="https://www.meilisearch.com/docs/reference/api/version">API specification</a>
     */
    String getVersion() {
        return httpClient.get("/version", String.class);
    }
}
