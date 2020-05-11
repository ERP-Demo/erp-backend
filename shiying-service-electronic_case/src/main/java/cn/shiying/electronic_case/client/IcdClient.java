package cn.shiying.electronic_case.client;

import cn.shiying.common.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/icd/icd")
@FeignClient("shiying-service-icd")
public interface IcdClient {

    @PostMapping("/icds")
    public Result icds(@RequestParam("ids") List<String> ids);
}
