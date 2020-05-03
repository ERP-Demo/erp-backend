package cn.shiying.drugs_purchase;

import cn.shiying.common.utils.PageUtils;
import cn.shiying.drugs_purchase.config.DrugsSchedule;
import cn.shiying.drugs_purchase.entity.form.Drugs;
import cn.shiying.drugs_purchase.entity.form.DrugsAndDetailed;
import cn.shiying.drugs_purchase.service.SupplierAndDrugsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.SchemaOutputResolver;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest(classes = { Drugs_purchaseApplication.class })
@RunWith(SpringRunner.class)
public class TestDrugs_purchase {

    @Autowired
    DrugsSchedule schedule;
    @Test
    public void test3() {
//        System.out.println("1");
        System.out.println(schedule.createId());
        System.out.println(schedule.createId());
    }

}
