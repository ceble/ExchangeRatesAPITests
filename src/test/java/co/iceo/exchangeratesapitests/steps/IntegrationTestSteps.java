package co.iceo.exchangeratesapitests.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IntegrationTestSteps {
    public static final String BASE_URL = "https://api.apilayer.com/exchangerates_data";
    private Response response;
    private String apikey = "5lGfVB7YRVwDti7FG2ojB0TZGLF650Ue";
    private String endpoint = "/latest";
    private String symbols;
    private String base;

    @When("^the client requests to the latest exchange rates endpoint$")
    public void makeRequestToLatestExchangeRatesEndpoint() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + endpoint).newBuilder();
        urlBuilder.addQueryParameter("symbols", symbols).addQueryParameter("base", base);

        String url = urlBuilder.build().toString();


        Request request = new Request.Builder()
                .url(url)
                .addHeader("apikey", apikey)
                .build();

        response = client.newCall(request).execute();

    }

    @Then("the API should return a {int} response code")
    public void verifyResponseCode(int expectedResponseCode) {
        int actualResponseCode = response.code();
        assertEquals(expectedResponseCode, actualResponseCode);
    }

    @Given("an invalid API key")
    public void anInvalidAPIKey() {
        apikey = "invalidAPIkey";

    }

    @Given("a non-existent endpoint")
    public void aNonExistentEndpoint() {
        endpoint = "/notanendpoint";

    }

    @Given("the client requests {string} and base {string}")
    public void theExchangeSymbolAndBase(String symbol, String base) {
        this.symbols = symbol;
        this.base = base;
    }

    @And("the client revives response with success status base {string} and rates for {string}")
    public void verifyResponseWithSuccessStatusAndRequestedRates(String base, String rate) throws IOException {
        assert response.body() != null;
        String body = response.body().string();
        assertTrue(body.contains(base));
        assertTrue(body.contains(rate));

    }

    @Given("a request with invalid parameters")
    public void aRequestWithInvalidParameters() {
        symbols = "notSymbol";
        base = "notBase";
    }

    @Given("a valid API key with insufficient access rights")
    public void aValidAPIKeyWithInsufficientAccessRights() {
        apikey = "5lGfVB7YRVwDti7FG2ojB0TZGLF650Uf";
        // this doesn't work I don't find idea how to get one :/
    }
}
