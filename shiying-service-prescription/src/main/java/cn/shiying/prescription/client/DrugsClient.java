package cn.shiying.prescription.client;

import cn.shiying.common.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequestMapping("/drugs/detailed")
@FeignClient("shiying-service-drugs")
public interface DrugsClient {
    @PostMapping("/getPrice")
    Result getPrice(@RequestParam("drugIds") List<Integer> drugIds);
}
