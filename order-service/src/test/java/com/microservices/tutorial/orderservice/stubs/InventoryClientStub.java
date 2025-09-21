package com.microservices.tutorial.orderservice.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub
{
    public static void stubInventoryCall(String skuCode, Integer quantity)
    {
        stubFor(get(urlEqualTo("/api/inventory" + skuCode + "&quantity" + quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));
    }
}
