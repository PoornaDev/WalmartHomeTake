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
import walmart.com.hometake.model.pojos.Item;
import walmart.com.hometake.model.pojos.SearchResults;
import walmart.com.hometake.model.utils.NetworkConstants;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
public class RecommendedProductsPresenterUnitTest {

    @Mock
    private HomeTakeSearchServiceInterface homeTakeServiceInterface;

    private Retrofit retrofit;

    @Before
    public void setUp() {

        retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Test
    public void testRecommendedProductsSuccess() {

        homeTakeServiceInterface = retrofit.create(HomeTakeSearchServiceInterface.class);
        Call<List<Item>> call =  homeTakeServiceInterface.getRecommendedItems(NetworkConstants.API_KEY, "36904791");

        try {
            Response<List<Item>> response = call.execute();
            List<Item> recommendedProducts = response.body();
            assertTrue(response.isSuccessful() && recommendedProducts!=null &&recommendedProducts.size() > 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRecommendedProductsListFailure() {

        homeTakeServiceInterface = retrofit.create(HomeTakeSearchServiceInterface.class);
        Call<List<Item>> call =  homeTakeServiceInterface.getRecommendedItems(NetworkConstants.API_KEY, "1234456677899");
        try {
            Response<List<Item>> response = call.execute();
            assertFalse(response.isSuccessful());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testItemDetailsSuccess() {

        homeTakeServiceInterface = retrofit.create(HomeTakeSearchServiceInterface.class);
        Call<Item> call =  homeTakeServiceInterface.getRecommendedProductDetails("36904791", NetworkConstants.API_KEY);

        try {
            Response<Item> response = call.execute();
            Item itemDetails = response.body();
            assertTrue(response.isSuccessful() && itemDetails!=null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testItemDetailsFailure() {

        homeTakeServiceInterface = retrofit.create(HomeTakeSearchServiceInterface.class);
        Call<Item> call =  homeTakeServiceInterface.getRecommendedProductDetails("abcdef", NetworkConstants.API_KEY);
        try {
            Response<Item> response = call.execute();
            assertFalse(response.isSuccessful());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
