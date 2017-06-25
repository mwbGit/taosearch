package com.taosearch.service.dataoke;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taosearch.http.AbstractHttpClient;
import com.taosearch.service.dataoke.api.IDaoLaoKeService;
import com.taosearch.service.dataoke.api.ProductMO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by MengWeiBo on 2017-03-29
 */

@Service("daoLaoKeService")
public class DaoLaoKeService extends AbstractHttpClient implements IDaoLaoKeService {

    @Value("8jdrk90okh")
    private String appKey;

    private static String detailUrl = "http://api.dataoke.com/index.php?r=port/index";

    public static void main(String[] args) throws Exception {
        DaoLaoKeService service = new DaoLaoKeService();
        service.setAppKey("8jdrk90okh");
        service.setMock(true);
        service.setMaxConnection(15);
        service.setConnectionTimeout(10000);
        service.setSocketTimeout(10000);
        service.init();
        Date a = new Date();
        for (int i=1;i<1000;i++) {
            ProductMO mo = service.getProductMO("543237719983");

            if (i%50 == 0) {
                Thread.sleep(60000);
                mo = service.getProductMO("543237719983");
            }
            if (mo == null) {
                Date a1 = new Date();

                System.out.print("");
            }else {
                System.out.print(i);
            }

        }
    }

    @Override
    public ProductMO getProductMO(String productId, String appkey) {
        setAppKey(appkey);

        return getProductMO(productId);
    }

    @Override
    public ProductMO getProductMO(String productId) {
        try {
            if (StringUtils.isBlank(productId)) {
                return null;
            }

            String jsonResult = get(getUrl(productId), null, null);
            JSONObject jsonObject = JSON.parseObject(jsonResult);

            String result = jsonObject.getString("result");
            if (StringUtils.isBlank(result)) {
                return null;
            }

            ProductMO mo = JSONObject.parseObject(result, ProductMO.class);

            return mo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getUrl(String productId) {
        return detailUrl + "&appkey=" + appKey + "&v=2&id=" + productId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
