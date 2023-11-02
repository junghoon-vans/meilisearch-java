package com.meilisearch.sdk;

import com.meilisearch.sdk.exceptions.MeilisearchApiException;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.meilisearch.sdk.http.URLBuilder;
import com.meilisearch.sdk.model.IndexesQuery;
import com.meilisearch.sdk.model.Results;
import com.meilisearch.sdk.model.TaskInfo;
import java.util.HashMap;

/**
 * Class covering the Meilisearch Index API.
 *
 * @see <a href="https://www.meilisearch.com/docs/reference/api/indexes">API specification</a>
 */
class IndexesHandler {
    private final HttpClient httpClient;

    /**
     * Creates and sets up an instance of IndexesHandler to simplify Meilisearch API calls to manage
     * indexes
     *
     * @param config Meilisearch configuration
     */
    protected IndexesHandler(Config config) {
        this.httpClient = config.httpClient;
    }

    /**
     * Creates an index with a unique identifier
     *
     * @param uid Unique identifier of the index
     * @return Meilisearch API response as TaskInfo
     * @throws MeilisearchApiException if an error occurs
     */
    TaskInfo createIndex(String uid) {
        return this.createIndex(uid, null);
    }

    /**
     * Creates an index with a unique identifier
     *
     * @param uid Unique identifier of the index
     * @param primaryKey Field to use as the primary key for documents in that index
     * @return Meilisearch API response as TaskInfo
     * @throws MeilisearchApiException if an error occurs
     */
    TaskInfo createIndex(String uid, String primaryKey) {
        HashMap<String, String> index = new HashMap<String, String>();
        index.put("uid", uid);
        index.put("primaryKey", primaryKey);

        return httpClient.post(indexesPath().getURL(), index, TaskInfo.class);
    }

    /**
     * Gets an index from its uid
     *
     * @param uid Unique identifier of the index to get
     * @return Meilisearch API response as Index instance
     * @throws MeilisearchApiException if an error occurs
     */
    Index getIndex(String uid) {
        return httpClient.get(indexesPath().addSubroute(uid).getURL(), Index.class);
    }

    /**
     * Gets indexes in the current Meilisearch instance
     *
     * @return Results containing a list of indexes
     * @throws MeilisearchApiException if an error occurs
     */
    Results<Index> getIndexes() {
        return httpClient.get(indexesPath().getURL(), Results.class, Index.class);
    }

    /**
     * Gets indexes in the current Meilisearch instance
     *
     * @param params parameters accepted by the indexes route
     * @return Results containing a list of indexes
     * @throws MeilisearchApiException if an error occurs
     */
    Results<Index> getIndexes(IndexesQuery params) {
        return httpClient.get(
                indexesPath().addQuery(params.toQuery()).getURL(), Results.class, Index.class);
    }

    /**
     * Gets indexes in the current Meilisearch instance
     *
     * @return List of indexes as String
     * @throws MeilisearchApiException if an error occurs
     */
    String getRawIndexes() {
        return httpClient.get(indexesPath().getURL(), String.class);
    }

    /**
     * Gets indexes in the current Meilisearch instance
     *
     * @param params parameters accepted by the indexes route
     * @return List of indexes as String
     * @throws MeilisearchApiException if an error occurs
     */
    String getRawIndexes(IndexesQuery params) {
        return httpClient.get(indexesPath().addQuery(params.toQuery()).getURL(), String.class);
    }

    /**
     * Updates the primary key of an index in the Meilisearch instance
     *
     * @param uid Unique identifier of the index to update
     * @param primaryKey New primary key field to use for documents in that index
     * @return Meilisearch API response as TaskInfo
     * @throws MeilisearchApiException if an error occurs
     */
    TaskInfo updatePrimaryKey(String uid, String primaryKey) {
        HashMap<String, String> index = new HashMap<String, String>();
        index.put("primaryKey", primaryKey);

        return httpClient.patch(indexesPath().addSubroute(uid).getURL(), index, TaskInfo.class);
    }

    /**
     * Deletes an index in the Meilisearch instance
     *
     * @param uid Unique identifier of the index to delete
     * @return Meilisearch API response as TaskInfo
     * @throws MeilisearchApiException if an error occurs
     */
    TaskInfo deleteIndex(String uid) {
        return httpClient.delete(indexesPath().addSubroute(uid).getURL(), TaskInfo.class);
    }

    /** Creates an URLBuilder for the constant route indexes */
    private URLBuilder indexesPath() {
        return new URLBuilder("/indexes");
    }
}
