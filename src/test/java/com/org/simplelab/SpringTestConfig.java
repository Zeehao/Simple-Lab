package com.org.simplelab;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * IMPORTANT NOTE ON WRITING TESTS:
 * TESTS DO NOT RUN IN ORDER OF DECLARATION. Refer to @TestMethodOrder for the order in which
 * tests will run.
 *
 * Notes on DB testing:
 * When writing tests that add entities to the DB, make sure the _metadata field has some value that you can
 * query to delete all the entities you added. This can be done in the cleanup() method.
 * Refer to User_insertionTest() in DBTests.java for an example of this.
 */

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@SpringBootTest
public abstract class SpringTestConfig {

    /**
     * Notes on metadata:
     * This metadata field is used to identify entities created during a specific test instance
     * You should have some method at the end of your test class which deletes all the entities
     * you created by querying the DB for this metadata.
     */
    static String metadata;

    @BeforeAll
    static void generateMetadata(){
        int length = 25;
        boolean useLetters = true;
        boolean useNumbers = true;
        metadata = RandomStringUtils.random(length, useLetters, useNumbers);
    }



}