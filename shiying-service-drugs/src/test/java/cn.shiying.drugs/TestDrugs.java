package cn.shiying.drugs;

import cn.shiying.common.utils.PageUtils;
import cn.shiying.drugs.entity.DrugsStorageReportsLoss;
import cn.shiying.drugs.service.DrugsDetailedService;
import cn.shiying.drugs.service.DrugsStorageReportsLossService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest(classes = { DrugsApplication.class })
@RunWith(SpringRunner.class)
public class TestDrugs {

    @Autowired
    private DrugsStorageReportsLossService storageService;

    @Test
    public void test1(){
        List<DrugsStorageReportsLoss> list=storageService.selectDrugsId();
        System.out.println("数据："+list);
    }

    @Test
    public void test2(){
        DrugsStorageReportsLoss DrugsStorage=new DrugsStorageReportsLoss(1,1,6,"过期","过期","李四");
        storageService.addStorageReport(DrugsStorage);
    }

    @Test
    public void test3(){
        Map<String, Object> params = new HashMap();
        params.put("1",2);
        PageUtils page = storageService.queryPage(params);
        System.out.println(page);
    }

}
