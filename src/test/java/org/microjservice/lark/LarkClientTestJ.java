package org.microjservice.lark;

import io.micronaut.context.annotation.Value;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.microjservice.lark.api.models.MessageRequest;
import org.microjservice.lark.core.auth.models.Credential;
import org.microjservice.lark.api.models.MessageRequest.I18nContent.RichTextContent;

import org.microjservice.lark.api.models.MessageRequest.I18nContent.RichTextContent.Content.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

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

    @Inject
    LarkClient larkClient;


    @Test
    public void testBuilder() {
        LarkClient larkClient = new LarkClient.Builder()
                .withCredential(
                        new Credential(appId, appSecret, Credential.CredentialType.INTERNAL_APP)
                )
                .withEndpoint(endpoint)
                .build();
        Assertions.assertTrue(
                larkClient.getChatApi().list().blockingGet().getData().getGroups().size() > 0
        );
    }

    @Test
    public void testSendingMessage() throws MalformedURLException {

        RichTextContent englishContent = new RichTextContent(
                "Title",
                Arrays.asList(
                        Arrays.asList(
                                new TextContent("First line"),
                                new LinkContent(new URL("https://github.com/MicroJService/lark-api"), "Hyperlinks"),
                                new AtContent("ou_1avnmsbv3k45jnk34j5", "tom")
                        ),
                        Collections.singletonList(
                                new ImgContent("img_v2_80f5e116-af07-42a6-8df7-d7f07b14f1fg", 300, 300)
                        ),
                        Arrays.asList(
                                new TextContent("Second line:"),
                                new TextContent("text testing")
                        ),
                        Collections.singletonList(
                                new ImgContent("img_v2_80f5e116-af07-42a6-8df7-d7f07b14f1fg", 300, 300)
                        )
                )
        );

        MessageRequest messageRequest = new MessageRequest(
                "oc_760ffdaf006eb1b422af7bf64f8df2ec",
                new MessageRequest.I18nContent(
                        null,englishContent
                ),
                MessageRequest.MessageType.POST
        );

        larkClient.getMessageApi().send(MessageRequest.ReceiveIdType.CHAT_ID, messageRequest);

    }
}