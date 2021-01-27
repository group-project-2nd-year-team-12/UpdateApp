package com.example.demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SearchViewFood extends AppCompatActivity {
//    private static final String base_url="http://10.0.2.2";
//    private static final String full_url="Android/files/post.php";
//
//    class Spacecraft{
//        @SerializedName("B_post_id")
//        private int B_post_id;
//
//        @SerializedName("category")
//        private String category;
//
//        @SerializedName("girlsBoys")
//        private String girlsBoys;
//
//        @SerializedName("image")
//        private String image;
//        @SerializedName("city")
//        private String city;
//
//        public Spacecraft(int B_post_id,String category,String girlsBoys,String image,String city ){
//
//            this.B_post_id=B_post_id;
//            this.category=category;
//            this.girlsBoys=girlsBoys;
//            this.image=image;
//            this.city=city;
//        }
//
//
//        public int getB_post_id() {
//            return B_post_id;
//        }
//
//        public void setB_post_id(int b_post_id) {
//            B_post_id = b_post_id;
//        }
//
//        public String getCategory() {
//            return category;
//        }
//
//        public void setCategory(String category) {
//            this.category = category;
//        }
//
//        public String getGirlsBoys() {
//            return girlsBoys;
//        }
//
//        public void setGirlsBoys(String girlsBoys) {
//            this.girlsBoys = girlsBoys;
//        }
//
//        public String getImage() {
//            return image;
//        }
//
//        public void setImage(String image) {
//            this.image = image;
//        }
//
//        public String getCity() {
//            return city;
//        }
//
//        public void setCity(String city) {
//            this.city = city;
//        }
//
//
//        @Override
//        public String toString() {
//            return category;
//        }
//    }
//
//
//
//    interface MyAPIService{
//
//        @GET("/Android/files")
//        Call<List<Spacecraft>> getSpacecraft;
//
//    }
//
//    static class RetrofitClientInstance{
//        private static Retrofit retrofit;
//
//        public static Retrofit getRetrofitInstance(){
//            if (retrofit==null){
//                retrofit=new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
//
//            }
//
//            return retrofit;
//        }
//    }
//
//    class FilterHelper extends Filter {
//
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            return null;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_food);
    }
}