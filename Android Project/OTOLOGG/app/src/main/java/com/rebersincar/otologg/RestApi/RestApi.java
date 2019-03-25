package com.rebersincar.otologg.RestApi;

import com.rebersincar.otologg.Models.BilgiChangePojo;
import com.rebersincar.otologg.Models.Bilgiler;
import com.rebersincar.otologg.Models.ComparePojo;
import com.rebersincar.otologg.Models.LoginPojo;
import com.rebersincar.otologg.Models.MarkaDuzenlePojo;
import com.rebersincar.otologg.Models.MarkaEklePojo;
import com.rebersincar.otologg.Models.MarkaPojo;
import com.rebersincar.otologg.Models.MarkaSilPojo;
import com.rebersincar.otologg.Models.ModelDuzenlePojo;
import com.rebersincar.otologg.Models.ModelEklePojo;
import com.rebersincar.otologg.Models.ModelPojo;
import com.rebersincar.otologg.Models.ModelSilPojo;
import com.rebersincar.otologg.Models.PasswordChangePojo;
import com.rebersincar.otologg.Models.PasswordPojo;
import com.rebersincar.otologg.Models.PuanKontrolPojo;
import com.rebersincar.otologg.Models.PuanPojo;
import com.rebersincar.otologg.Models.RegisterPojo;
import com.rebersincar.otologg.Models.ResimDelPojo;
import com.rebersincar.otologg.Models.ResimEklePojo;
import com.rebersincar.otologg.Models.ResimPojo;
import com.rebersincar.otologg.Models.ResimSilPojo;
import com.rebersincar.otologg.Models.SiralamaPojo;
import com.rebersincar.otologg.Models.SliderPojo;
import com.rebersincar.otologg.Models.VideoEklePojo;
import com.rebersincar.otologg.Models.VideoPojo;
import com.rebersincar.otologg.Models.VideoSilPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {
    @FormUrlEncoded
    @POST("/login.php")
    Call<LoginPojo> control(@Field("mail") String mail,
                            @Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("/register.php")
    Call<RegisterPojo> register(@Field("ad") String ad,
                                @Field("soyad") String soyad,
                                @Field("email") String email,
                                @Field("sifre") String sifre);

    @GET("/markalar.php")
    Call<List<MarkaPojo>> marka();

    @FormUrlEncoded
    @POST("/model.php")
    Call<List<ModelPojo>> model(@Field("marka_id") int id);

    @FormUrlEncoded
    @POST("/videos.php")
    Call<VideoPojo> video(@Field("markaid") int markaid,
                          @Field("modelid") int modelid);
    @FormUrlEncoded
    @POST("/comparemodel.php")
    Call<ComparePojo> compare(@Field("marka") int markaid,
                              @Field("model") int modelid);

    @GET("/images.php")
    Call<List<SliderPojo>> slider(@Query("markaid") int markaid,
                                  @Query("modelid") int modelid);

    @GET("/siralama.php")
    Call<List<SiralamaPojo>> siralama();

    @GET("/puan.php")
    Call<PuanPojo> puanver(@Query("userid") int userid,
                           @Query("markaid") int markaid,
                           @Query("modelid") int modelid,
                           @Query("userpuan") int userpuan);

    @GET("/puankontrol.php")
    Call<PuanKontrolPojo> puankontrol(@Query("userid") int userid,
                                  @Query("markaid") int markaid,
                                  @Query("modelid") int modelid);

    //ADMİN PANEL
    @FormUrlEncoded
    @POST("/markaekle.php")
    Call<MarkaEklePojo> markaekle(@Field("markaad") String markaAd,
                                @Field("logo") String logo);

    @FormUrlEncoded
    @POST("/markaduzenle.php")
    Call<MarkaDuzenlePojo> markaduzenle(@Field("id") int id,
                                        @Field("markaad") String markaAd,
                                        @Field("logo") String logo,
                                        @Field("oldLogo") String oldLogo);

    @FormUrlEncoded
    @POST("/markasil.php")
    Call<MarkaSilPojo> markasil  (@Field("id") int id);


    @FormUrlEncoded
    @POST("/modelekle.php")
    Call<ModelEklePojo> modelekle(@Field("markaid") int markaId,
                                  @Field("modelad") String modelAd,
                                  @Field("modelyil") String modelYil,
                                  @Field("yakitid") int yakitId,
                                  @Field("vitesid") int vitesId,
                                  @Field("viteskademe") int vitesKademe,
                                  @Field("motorgucu") float motorGucu,
                                  @Field("silindirsayisi") int silindirSayisi,
                                  @Field("koltuksayisi") int koltukSayisi,
                                  @Field("kapisayisi") int kapiSayisi,
                                  @Field("yakitdepohacmi") int yakitDepoHacmi,
                                  @Field("agirlik") int agirlik,
                                  @Field("hiz") int hiz,
                                  @Field("fiyat") int fiyat);

    @FormUrlEncoded
    @POST("/modelduzenle.php")
    Call<ModelDuzenlePojo> modelduzenle (@Field("markaid") int markaId,
                                         @Field("modelid") int modelId,
                                         @Field("modelad") String modelAd,
                                         @Field("modelyil") String modelYil,
                                         @Field("yakitid") int yakitId,
                                         @Field("vitesid") int vitesId,
                                         @Field("viteskademe") int vitesKademe,
                                         @Field("motorgucu") float motorGucu,
                                         @Field("silindirsayisi") int silindirSayisi,
                                         @Field("koltuksayisi") int koltukSayisi,
                                         @Field("kapisayisi") int kapiSayisi,
                                         @Field("yakitdepohacmi") int yakitDepoHacmi,
                                         @Field("agirlik") int agirlik,
                                         @Field("hiz") int hiz,
                                         @Field("fiyat") int fiyat,
                                         @Field("puan") int puan);



    @FormUrlEncoded
    @POST("/modelsil.php")
    Call<ModelSilPojo> modelsil  (@Field("markaid") int markaid,
                                  @Field("modelid") int modelid);

    @FormUrlEncoded
    @POST("/videoekle.php")
    Call<VideoEklePojo> videoekle  (@Field("markaid") int markaid,
                                   @Field("modelid") int modelid,
                                    @Field("reklam") String reklam,
                                    @Field("inceleme") String inceleme,
                                    @Field("kaza") String kaza);

    @FormUrlEncoded
    @POST("/videosil.php")
    Call<VideoSilPojo> videosil  (@Field("markaid") int markaid,
                                  @Field("modelid") int modelid);

    @FormUrlEncoded
    @POST("/resimsil.php")
    Call<ResimSilPojo> resimsil  (@Field("markaid") int markaid,
                                  @Field("modelid") int modelid);


    @GET("/resimdel.php")
    Call<ResimDelPojo> resimdel  (@Query("resimid") int resimId);

    @GET("/resim.php")
    Call<List<ResimPojo>> resimler  (@Query("markaid") int markaId,
                                     @Query("modelid") int modelId);


    @FormUrlEncoded
    @POST("/resimekle.php")
    Call<ResimEklePojo> resimekle  (@Field("markaid") int markaId,
                                @Field("modelid") int modelId,
                                @Field("resim") String resim);

    //USER SETTINGS

    @FormUrlEncoded
    @POST("/password.php")
    Call<PasswordPojo> sifrekontrol (@Field("userid") int userId);

    @FormUrlEncoded
    @POST("/passwordchange.php")
    Call<PasswordChangePojo> sifredegistir (@Field("userid") int userId,
                                            @Field("password") String password);
    @FormUrlEncoded
    @POST("/bilgi.php")
    Call<Bilgiler> bilgiler (@Field("userid") int userId);

    @FormUrlEncoded
    @POST("/bilgichange.php")
    Call<BilgiChangePojo> bilgidegistir (@Field("userid") int userId,
                                         @Field("ad") String ad,
                                         @Field("soyad") String soyad,
                                         @Field("mail") String mail);
}