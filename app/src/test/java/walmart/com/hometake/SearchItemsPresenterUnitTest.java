package walmart.com.hometake;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import walmart.com.hometake.model.network.HomeTakeSearchServiceInterface;
import walmart.com.hometake.model.pojos.SearchResults;
import walmart.com.hometake.model.utils.NetworkConstants;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SearchItemsPresenterUnitTest {

    @Mock
    private HomeTakeSearchServiceInterface homeTakeServiceInterface;


    private Retrofit retrofit;

    @Before
    public void setUp() {

        retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testItemsListSuccess() {

        homeTakeServiceInterface = retrofit.create(HomeTakeSearchServiceInterface.class);
        Call<SearchResults> call =  homeTakeServiceInterface.getSearchResultsList("ipod", NetworkConstants.API_KEY, "json");

        try {
            Response<SearchResults> response = call.execute();
            SearchResults searchResults = response.body();
            assertTrue(response.isSuccessful() && searchResults.getItems()!=null && searchResults.getItems().size() > 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testItemsListFailure() {

        homeTakeServiceInterface = retrofit.create(HomeTakeSearchServiceInterface.class);
        Call<SearchResults> call =  homeTakeServiceInterface.getSearchResultsList("226689...cnscgsbxxchip", NetworkConstants.API_KEY, "json");
        try {
            Response<SearchResults> response = call.execute();
            assertFalse(response.isSuccessful());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
