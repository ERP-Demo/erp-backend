package cn.shiying.prescription.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableScheduling
public class DrugsSchedule {

    private int num;
    public DrugsSchedule(){
        num=1;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void restid(){
        num=1;
    }

    public String createId() {
        //进货单号
        String n="";
        if (10 > num){
            n = "00" + num;
        } else if(100 > num) {
            n = "0" + num;
        } else {
            n = num+"";
        }
        num++;
        return "PR" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + n;
    }
}
