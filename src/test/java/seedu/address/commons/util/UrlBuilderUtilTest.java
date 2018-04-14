package seedu.address.commons.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UrlBuilderUtilTest {
    private static final String TEST_BASE_URL ="https://min-api.cryptocompare.com/data/pricemulti";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void buildUrl() {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("fsyms", "BTC,ETH"));
        params.add(new BasicNameValuePair("tsyms", "USD,EUR"));

        // valid case
        assertEquals(TEST_BASE_URL + "?fsyms=BTC%2CETH&tsyms=USD%2CEUR",
                UrlBuilderUtil.buildUrl(TEST_BASE_URL, params));

        // null url parameter -> throws NullPointerException
        thrown.expect(NullPointerException.class);
        UrlBuilderUtil.buildUrl(null, params);

        // null params parameter -> throws NullPointerException
        thrown.expect(NullPointerException.class);
        UrlBuilderUtil.buildUrl(TEST_BASE_URL, null);

        // null both parameters -> throws NullPointerException
        thrown.expect(NullPointerException.class);
        UrlBuilderUtil.buildUrl(null, null);
    }
}
