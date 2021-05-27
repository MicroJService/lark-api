[![Kotlin](https://img.shields.io/badge/kotlin-1.3.x-blue.svg)](https://kotlinlang.org) [![Micronaut](https://img.shields.io/badge/micronaut-2.5.3-green.svg)](https://micronaut.io/)

## Overview

A Java/Kotlin library for [lark suite](https://open.larksuite.com/document/uMzMyEjLzMjMx4yMzITM/ugjMyEjL4IjMx4COyITM)
server side api.

## Getting Started

First, you have to create a lark suite bot to obtain the `app_id` and `app_secret` following the lark
suite [document](https://open.larksuite.com/document/ukzMxEjL5MTMx4SOzETM/uAzNwYjLwcDM24CM3AjN).
For [events subscription](https://open.larksuite.com/document/uMzMyEjLzMjMx4yMzITM/uETM4QjLxEDO04SMxgDN), set
the `Request URL` with `http://replace_with_your_host:10086/lark/event` and the `encrypt key` with a random string.

### Installation

For gradle:

```groovy
implementation 'org.microjservice.lark:lark-api:0.1.5'
```

For maven:

```xml

<dependency>
    <groupId>org.microjservice.lark</groupId>
    <artifactId>lark-api</artifactId>
    <version>0.1.4</version>
</dependency>
```

### Usage

For java:
```java
        LarkClient larkClient = new LarkClient.Builder()
                .withCredential(
                        new Credential(
                                "cli_xxxxxxxxxxxx",
                                "xxxxxxxxxxxxx",
                                //refer to the doc https://open.larksuite.com/document/uMzMyEjLzMjMx4yMzITM/ukjMyEjL5IjMx4SOyITM for more details
                                Credential.CredentialType.INTERNAL_APP
                        )
                )
                //default endpoint is https://open.feishu.cn/open-apis for China version, open.larksuite.com for oversea version
                .withEndpoint("https://open.larksuite.com/open-apis")
                .build();
        System.out.println(larkClient.getBotApi().info().getBot());
```

For micronaut framework, config the `application.yml`:
```yaml
lark:
  endpoint: https://open.larksuite.com/open-apis  #Alternative with https://open.feishu.cn in China
  app-id: cli_xxxxxx
  app-secret: RGExxxxxxxxx
```
And inject the `LarkClient`:
```java
    @Inject
    LarkClient larkClient;
```
That's it, lark-api aims to be more convenient than the [official sdk](https://github.com/larksuite/oapi-sdk-java).

#### Event subscription
If you register at least one EventConsumer, lark-api will start a http server with endpoint `http://0.0.0.0:10086/lark/event` to consume lark suite events. You can do this using builder api:

```java
        LarkClient larkApi = new LarkClient.Builder()
                .withCredential(
                        new Credential("cli_xxxxxx", "xxxxxxxx", Credential.CredentialType.INTERNAL_APP)
                )
                //more information in documention https://open.larksuite.com/document/uMzMyEjLzMjMx4yMzITM/uETM4QjLxEDO04SMxgDN
                .withEncryptKey("xxxxx")
                .withEventHandler(new EventConsumer() {
                    @Override
                    public void handleEvent(@NotNull EventRequest eventRequest) {
                        System.out.println("Receive: " + eventRequest.getEvent());
                    }

                    @NotNull
                    @Override
                    public Class<MessageEvent> getEventType() {
                        return MessageEvent.class;
                    }
                })
                .build();
```
Or use the `EventHandler` annotation:
```java
@EventHandler
public class MessageLogger implements EventConsumer<MessageEvent> {
    @NotNull
    @Override
    public Class<MessageEvent> getEventType() {
        return MessageEvent.class;
    }

    @Override
    public void handleEvent(@NotNull EventRequest<MessageEvent> eventRequest) {
        System.out.println("MessageLogger");
        System.out.println(eventRequest.getEvent());
    }
}

```
To use the annotation, you have to call the `Builder#withPackages("the base package for MessageLogger")` method. Besides, lark-api uses the [micronaut ioc framework](https://docs.micronaut.io/latest/guide/index.html#ioc) to process the `EventHandler` annotation, you need to add dependency:

Gradle:
```groovy
    annotationProcessor("io.micronaut:micronaut-inject-java:2.5.3")
```
Maven:
```xml
<annotationProcessorPaths>
    <path>
        <groupId>io.micronaut</groupId>
        <artifactId>micronaut-inject-java</artifactId>
        <version>2.5.3</version>
    </path>
</annotationProcessorPaths>
```
The event deliver is broadcast, meaning every `EventConsumer` will receive the corresponding type message. 

#### Messaging sending
Lark suite support text, image and post type message.
```java
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

```
For kotlin, we strongly recommend the [Kotlin DSL Builders](https://kotlinlang.org/docs/type-safe-builders.html) way to avoid the nested constructor hell in java, which is more like the html style:
```kotlin
 val message = message(language = Locale.US) {
        title { +"Title" }
        content {
            line {
                text { +"First line" }
                a(href = "https://github.com/MicroJService/lark-api") { +"Hyperlinks" }
                at(userid = "ou_1avnmsbv3k45jnk34j5") { +"tom" }
            }

            line {
                img(imagekey = "img_v2_80f5e116-af07-42a6-8df7-d7f07b14f1fg", width = 300, height = 300)
            }

            line {
                text { +"Second line:" }
                text { +"text testing" }
            }

            line {
                img(imagekey = "img_v2_80f5e116-af07-42a6-8df7-d7f07b14f1fg", width = 300, height = 300)
            }
        }
    }

larkClient.messageApi.send(
    ReceiveIdType.CHAT_ID,
    MessageRequest(
        "oc_760ffdaf006eb1b422af7bf64f8df2ec",
        I18nContent( null,message.toRichTextContent()),
        MessageType.POST
    )
)
```
