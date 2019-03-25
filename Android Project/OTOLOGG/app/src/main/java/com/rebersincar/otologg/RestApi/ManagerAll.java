package com.rebersincar.otologg.RestApi;

import com.rebersincar.otologg.Activitys.AdminPanel.ResimEkle;
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

public class ManagerAll extends BaseManager {
    private static ManagerAll ourInstance = new ManagerAll();
    public static synchronized ManagerAll getInstance()
    {
        return ourInstance;
    }
    public Call<LoginPojo> login (String mail, String sifre)
    {
        Call<LoginPojo> call = getRestApiClient().control(mail,sifre);
        return call;
    }

    public Call<RegisterPojo> register (String ad, String soyad, String email, String sifre)
    {
        Call<RegisterPojo> call = getRestApiClient().register(ad,soyad,email,sifre);
        return call;
    }

    public Call<List<ModelPojo>> model(int id)
    {
        Call<List<ModelPojo>> call = getRestApiClient().model(id);
        return call;
    }

    public Call<List<MarkaPojo>> marka()
    {
        Call<List<MarkaPojo>> call = getRestApiClient().marka();
        return call;
    }

    public Call<VideoPojo> video (int markaid, int modelid)
    {
        Call<VideoPojo> call = getRestApiClient().video(markaid,modelid);
        return call;
    }

    public Call<ComparePojo> compare (int markaid, int modelid)
    {
        Call<ComparePojo> call = getRestApiClient().compare(markaid,modelid);
        return call;
    }

    public Call<List<SliderPojo>> slider (int markaid, int modelid)
    {
        Call<List<SliderPojo>> call = getRestApiClient().slider(markaid,modelid);
        return call;
    }

    public Call<List<SiralamaPojo>> siralama ()
    {
        Call<List<SiralamaPojo>> call = getRestApiClient().siralama();
        return call;
    }

    public Call<PuanPojo> puanver (int userid, int markaid, int modelid,int userpuan)
    {
        Call<PuanPojo> call = getRestApiClient().puanver(userid,markaid,modelid,userpuan);
        return call;
    }

    public Call<PuanKontrolPojo> puankontrol (int userid, int markaid, int modelid)
    {
        Call<PuanKontrolPojo> call = getRestApiClient().puankontrol(userid,markaid,modelid);
        return call;
    }

    public Call<MarkaEklePojo> markaekle (String markaAd, String logo)
    {
        Call<MarkaEklePojo> call = getRestApiClient().markaekle(markaAd,logo);
        return call;
    }

    public Call<MarkaDuzenlePojo> markaduzenle (int id,String markaAd, String logo, String oldLogo)
    {
        Call<MarkaDuzenlePojo> call = getRestApiClient().markaduzenle(id,markaAd,logo,oldLogo);
        return call;
    }

    public Call<MarkaSilPojo> markasil (int id)
    {
        Call<MarkaSilPojo> call = getRestApiClient().markasil(id);
        return call;
    }

    public Call<ModelEklePojo> modelekle (int markaId,
                                          String modelAd,
                                          String modelYil,
                                          int yakitId,
                                          int vitesId,
                                          int vitesKademe,
                                          float motorGucu,
                                          int silindirSayisi,
                                          int koltukSayisi,
                                          int kapiSayisi,
                                          int yakitDepoHacmi,
                                          int agirlik,
                                          int hiz,
                                          int fiyat)
    {
        Call<ModelEklePojo> call = getRestApiClient().modelekle(markaId,
                                                                modelAd,
                                                                modelYil,
                                                                yakitId,
                                                                vitesId,
                                                                vitesKademe,
                                                                motorGucu,
                                                                silindirSayisi,
                                                                koltukSayisi,
                                                                kapiSayisi,
                                                                yakitDepoHacmi,
                                                                agirlik,
                                                                hiz,
                                                                fiyat);
        return call;
    }

    public Call<ModelDuzenlePojo> modelduzenle (int markaId,
                                             int modelId,
                                             String modelAd,
                                             String modelYil,
                                             int yakitId,
                                             int vitesId,
                                             int vitesKademe,
                                             float motorGucu,
                                             int silindirSayisi,
                                             int koltukSayisi,
                                             int kapiSayisi,
                                             int yakitDepoHacmi,
                                             int agirlik,
                                             int hiz,
                                             int fiyat,
                                                int puan)
    {
        Call<ModelDuzenlePojo> call = getRestApiClient().modelduzenle(markaId,
                modelId,
                modelAd,
                modelYil,
                yakitId,
                vitesId,
                vitesKademe,
                motorGucu,
                silindirSayisi,
                koltukSayisi,
                kapiSayisi,
                yakitDepoHacmi,
                agirlik,
                hiz,
                fiyat,
                puan);

        return call;
    }

    public Call<ModelSilPojo> modelsil (int markaid, int modelid)
    {
        Call<ModelSilPojo> call = getRestApiClient().modelsil(markaid,modelid);
        return call;
    }

    public Call<VideoEklePojo> videoekle (int markaid, int modelid, String reklam, String inceleme, String kaza)
    {
        Call<VideoEklePojo> call = getRestApiClient().videoekle(markaid,modelid,reklam,inceleme,kaza);
        return call;
    }

    public Call<VideoSilPojo> videosil (int markaid, int modelid)
    {
        Call<VideoSilPojo> call = getRestApiClient().videosil(markaid,modelid);
        return call;
    }

    public Call<ResimSilPojo> resimsil (int markaid, int modelid)
    {
        Call<ResimSilPojo> call = getRestApiClient().resimsil(markaid,modelid);
        return call;
    }

    public Call<ResimDelPojo> resimdel (int resimId)
    {
        Call<ResimDelPojo> call = getRestApiClient().resimdel(resimId);
        return call;
    }

    public Call<List<ResimPojo>> resimler (int markaid, int modelid)
    {
        Call<List<ResimPojo>> call = getRestApiClient().resimler(markaid,modelid);
        return call;
    }

    public Call<ResimEklePojo> resimekle (int markaid, int modelid, String resim)
    {
        Call<ResimEklePojo> call = getRestApiClient().resimekle(markaid,modelid,resim);
        return call;
    }

    public Call<PasswordPojo> sifrekontrol (int userId)
    {
        Call<PasswordPojo> call = getRestApiClient().sifrekontrol(userId);
        return call;
    }

    public Call<PasswordChangePojo> sifredegistir (int userId, String password)
    {
        Call<PasswordChangePojo> call = getRestApiClient().sifredegistir(userId,password);
        return call;
    }

    public Call<Bilgiler> bilgiler (int userId)
    {
        Call<Bilgiler> call = getRestApiClient().bilgiler(userId);
        return call;
    }

    public Call<BilgiChangePojo> bilgidegistir (int userId, String ad, String soyad, String mail)
    {
        Call<BilgiChangePojo> call = getRestApiClient().bilgidegistir(userId,ad,soyad,mail);
        return call;
    }

}
