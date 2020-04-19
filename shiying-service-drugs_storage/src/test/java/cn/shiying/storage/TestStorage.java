package cn.shiying.storage;

import cn.shiying.common.utils.PageUtils;
import cn.shiying.drugs_storage.Drugs_storageApplication;
import cn.shiying.drugs_storage.entity.DrugsStorageDetailedInfo;
import cn.shiying.drugs_storage.service.DrugsStorageDetailedInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest(classes = { Drugs_storageApplication.class })
@RunWith(SpringRunner.class)
public class TestStorage {

    @Autowired
    private DrugsStorageDetailedInfoService storageService;

    //创建jwt令牌
    @Test
    public void testRedis(){
        Map<String, Object> params = new HashMap();
        params.put("1",2);
        PageUtils page = storageService.queryPage(params);
        System.out.println(page);
    }

    @Test
    public void test2(){
//        List<DrugsStorageDetailedInfo> list= storageService.listDrugsDetailedVO();
    }

}
