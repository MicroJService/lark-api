package org.microjservice.lark;

import io.micronaut.context.annotation.Value;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.microjservice.lark.core.auth.models.Credential;


/**
 * Java compatible test for {@link LarkClient}
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@MicronautTest
public class LarkClientTestJ {
    @Value("${lark.app-id}")
    String appId;
    @Value("${lark.app-secret}")
    String appSecret;
    @Value("${lark.endpoint}")
    String endpoint;


    @Test
    public void testBuilder() throws Exception {
        LarkClient larkApi = new LarkClient.Builder()
                .withCredential(
                        new Credential(appId, appSecret, Credential.CredentialType.INTERNAL_APP)
                )
                .withEndpoint(endpoint)
                .build();
        Assertions.assertTrue(
                larkApi.getChatApi().list().getData().getGroups().size() > 0
        );
    }
}