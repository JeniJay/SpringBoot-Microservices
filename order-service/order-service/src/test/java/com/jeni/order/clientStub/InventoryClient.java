package com.jeni.order.clientStub;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClient {

    public static void getInventory(String skuCode,int quantity)
    {
        stubFor(get(urlEqualTo("/api/inventory/skuCode"+skuCode+"/quantity"+quantity))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));
    }
}
